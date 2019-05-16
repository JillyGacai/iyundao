package com.ayundao.entity;

import com.ayundao.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import java.util.UUID;

/**
 * @ClassName: Page
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/16 15:14
 * @Description: 页面
 * @Version: V1.0
 */
@Entity
@Table(name = "t_page")
public class Page extends BaseEntity<UUID> {

    private static final long serivalVersionUID = -129034801289823L;

    /**
     * 标题
     */
    @Column(name = "TITLE", length = 50)
    private String title;

    /**
     * 路径
     */
    @Column(name = "URI", length = 10, unique = true)
    private String uri;

    /**
     * 所属菜单
     */
    @Column(name = "MENU_ID")
    private Menu menu;

    /**
     * 页面名称 == index(无需后缀)
     */
    @Column(name = "NAME", nullable = false, length = 30)
    private String name;

    /**
     * 父级页面
     */
    @Column(name = "FATHER_ID")
    private Page father;

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
