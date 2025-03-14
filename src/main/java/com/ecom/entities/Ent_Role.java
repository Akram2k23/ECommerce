package com.ecom.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Table(name = "roles")
@Entity
public class Ent_Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private long rollId;
	
	@Column(name = "role_name")
	private String roleName;
	
	@ManyToMany(mappedBy = "roles")
	private Set<Ent_User> users = new HashSet<>();
	
	
	public Set<Ent_User> getUsers() {
		return users;
	}

	public void setUsers(Set<Ent_User> users) {
		this.users = users;
	}

	public Ent_Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ent_Role(long rollId, String roleName) {
		super();
		this.rollId = rollId;
		this.roleName = roleName;
	}

	public long getRollId() {
		return rollId;
	}

	public void setRollId(long rollId) {
		this.rollId = rollId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Role [rollId=" + rollId + ", roleName=" + roleName + "]";
	}

}
