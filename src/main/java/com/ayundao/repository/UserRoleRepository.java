package com.ayundao.repository;

import com.ayundao.entity.User;
import com.ayundao.entity.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @ClassName: UserRoleRepository
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/20 14:55
 * @Description: 仓库 - 角色关系
 * @Version: V1.0
 */
@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, String> {

    /**
     * 查找用户拥有的角色
     * @param user
     * @return
     */
    List<UserRole> findByUser(User user);
}
