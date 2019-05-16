package com.ayundao.entity;

import com.ayundao.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
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
public class Field extends BaseEntity<UUID> {

    private static final long serialVerisonUID = -1247192843712987834L;

    /**
     * 名称
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * 所属菜单
     */
    @Column(name = "MENU_ID", nullable = false)
    private Menu menu;

    /**
     * 排序
     */
    @Max(2)
    @Column(name = "SORT", columnDefinition = "tinyint default 0")
    private int sort;

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
