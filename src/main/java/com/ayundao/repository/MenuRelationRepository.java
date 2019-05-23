package com.ayundao.repository;

import com.ayundao.entity.MenuRelation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: MenuRelationRepository
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/23 17:52
 * @Description: 仓库 - 菜单关系
 * @Version: V1.0
 */
@Repository
public interface MenuRelationRepository extends CrudRepository<MenuRelation, String> {

}
