package com.ayundao.base;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;


import com.ayundao.base.utils.*;
import com.ayundao.entity.*;
import com.ayundao.service.UserGroupRelationService;
import com.ayundao.service.UserRelationService;
import com.ayundao.service.UserRoleService;
import com.ayundao.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
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
@Component
public abstract class BaseController {


    /**
     * 错误消息
     */
    protected static final String ERROR_MESSAGE = "common.message.error";


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

    private static final String USER_INFO = ":userInfo";

    private static final String USER_SUBJECT = ":subject";

    private static final String USER_GROUP = ":userGroup";

    private static final String USER_ROLE = ":role";


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
        req.setAttribute(account, account);
        String[] ids = null;

        try {
            //机构信息
            List<UserRelation> userRelation = userRelationService.findByUser(user);
            JSONArray arr = new JSONArray();
            for (UserRelation relation : userRelation) {
                JSONObject json = new JSONObject();
                json.put("id", relation.getId());
                json.put("depart", relation.getDepart() != null ? relation.getDepart().getId() : "");
                json.put("groups", relation.getGroups() != null ? relation.getDepart().getId() : "");
                if (!CollectionUtils.isEmpty(relation.getMenuRelations())) {
                    JSONArray menus = new JSONArray();
                    for (MenuRelation menu : relation.getMenuRelations()) {
                        JSONObject mj = new JSONObject();
                        mj.put("id", menu.getId());
                        mj.put("userGroup", menu.getUserGroupRelation() != null ? menu.getUserGroupRelation().getId() : "");
                        mj.put("role", menu.getRole() != null ? menu.getRole().getId() : "");
                        menus.put(mj);
                    }
                }
                arr.put(json);
            }
            redisUtils.set(account + USER_SUBJECT, arr.toString(), 3600);
            //用户组信息
            List<UserGroupRelation> userGroupRelations = userGroupRelationService.findByUser(user);
            saveRelations(userGroupRelations, USER_GROUP);

            //角色信息
            List<UserRole> userRoles = userRoleService.findByUser(user);
            String role = "subject.manager, admin";
            redisUtils.set(account + USER_ROLE, role);
        } catch (JSONException e) {
            e.printStackTrace();
        }



        setUser(user);
        redisUtils.set(account+USER_INFO, JsonUtils.getJson(user));
        req.getSession().setAttribute("i-YunDao-account", account);
        return this.user;
    }

    /**
     * 存储用户关系
     * @param list
     */
    private void saveRelations(List<? extends BaseEntity> list, String name) {
        if (!list.isEmpty()) {
            String[] ids =  new String[list.size()];
            for (int i = 0; i < ids.length; i++) {
                ids[i] = (String) list.get(i).getId();
            }
            redisUtils.set(account + name, JsonUtils.toJson(ids));
        }
    }


    public User getUser() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        if (request.getSession().getAttribute("i-YunDao-account") == null) {
            return null;
        } 
        account = request.getSession().getAttribute("i-YunDao-account").toString();
        user = user == null ? new User() : user;
        user = redisUtils.get(account+USER_INFO) != null
                ? toUser(redisUtils.get(account+USER_INFO).toString())
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