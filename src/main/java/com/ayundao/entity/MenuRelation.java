package com.ayundao.entity;

import com.ayundao.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * @ClassName: MenuRelation
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/16 18:48
 * @Description: 菜单关系
 * @Version: V1.0
 */
@Entity
@Table(name = "t_menu_relations")
public class MenuRelation extends BaseEntity<String> {

    private static final long serialVersionUID = -1234798123749127L;

    /**
     * 所属机构
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERRELATIONID")
    private UserRelation userRelation;

    /**
     * 用户组ID
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERGROUPRELATIONID")
    private UserGroupRelation userGroupRelation;

    /**
     * 所属角色
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLEID")
    private Role role;

    /**
     * 菜单
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENUID")
    private Menu menu;
}
