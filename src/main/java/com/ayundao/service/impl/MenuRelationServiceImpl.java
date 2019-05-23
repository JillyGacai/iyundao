package com.ayundao.service.impl;

import com.ayundao.repository.MenuRelationRepository;
import com.ayundao.service.MenuRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: MenuRelationServiceImpl
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/23 16:39
 * @Description: 服务实现 - 菜单关系
 * @Version: V1.0
 */
@Service
public class MenuRelationServiceImpl implements MenuRelationService {

    @Autowired
    private MenuRelationRepository menuRelationRepository;

}
