package com.ayundao.controller;

import com.ayundao.base.BaseController;
import com.ayundao.entity.User;
import com.ayundao.entity.UserRelation;
import com.ayundao.service.MenuRelationService;
import com.ayundao.service.UserRelationService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.lucene.index.TieredMergePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private MenuRelationService menuRelationService;

    @Autowired
    private UserRelationService userRelationService;


    @GetMapping("/list")
    public String list(Model model) {
        User user = getUser();
        if (user == null) {
            return "redirect:/index";
        }
        List<UserRelation> userRelations = getUserRelation(user);
        if (CollectionUtils.isNotEmpty(userRelations)) {
            Set<String> ids = new HashSet<>();

        }
        return "list";
    }

}
