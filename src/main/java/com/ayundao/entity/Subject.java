package com.ayundao.entity;

import com.ayundao.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * @ClassName: Subject
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/16 14:32
 * @Description: 机构实体
 * @Version: V1.0
 */
@Entity
@Table(name = "t_subject")
public class Subject extends BaseEntity<UUID> {

    private static final long serialVersionUID = -129079014789123784L;

    /**
     * 名称
     */
    @Column(name = "NAME", nullable = false, length = 30)
    private String name;

    /**
     * 机构类型
     */
    @Column(name = "SUBJECT_TYPE")
    private SUBJECT_TYPE subjectType;

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

    public enum SUBJECT_TYPE{
        /**
         * 总院
         */
        head,

        /**
         * 分院
         */
        part,

        /**
         * 其他
         */
        etc
    }

}
