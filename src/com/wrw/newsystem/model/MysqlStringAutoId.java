package com.wrw.newsystem.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author wrw
 *MySql���ݿ��Զ�����32λ String��������
 */
@MappedSuperclass
public class MysqlStringAutoId {
	
	protected String id;

	@Id  
    @GeneratedValue(generator="system-uuid")  
    @GenericGenerator(name = "system-uuid",strategy="uuid")  
    @Column(length=32) 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
