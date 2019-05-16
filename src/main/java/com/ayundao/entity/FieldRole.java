package com.ayundao.entity;

import com.ayundao.base.BaseEntity;

import javax.persistence.*;
import java.util.UUID;

/**
 * @ClassName: FieldRole
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/16 18:15
 * @Description: 字段角色
 * @Version: V1.0
 */
@Entity
@Table(name = "t_file_role")
@SecondaryTables({
        @SecondaryTable(name="t_field", pkJoinColumns = {
                @PrimaryKeyJoinColumn(name = "FIELDID")
        }),
        @SecondaryTable(name="t_user_role", pkJoinColumns = {
                @PrimaryKeyJoinColumn(name = "USERROLEID")
        }),
        @SecondaryTable(name="t_user_group_relation", pkJoinColumns = {
                @PrimaryKeyJoinColumn(name = "USERGROUPRELATIONID")
        })
})
public class FieldRole extends BaseEntity<UUID> {

    private static final long serialVersionUID = -1237498712983479L;

    /**
     * 所属字段
     */
    @Column(name = "FIELDID")
    private Field field;

    /**
     * 所属用户组关系
     */
    @Column(name = "USERGROUPRELATIONID")
    private UserGroupRelation userGroupRelation;

    /**
     * 所属角色关系
     */
    @Column(name = "USERROLEID")
    private UserRole userRole;

    /**
     * 备用字段1
     */
    @Column(name = "INFO1")
    private String info1;

    /**
     * 备用字段2
     */
    @Column(name = "INFO2")
    private String info2;

    /**
     * 备用字段3
     */
    @Column(name = "INFO3")
    private String info3;

    /**
     * 备用字段4
     */
    @Column(name = "INFO4")
    private String info4;

    /**
     * 备用字段5
     */
    @Column(name = "INFO5")
    private String info5;
}
