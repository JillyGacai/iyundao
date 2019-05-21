package com.ayundao.controller;

import com.ayundao.base.BaseController;
import com.ayundao.base.utils.EncryptUtils;
import com.ayundao.base.utils.JsonUtils;
import com.ayundao.entity.User;
import com.ayundao.service.RedisServcie;
import com.ayundao.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
@Controller
@RequestMapping("/")
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
    public String login(String account, String password, Model model, HttpServletRequest req, HttpServletResponse resp) {
        User user = getUser();
        if (user != null) {
            return "redirect:/subject/list";
        }
        if (StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password)) {
            user = userService.findByAccount(account);
            if (user == null) {
                model.addAttribute(ERROR_MESSAGE, "用户名/密码不正确");
                return "index";
            }
            password = EncryptUtils.getSaltMD5(password, user.getSalt());
            if (password.equals(user.getPassword())) {
                //封装用户
                setCurrentUser(req, resp, user);
                return "redirect:/subject/list";
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
     * @return
     */
    @PostMapping("/loginout")
    public String out() {
        User user = getUser();
        if (user != null) {
            loginOut(user);
        }
        return "index";
    }

}
