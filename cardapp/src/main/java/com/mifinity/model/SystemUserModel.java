package com.mifinity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * System User Model
 * @author juliocesaradias
 */

@Entity
@Table(name="system_user")
public class SystemUserModel {
	
	@Id
    private long id;
    
    @Column(name="username", unique = true, nullable=false)
    private String username;
    
    @Column(name="password", unique = false, nullable=false)
    private String password;
    
    @Column(name="system_user_profile_id", unique = false, nullable=false)
    private long systemProfile;
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getSystemProfile() {
		return systemProfile;
	}

	public void setSystemProfile(long systemProfile) {
		this.systemProfile = systemProfile;
	}
}