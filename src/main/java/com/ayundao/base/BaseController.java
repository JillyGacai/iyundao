package com.ayundao.base;

import java.lang.reflect.Method;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;


import com.ayundao.base.annotation.Permission;
import com.ayundao.base.exception.AuthenticationException;
import com.ayundao.base.utils.*;
import com.ayundao.entity.User;
import com.ayundao.entity.UserGroupRelation;
import com.ayundao.entity.UserRelation;
import com.ayundao.entity.UserRole;
import com.ayundao.service.UserGroupRelationService;
import com.ayundao.service.UserRelationService;
import com.ayundao.service.UserRoleService;
import com.ayundao.service.UserService;
import org.hibernate.Session;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * @ClassName: BaseController
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/13 16:24
 * @Description: Controller - 基类
 * @Version: V1.0
 */
@Permission
@Component
public abstract class BaseController {

    /**
     * 角色验证
     */
    protected static final String AUTHOR_ROLE ="role";

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
    /**
     * 用户组验证
     */
    protected static final String AUTHOR_USER_GROUP ="user.group";
    /**
     * 机构验证
     */
    protected static final String AUTHOR_SUBJECT ="subject";
    @Value("${server.salt}")
    private String salt;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserRelationService userRelationService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserGroupRelationService userGroupRelationService;

    @Autowired
    private UserService userService;

    private String account;

    private User user;



    /**
     * 数据验证
     *
     * @param target 验证对象
     * @param groups 验证组
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
     * @param targets 验证对象
     * @param groups  验证组
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
     * @param type     类型
     * @param property 属性
     * @param value    值
     * @param groups   验证组
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
     * @param type       类型
     * @param properties 属性
     * @param groups     验证组
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
     * @param code 代码
     * @param args 参数
     * @return 国际化消息
     */
    protected String message(String code, Object... args) {
        return SpringUtils.getMessage(code, args);
    }

    /**
     * 添加瞬时消息
     *
     * @param redirectAttributes RedirectAttributes
     * @param message            消息
     * @param args               参数
     */
    protected void addFlashMessage(RedirectAttributes redirectAttributes, String message, Object... args) {
        redirectAttributes.addFlashAttribute(FLASH_MESSAGE_ATTRIBUTE_NAME, SpringUtils.getMessage(message, args));
    }

    /**
     * 授权登录异常
     */
    @ExceptionHandler({AuthenticationException.class})
    public String authenticationException() {
        return "index";
    }

    /**
     * 存储当前用户
     *
     * @param req
     * @param resp
     * @param user
     * @return
     */
    public User setCurrentUser(HttpServletRequest req, HttpServletResponse resp, User user) {
        //用户信息
        account = user.getAccount();
        account = EncryptUtils.DESencode(account, salt);
        redisUtils.set("user:" + account, JsonUtils.getJson(user));
        req.setAttribute(account, account);
        //机构信息
        List<UserRelation> userRelation = userRelationService.findByUser(user);
        user.setUserRelations(new HashSet<>(userRelation));

        //用户组信息
        List<UserGroupRelation> userGroupRelations = userGroupRelationService.findByUser(user);
        user.setUserGroupRelations(new HashSet<>(userGroupRelations));

        //角色信息
        List<UserRole> userRoles = userRoleService.findByUser(user);
        user.setUserRoles(new HashSet<>(userRoles));
        setUser(user);
        req.getSession().setAttribute("i-YunDao-account", account);
        return this.user;
    }


    public User getUser() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        if (request.getSession().getAttribute("i-YunDao-account") == null) {
            return null;
        } 
        account = request.getSession().getAttribute("i-YunDao-account").toString();
        user = user == null ? new User() : user;
        user = redisUtils.get("user:" + account) != null
                ? toUser(redisUtils.get("user:" + account).toString())
                : null;
        if (user == null) {
            user = userService.findByAccount(EncryptUtils.DESdecode(account, salt));
        } 
        return user;
    }

    private User toUser(String str) {
        try {
            JSONObject json = new JSONObject(str);
            user.setId(json.get("id").toString());
            user.setAccount(json.get("account").toString());
            user.setName(json.get("name").toString());
            user.setRemark(json.get("remark").toString());
            user.setSalt(json.get("salt").toString());
            user.setPassword(json.get("password").toString());
            user.setSex(Integer.parseInt(json.get("sex").toString()));
            for (User.USER_TYPE type : User.USER_TYPE.values()) {
                if (type.toString().equals(json.get("userType").toString())) {
                    user.setUserType(type);
                    break;
                } 
            }
            for (User.ACCOUNT_TYPE type : User.ACCOUNT_TYPE.values()) {
                if (type.toString().equals(json.get("status").toString())) {
                    user.setStatus(type);
                    break;
                } 
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 退出登录注销数据
     */
    public void loginOut(User user) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        request.getSession().removeAttribute("i-YunDao-account");
        redisUtils.del("user:" + user.getAccount());
        setUser(null);
        this.user = null;
    }

}