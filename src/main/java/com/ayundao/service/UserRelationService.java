package com.ayundao.service;

import com.ayundao.entity.User;
import com.ayundao.entity.UserRelation;

import java.util.List;

/**
 * @ClassName: UserRelationService
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/20 14:43
 * @Description: 服务-机构关系
 * @Version: V1.0
 */
public interface UserRelationService {

    /**
     * 查找用户本人的机构关系
     * @param user
     * @return
     */
    List<UserRelation> findByUser(User user);
}
