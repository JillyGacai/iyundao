package com.ayundao.entity;

import com.ayundao.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * @ClassName: UserGroup
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/16 14:51
 * @Description: 用户组
 * @Version: V1.0
 */
@Entity
@Table(name = "t_user_group")
public class UserGroup extends BaseEntity<UUID> {

    private static final long serialVerisonUID = -129374981273498L;

    /**
     * 名称
     */
    @Column(name = "NAME", nullable = false, length = 30)
    private String name;

    /**
     * 负责人
     */
    @Column(name = "USER_ID")
    private User user;

    /**
     * 父级--用户组
     */
    @Column(name = "FATHER_ID")
    private UserGroup father;

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
