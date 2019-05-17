package com.ayundao.base;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.ayundao.base.utils.RedisUtils;
import com.ayundao.base.utils.SpringUtils;
import com.ayundao.entity.User;
import com.ayundao.service.RedisServcie;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * @ClassName: BaseController
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/13 16:24
 * @Description: Controller - 基类
 * @Version: V1.0
 */
public abstract class BaseController {

    /**
     * 错误消息
     */
    protected static final String ERROR_MESSAGE = "common.message.error";

    /**
     * 成功消息
     */
    protected static final String SUCCESS_MESSAGE = "common.message.success";

    /**
     * 请求无法处理视图
     */
    protected static final String UNPROCESSABLE_ENTITY_VIEW = "common/error/unprocessable_entity";

    /**
     * "瞬时消息"属性名称
     */
    protected static final String FLASH_MESSAGE_ATTRIBUTE_NAME = "flashMessage";

    /**
     * "验证结果"属性名称
     */
    private static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME = "constraintViolations";

    @Autowired
    private Validator validator;

    @Autowired
    private RedisServcie redisServcie;

    private User user;

    /**
     * 数据验证
     *
     * @param target
     *            验证对象
     * @param groups
     *            验证组
     * @return 验证结果
     */
    protected boolean isValid(Object target, Class<?>... groups) {

        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target, groups);
        if (constraintViolations.isEmpty()) {
            return true;
        }
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations, RequestAttributes.SCOPE_REQUEST);
        return false;
    }

    /**
     * 数据验证
     *
     * @param targets
     *            验证对象
     * @param groups
     *            验证组
     * @return 验证结果
     */
    protected boolean isValid(Collection<Object> targets, Class<?>... groups) {

        for (Object target : targets) {
            if (!isValid(target, groups)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 数据验证
     *
     * @param type
     *            类型
     * @param property
     *            属性
     * @param value
     *            值
     * @param groups
     *            验证组
     * @return 验证结果
     */
    protected boolean isValid(Class<?> type, String property, Object value, Class<?>... groups) {

        Set<?> constraintViolations = validator.validateValue(type, property, value, groups);
        if (constraintViolations.isEmpty()) {
            return true;
        }
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations, RequestAttributes.SCOPE_REQUEST);
        return false;
    }

    /**
     * 数据验证
     *
     * @param type
     *            类型
     * @param properties
     *            属性
     * @param groups
     *            验证组
     * @return 验证结果
     */
    protected boolean isValid(Class<?> type, Map<String, Object> properties, Class<?>... groups) {

        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            if (!isValid(type, entry.getKey(), entry.getValue(), groups)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取国际化消息
     *
     * @param code
     *            代码
     * @param args
     *            参数
     * @return 国际化消息
     */
    protected String message(String code, Object... args) {
        return SpringUtils.getMessage(code, args);
    }

    /**
     * 添加瞬时消息
     *
     * @param redirectAttributes
     *            RedirectAttributes
     * @param message
     *            消息
     * @param args
     *            参数
     */
    protected void addFlashMessage(RedirectAttributes redirectAttributes, String message, Object... args) {

        redirectAttributes.addFlashAttribute(FLASH_MESSAGE_ATTRIBUTE_NAME, SpringUtils.getMessage(message, args));
    }

    /**
     * 存储当前用户
     * @param req
     * @param resp
     * @param user
     * @return
     */
    public User setCurrentUser(HttpServletRequest req, HttpServletResponse resp, User user) {
        redisServcie.set("user:" + user.getAccount(), user.toString());
        setUser(user);
        return this.user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}