package com.ayundao.entity;

import com.ayundao.base.BaseEntity;

import javax.persistence.*;
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
@Table(name = "t_file_role")
@SecondaryTables({
        @SecondaryTable(name="t_user_group_relation", pkJoinColumns = {
                @PrimaryKeyJoinColumn(name = "USERGROUPRELATIONID")
        }),
        @SecondaryTable(name="t_role", pkJoinColumns = {
                @PrimaryKeyJoinColumn(name = "ROLEID")
        })
})
public class MenuRelation extends BaseEntity<UUID> {

    private static final long serialVersionUID = -1234798123749127L;

    /**
     * 用户路径
     */
    @Column(name = "URI")
    private String uri;

    /**
     * 用户组ID
     */
    @Column(name = "USERGROUPRELATIONID")
    private UserGroupRelation userGroupRelation;

    /**
     * 所属角色
     */
    @Column(name = "ROLEID")
    private Role role;
}
