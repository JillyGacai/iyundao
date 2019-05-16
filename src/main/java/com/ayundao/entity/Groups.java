package com.ayundao.entity;

import com.ayundao.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * @ClassName: Groups
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/16 14:44
 * @Description: 小组(组织)实体
 * @Version: V1.0
 */
@Entity
@Table(name = "t_groups")
public class Groups extends BaseEntity<UUID> {

    private static final long serialVerisonUID = -10927349812794379L;

    /**
     * 名称
     */
    @Column(name = "NAME", length = 30)
    private String name;

    /**
     * 描述
     */
    @Column(name = "REMARK", length = 500)
    private String remark;

    /**
     * 所属机构
     */
    @Column(name = "SUBJECT_ID")
    private Subject subject;

    /**
     * 负责人
     */
    @Column(name = "USER_ID")
    private User user;

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
