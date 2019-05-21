package com.ayundao.repository;

import com.ayundao.entity.User;
import com.ayundao.entity.UserRelation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: UserRelationRepository
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/20 15:08
 * @Description: 仓库 - 用户关系
 * @Version: V1.0
 */
@Repository
public interface UserRelationRepository extends CrudRepository<UserRelation, String> {

    List<UserRelation> findByUser(User user);
}
