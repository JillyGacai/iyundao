package com.ayundao.controller;

import com.ayundao.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping
    public String index() {
        return "index";
    }

}
