package org.wind.sc.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springside.modules.utils.Collections3;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

@Entity
@Table(name = "t_user")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class User extends IdEntity{
	
	@NotBlank
	private String loginName;
	@NotBlank
	private String name;
	private String password;
	private String salt;
	@Email
	private String email;
	private String status;
	@Transient
	private String plainPassword;

	@ManyToMany
	@JoinTable(name = "t_user_role",joinColumns = {@JoinColumn(name = "user_id")},inverseJoinColumns = {@JoinColumn(name = "role_id")})
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id ASC")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Role> roleList = Lists.newArrayList();//有序的对象集合
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	public User() {
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<Role> getRoleList() {
		return roleList;
	}


	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}


	public Team getTeam() {
		return team;
	}


	public void setTeam(Team team) {
		this.team = team;
	}
	
	@Transient
	@JsonIgnore
	public String getRoleNames() {
		return Collections3.extractToString(roleList, "name", ", ");
	}
	
	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}
	

	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	
}
