package com.wrw.newsystem.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

/**
 * @author wrw
 * 定义实体的基础公共属性，所有实体都会继承.
 */

@MappedSuperclass
public class BaseModel extends MysqlStringAutoId implements Serializable{

	private static final long serialVersionUID = -4498233384948128317L;
	
	//逻辑删除标识位—已删除状态
	public static final String DELETE_FLAG_DELETED = "1";
		
	//逻辑删除标识位—未删除状态
	public static final String DELETE_FLAG_NORMAL = "0";
	
	/**
	 * 创建日期
	 * 不可修改
	 */
	@Column(name = "create_date", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createDate;
	/**
	 * 修改日期
	 */
	@Column(name = "update_date")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date updateDate;
	/**
	 * 删除标志(0-正常，1-删除)
	 * @return
	 */
	@Column(name = "delete_flag",length=1)
	protected String deleteFlag;
	
	@PrePersist
    public void prePersist() {
		if(this.createDate == null){
			this.setCreateDate(new Date());
		}
		this.setUpdateDate(new Date());
		if(StringUtils.isBlank(this.getDeleteFlag())){
			this.setDeleteFlag(BaseModel.DELETE_FLAG_NORMAL);	
		}
    }
 
    @PreUpdate
    public void preUpdate() {
    	this.setUpdateDate(new Date());
    }

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
    
    
}
