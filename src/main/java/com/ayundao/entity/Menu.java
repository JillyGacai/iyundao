package com.ayundao.entity;

import com.ayundao.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * @ClassName: Menu
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/16 14:55
 * @Description: 菜单
 * @Version: V1.0
 */
@Entity
@Table(name = "t_menu")
public class Menu extends BaseEntity<UUID> {

    private static final long serialVersionUID = -12374981274912789L;

    /**
     * 名称
     */
    @Column(name = "NAME", nullable = false, length = 30)
    private String name;

    /**
     * 负责人
     */
    @Column(name = "USER_ID", nullable = false)
    private User user;

    /**
     * 描述
     */
    @Column(name = "REMARK", length = 500)
    private String remark;

    /**
     * 是否公开
     */
    @Column(name = "ISPUBLIC", columnDefinition = "boolean default false")
    private boolean isPublic;

    /**
     * 路径
     */
    @Column(name = "URI", length = 10)
    private String uri;

    /**
     * 父级--菜单
     */
    @Column(name = "FATHER_ID")
    private Menu father;

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
