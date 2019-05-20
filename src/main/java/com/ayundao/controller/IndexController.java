package com.ayundao.controller;

import com.ayundao.base.BaseController;
import com.ayundao.base.annotation.Permission;
import com.ayundao.base.utils.MD5Utils;
import com.ayundao.entity.User;
import com.ayundao.service.RedisServcie;
import com.ayundao.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: IndexController
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/13 16:40
 * @Description: 首页
 * @Version: V1.0
 */
@Controller("/")
public class IndexController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisServcie redisServcie;

    /**
     * 首页
     * @return
     */
    @GetMapping
    public String index() {
        return "index";
    }

    /**
     * 登录方法
     * @param account
     * @param password
     * @return
     */
    @PostMapping("/login")
    @Permission
    public String login(String account, String password, Model model, HttpServletRequest req, HttpServletResponse resp) {
        User user = getUser();
        if (user != null) {
            return "list";
        }
        if (StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password)) {
            user = userService.findByAccount(account);
            if (user == null) {
                model.addAttribute(ERROR_MESSAGE, "用户名/密码不正确");
                return "index";
            }
            password = MD5Utils.getSaltMD5(password, user.getSalt());
            if (password.equals(user.getPassword())) {
                //封装用户
                setCurrentUser(req, resp, user);
                return "list";
            }
        } else {
            model.addAttribute(ERROR_MESSAGE, "用户名/密码不能异常");
        }
        return "index";
    }

    /**
     * 注册
     * @param account
     * @param password
     * @return
     */
    @PostMapping("/reg")
    public String register(String account, String password) {
        return "index";
    }

    /**
     * 退出登录
     * @param account
     * @param password
     * @param model
     * @return
     */
    @PostMapping("/loginout")
    public String loginout(String account, String password, Model model) {

        return "index";
    }
}
