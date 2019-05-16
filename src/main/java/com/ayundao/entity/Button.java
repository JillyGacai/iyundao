package com.ayundao.entity;

import com.ayundao.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import java.util.UUID;

/**
 * @ClassName: Button
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/16 15:33
 * @Description: 按钮
 * @Version: V1.0
 */
@Entity
@Table(name = "t_button")
public class Button extends BaseEntity<UUID> {

    private static final long serialVersionUID = -1234798213489L;

    /**
     * 名称
     */
    @Column(name = "NAME", nullable = false, length = 30)
    private String name;

    /**
     * 所属字段
     */
    @Column(name = "FIELDID", nullable = false)
    private Field field;

    /**
     * 执行路径
     */
    @Column(name = "URI", length = 10)
    private String uri;

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
