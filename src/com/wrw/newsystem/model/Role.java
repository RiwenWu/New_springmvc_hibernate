package com.wrw.newsystem.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;


/**
 * @author wrw
 * ��ɫ����
 * ���ڱ����ɫ��Ϣ���û��б���Զࣩ���ɫ��һ�Զࣩ��Ӧ��Ȩ��
 */

@Entity(name = "t_role")
public class Role {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer no;
	
	@Column(length = 100)
	private String name;
	
	@Column(length = 500)
	private String description;
	
	private Integer status;
	
	@Column(name = "code", length = 128)
	private String code;	
	
	@OneToMany(targetEntity = Permission.class, 
			cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "t_role_permission", 
		    joinColumns = @JoinColumn(name = "role_id"), 
		    inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private Set<Permission> permissionList = new HashSet<Permission>();			//ӵ��Ȩ��
	
	@Column(name="role_name", length = 64)
	private String roleName;					//shiro��name
	
	//PERSIST�����������й������½�����ʱ����       
	//MERGE�����ں����й�����������
	@ManyToMany(targetEntity = User.class, 
		    cascade = CascadeType.ALL)
    @JoinTable(name = "t_user_role",
    		joinColumns = { @JoinColumn(name = "role_id")},
    		inverseJoinColumns = { @JoinColumn(name = "user_id")})
	private Set<User> userList;	
	
	public Integer getNo() {
		return no;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public Set<Permission> getPermissionList() {
		return permissionList;
	}
	public void setPermissionList(Set<Permission> permissionList) {
		this.permissionList = permissionList;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public Set<User> getUserList() {
		return userList;
	}
	public void setUserList(Set<User> userList) {
		this.userList = userList;
	}
	
	
	@Transient
    public Set<String> getPermissionsName() {
        Set<String> list = new HashSet<String>();
        Set<Permission> perlist = getPermissionList();
        for (Permission per : perlist) {
            list.add(per.getPermissionname());
        }
        return list;
    }
	
}
