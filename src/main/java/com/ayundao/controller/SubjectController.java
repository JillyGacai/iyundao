package com.ayundao.controller;

import com.ayundao.base.BaseController;
import com.ayundao.entity.User;
import com.ayundao.service.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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


    @Autowired
    private UserRelationService userRelationService;

    @GetMapping("/list")
    public String list(Model model) {


        User user = getUser();
        return "list";
    }

}
