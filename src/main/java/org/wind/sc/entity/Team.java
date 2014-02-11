package org.wind.sc.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.collect.Lists;

@Entity
@Table(name="t_team")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Team extends IdEntity{
	
	private String name;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "master_id")
	private User master;
	
	@OneToMany(mappedBy = "team")
	private List<User> userList = Lists.newArrayList();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getMaster() {
		return master;
	}
	public void setMaster(User master) {
		this.master = master;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
