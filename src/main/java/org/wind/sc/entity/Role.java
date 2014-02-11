package org.wind.sc.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.collect.ImmutableList;


@Entity
@Table(name="t_role")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Role extends IdEntity{

	private String name;
	private String permissions; 
	
	public Role(){
	}
	
	public Role(Long id){
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
	@Transient
	public List<String> getPermissionList(){
		return ImmutableList.copyOf(StringUtils.split(this.permissions,","));
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
