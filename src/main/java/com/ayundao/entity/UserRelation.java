package com.ayundao.entity;

import com.ayundao.base.BaseEntity;

import javax.persistence.*;
import java.util.UUID;

/**
 * @ClassName: UserRelation
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/16 14:31
 * @Description: 用户与机构实体
 * @Version: V1.0
 */
@Entity
@Table(name = "t_user_relations")
@SecondaryTables({
        @SecondaryTable(name="t_subject", pkJoinColumns = {
                @PrimaryKeyJoinColumn(name = "SUBJECTID")
        }),
        @SecondaryTable(name="t_depart", pkJoinColumns = {
                @PrimaryKeyJoinColumn(name = "DEPARTID")
        }),
        @SecondaryTable(name="t_groups", pkJoinColumns = {
                @PrimaryKeyJoinColumn(name = "GROUPSID")
        }),
        @SecondaryTable(name="t_user", pkJoinColumns = {
                @PrimaryKeyJoinColumn(name = "USERID")
        })
})
public class UserRelation extends BaseEntity<UUID> {

    private static final long serialVersionUID = -1293749127349L;

    /**
     * 所属机构
     */
    @Column(name = "SUBJECTID")
    private Subject subjects;

    /**
     * 所属部门
     */
    @Column(name = "DEPARTID")
    private Depart depart;

    /**
     * 所属小组
     */
    @Column(name = "GROUPSID")
    private Groups groups;

    /**
     * 用户
     */
    @Column(name = "USERID")
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
