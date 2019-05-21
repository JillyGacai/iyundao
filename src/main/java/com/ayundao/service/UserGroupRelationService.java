package com.ayundao.service;

import com.ayundao.entity.User;
import com.ayundao.entity.UserGroupRelation;

import java.util.List;

/**
 * @ClassName: UserGroupRelationService
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/20 14:53
 * @Description: 服务 - 用户关系
 * @Version: V1.0
 */
public interface UserGroupRelationService {

    /**
     * 查找用户所属用户组
     * @param user
     * @return
     */
    List<UserGroupRelation> findByUser(User user);
}
