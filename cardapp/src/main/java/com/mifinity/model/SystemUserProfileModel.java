package com.mifinity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * System User Profile Model
 * @author juliocesaradias
 */

@Entity
@Table(name="system_user_profile")
public class SystemUserProfileModel {
	
	@Id
    private long id;
    
    @Column(name="description", unique = false, nullable=false)
    private String description;
    
    @Column(name="system_user_profile", unique = false, nullable=false)
    private String profileType;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProfileType() {
		return profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}
}