package com.ayundao.entity;

import com.ayundao.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Set;
import java.util.UUID;

/**
 * @ClassName: Field
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/16 15:30
 * @Description: 字段
 * @Version: V1.0
 */
@Entity
@Table(name = "t_field")
public class Field extends BaseEntity<String> {

    private static final long serialVerisonUID = -1247192843712987834L;

    /**
     * 名称
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * 所属页面
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAGEID", nullable = false)
    private Page page;

    /**
     * 排序
     */
    @Max(2)
    @Column(name = "SORT", columnDefinition = "tinyint default 0")
    private int sort;

    /**
     * 字段关系
     */
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FieldRole> fieldRoles;

    /**
     * 按钮
     */
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Button> buttons;

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
