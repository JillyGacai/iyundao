package com.ayundao.base;

import com.ayundao.base.utils.ClassUtils;
import com.ayundao.base.utils.TimeUtils;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.tree.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * Entity - 基类
 *
 * @author 念
 */
@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

    private static final long serialVersionUID = -67188388306700736L;

    /**
	 * "ID"属性名称
	 */
	public static final String ID_PROPERTY_NAME = "id";
    /**
	 * "创建日期"属性名称
	 */
	public static final String CREATED_DATE_PROPERTY_NAME = "createdDate";
    /**
	 * "最后修改日期"属性名称
	 */
	public static final String LAST_MODIFIED_DATE_PROPERTY_NAME = "lastModifiedDate";
    /**
	 * "版本"属性名称
	 */
	public static final String VERSION_PROPERTY_NAME = "version";
	@Transient
    protected final transient Log logger = LogFactory.getLog(this.getClass());
	/**
	 * ID
	 */
	@JsonView(BaseView.class)
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @GenericGenerator(name = "jpa-uuid",
            strategy = "uuid")
    @Column(name = "ID", length = 32)
	private ID id;
	/**
	 * 创建日期
	 */
	@Column(name = "CREATEDATE", nullable = false, updatable = false)
	private String createdDate;
	/**
	 * 最后修改日期
	 */
	@Column(name = "LASTMODIFIEDTIME", nullable = false)
	private String lastModifiedDate;
	/**
	 * 版本
	 */
	@Version
	@Column(name = "VERSION", nullable = false)
	private Long version;

	/**
	 * 获取ID
	 *
	 * @return ID
	 */
	public ID getId() {
		return id;
	}

	/**
	 * 设置ID
	 *
	 * @param id
	 *            ID
	 */
	private void setId(ID id) {
		this.id = id;
	}

	/**
	 * 获取创建日期
	 *
	 * @return 创建日期
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * 设置创建日期
	 *
	 * @param createdDate
	 *            创建日期
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = TimeUtils.setTime(createdDate);
	}

	/**
	 * 获取最后修改日期
	 *
	 * @return 最后修改日期
	 */
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * 设置最后修改日期
	 *
	 * @param lastModifiedDate
	 *            最后修改日期
	 */
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = TimeUtils.setTime(lastModifiedDate);
	}

	/**
	 * 获取版本
	 *
	 * @return 版本
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * 设置版本
	 *
	 * @param version
	 *            版本
	 */
	public void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * 判断是否为新建对象
	 *
	 * @return 是否为新建对象
	 */
	@Transient
	public boolean isNew() {
		return getId() == null;
	}

	/**
	 * 重写toString方法
	 *
	 * @return 字符串
	 */
	@Override
	public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        Iterator var3 = ClassUtils.getDeclaredFieldsWithSuper(this.getClass()).values().iterator();

        while(var3.hasNext()) {
            Field field = (Field)var3.next();
            Class<? extends Object> typeClazz = field.getType();
            if (!AbstractEntity.class.isAssignableFrom(typeClazz) && !Collection.class.isAssignableFrom(typeClazz) && !Map.class.isAssignableFrom(typeClazz)) {
                int modifiers = field.getModifiers();
                if (field.getName().indexOf(36) == -1 && !Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers)) {
                    builder.append(field.getName(), ClassUtils.forceGetProperty(this, field.getName()));
                }
            }
        }

        return builder.toString();
	}

	/**
	 * 重写equals方法
	 *
	 * @param obj
	 *            对象
	 * @return 是否相等
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!BaseEntity.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		BaseEntity<?> other = (BaseEntity<?>) obj;
		return getId() != null ? getId().equals(other.getId()) : false;
	}

	/**
	 * 重写hashCode方法
	 *
	 * @return HashCode
	 */
	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += getId() != null ? getId().hashCode() * 31 : 0;
		return hashCode;
	}

	/**
	 * 保存验证组
	 */
	public interface Save extends Default {

	}

	/**
	 * 更新验证组
	 */
	public interface Update extends Default {

	}

	/**
	 * 基础视图
	 */
	public interface BaseView {

	}

}