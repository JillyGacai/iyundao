package com.ayundao.entity;

import com.ayundao.base.BaseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.UUID;

/**
 * @ClassName: ButtonRole
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/16 18:10
 * @Description: 按钮和角色关系
 * @Version: V1.0
 */
@Entity
@Table(name = "t_button_role")
@SecondaryTables({
        @SecondaryTable(name="t_button", pkJoinColumns = {
                @PrimaryKeyJoinColumn(name = "BUTTONID")
        }),
        @SecondaryTable(name="t_user_relation", pkJoinColumns = {
                @PrimaryKeyJoinColumn(name = "USERGROUPRELATIONID")
        }),
        @SecondaryTable(name="t_user_role", pkJoinColumns = {
                @PrimaryKeyJoinColumn(name = "USERROLEID")
        })
})
public class ButtonRole extends BaseEntity<UUID> {

    private static final long serialVersionUID = -4912830948120L;

    /**
     * 所属按钮
     */
    @Column(name = "BUTTONID")
    private Button button;

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
