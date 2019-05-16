package com.ayundao.entity;

import com.ayundao.base.BaseEntity;
import javax.persistence.*;
import java.util.UUID;

/**
 * @ClassName: User
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/15 9:39
 * @Description: 用户
 * @Version: V1.0
 */
@Entity
@Table(name = "t_user")
public class User extends BaseEntity<UUID> {

    private static final long serialVersionUID = -1172094710974098503L;

    /**
     * 账号
     */
    @Column(name = "ACCOUNT", nullable = false, unique = true, length = 50)
    private String account;

    /**
     * 姓名
     */
    @Column(name ="NAME", nullable = false,length = 50)
    private String name;

    /**
     * 密码
     */
    @Column(name = "PASSWORD", nullable = false, length = 50)
    private String password;

    /**
     * 密码盐
     */
    @Column(name = "SALT", nullable = false, length = 8)
    private String salt;

    /**
     * 性别 0-男, 1-女
     */
    @Column(name = "SEX")
    private int sex;

    /**
     * 账号状态
     */
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "STATUS")
    private ACCOUNT_TYPE status;

    /**
     * 用户类型
     */
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "USERTYPE")
    private USER_TYPE userType;

    /**
     * 用户简介
     */
    @Column(name = "REMARK", columnDefinition = "varchar(20) default '未填写'", length = 500)
    private String remark;

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

    /**
     * 账号类型
     */
    public enum ACCOUNT_TYPE {
        /**
         * 禁用
         */
        disable,

        /**
         * 锁定
         */
        block,

        /**
         * 正常
         */
        normal
    }

    /**
     * 用户类型
     */
    public enum USER_TYPE {
        /**
         * 普通用户
         */
        normal,

        /**
         * 管理员
         */
        amdin,

        /**
         * 负责人
         */
       manager
    }

}
