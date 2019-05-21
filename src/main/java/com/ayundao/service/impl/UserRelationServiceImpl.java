package com.ayundao.service.impl;

import com.ayundao.entity.User;
import com.ayundao.entity.UserRelation;
import com.ayundao.repository.UserRelationRepository;
import com.ayundao.service.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: UserRelationServiceImpl
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/20 14:44
 * @Description: 服务实现 - 机构关系
 * @Version: V1.0
 */
@Service
public class UserRelationServiceImpl implements UserRelationService {

    @Autowired
    private UserRelationRepository userRelationRepository;

    @Override
    public List<UserRelation> findByUser(User user) {
        return userRelationRepository.findByUser(user);
    }
}
