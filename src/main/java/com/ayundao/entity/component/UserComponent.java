package com.ayundao.entity.component;

import javax.persistence.Embeddable;

/**
 * @ClassName: UserComponent
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/15 17:03
 * @Description: 用户组件
 * @Version: V1.0
 */
@Embeddable
public class UserComponent {

    private String ID;

    private String name;

    public UserComponent(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
