package com.mifinity.bean;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.mifinity.controller.AbstractCardController;
import com.mifinity.controller.ManageCardController;
import com.mifinity.messages.MifinityConstants;
import com.mifinity.model.CardHolderModel;
import com.mifinity.util.CCUtils;
import com.mifinity.util.MifinityUtils;

/**
 * CardMB that represents card details and functions.
 * @author juliocesaradias
 */

@ManagedBean(name = "CardMB", eager = true)
@RequestScoped
public class CardMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String cardNumber;
	private String exp;
	
	private List<CardHolderModel> resultList;
	
	public CardMB() {
		resultList = new ArrayList<>();
	}
	
	public void save() {
		if(MifinityUtils.isNotEmptyOrNull(this.cardNumber)) {
			try {
				if(CCUtils.validCC(this.cardNumber)) {
					ManageCardController controller = new ManageCardController();
					this.resultList = controller.searchCard();
					if(this.resultList.isEmpty()) {
						controller.add();
						FacesContext.getCurrentInstance().addMessage(FacesMessage.SEVERITY_INFO.toString(), new FacesMessage(MifinityConstants.CARD_REGISTERED_SUCCESSFULLY));
					} else {
						controller.update(this.resultList.get(0));
						FacesContext.getCurrentInstance().addMessage(FacesMessage.SEVERITY_INFO.toString(), new FacesMessage(MifinityConstants.CARD_UPDATED_SUCCESSFULLY));
					}
				} else {
					FacesContext.getCurrentInstance().addMessage(FacesMessage.SEVERITY_ERROR.toString(), new FacesMessage(MifinityConstants.PLEASE_VERIFY_CARD_NUMBER));
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(FacesMessage.SEVERITY_ERROR.toString(), new FacesMessage(MifinityConstants.PLEASE_VERIFY_CARD_NUMBER));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(FacesMessage.SEVERITY_ERROR.toString(), new FacesMessage(MifinityConstants.PLEASE_INSERT_CARD_NUMBER));
			return;
		}
	}
	
	public void searchCard() {
		if(MifinityUtils.isNotEmptyOrNull(this.cardNumber)) {
			try {
				AbstractCardController controller = new AbstractCardController();
				this.resultList = controller.searchCard();
			} catch (Exception e) {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(FacesMessage.SEVERITY_ERROR.toString(), new FacesMessage(MifinityConstants.PLEASE_VERIFY_CARD_NUMBER));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(FacesMessage.SEVERITY_ERROR.toString(), new FacesMessage(MifinityConstants.PLEASE_INSERT_CARD_NUMBER));
			return;
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public List<CardHolderModel> getResultList() {
		return resultList;
	}

	public void setResultList(List<CardHolderModel> resultList) {
		this.resultList = resultList;
	}
}