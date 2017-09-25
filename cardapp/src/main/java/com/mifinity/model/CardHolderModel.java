package com.mifinity.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Card Holder Model
 * @author juliocesaradias
 */

@Entity
@Table(name="card_holder")
public class CardHolderModel {
	
	@Id
	@GeneratedValue
    private long id;
    
	@Column(name = "card_holder_name", nullable = false, unique = false)
    private String name;
	
	@Column(name = "card_number", unique = true, nullable = false)
    private String cardNumber;
    
	@Column(name = "exp_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar expDate;
	
	@Column(name = "system_user_id", unique = false, nullable = false)
    private Long systemUserId;
    
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getExp() {
		return expDate.getTime();
	}

	public void setExp(Calendar exp) {
		this.expDate = exp;
	}

	public Calendar getExpDate() {
		return expDate;
	}

	public void setExpDate(Calendar expDate) {
		this.expDate = expDate;
	}

	public Long getSystemUserId() {
		return systemUserId;
	}

	public void setSystemUserId(Long systemUserId) {
		this.systemUserId = systemUserId;
	}
}