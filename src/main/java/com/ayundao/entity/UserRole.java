package com.ayundao.entity;

import com.ayundao.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * @ClassName: UserRole
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/16 18:38
 * @Description: 用户角色关系
 * @Version: V1.0
 */
@Entity
@Table(name = "t_user_role")
public class UserRole extends BaseEntity<String> {

    private static final long serialVersionUID = -193279412739472L;

    /**
     * 用户ID
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID")
    private User user;

    /**
     * 所属角色
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLEID")
    private Role role;

    /**
     * 字段关系
     */
    @OneToMany(mappedBy = "userRole", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FieldRole> fieldRoles;

    /**
     * 字段关系
     */
    @OneToMany(mappedBy = "userRole", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ButtonRole> buttonRoles;


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
