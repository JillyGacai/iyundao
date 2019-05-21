package com.ayundao.controller;

import com.ayundao.base.BaseController;
import com.ayundao.base.annotation.Permission;
import com.ayundao.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @ClassName: SubjectController
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/19 18:24
 * @Description: 控制层 - 机构
 * @Version: V1.0
 */
@Controller
@RequestMapping("/subject")
public class SubjectController extends BaseController {
    
    @GetMapping("/list")
    public String list(Model model) {
        User user = getUser();
        model.addAttribute("userRelation", user.getUserRelations());
        model.addAttribute("userGroupRelation", user.getUserGroupRelations());
        model.addAttribute("userRole", user.getUserRoles());
        return "subject/list.ftl";
    }

}
