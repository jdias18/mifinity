package com.mifinity.controller;

import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.context.FacesContext;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.mifinity.bean.CardMB;
import com.mifinity.dao.CardHolderDAO;
import com.mifinity.dao.SystemUserDAO;
import com.mifinity.model.CardHolderModel;
import com.mifinity.model.SystemUserModel;

/**
 * Abstract class for general functions.
 * @author juliocesaradias
 */

public class AbstractCardController {
	
	private CardMB cardMB;
	
	public AbstractCardController() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    ELContext elContext = facesContext.getELContext();
	    ExpressionFactory factory = facesContext.getApplication().getExpressionFactory();
		this.cardMB = (CardMB)factory.createValueExpression(elContext, "#{CardMB}", Object.class).getValue(elContext);
	}
	
	public List<CardHolderModel> searchCard(){
		
		SystemUserDAO systemUserDAO = new SystemUserDAO();
		List<SystemUserModel> systemUserList = systemUserDAO.getSystemUserByName(getLoggedUserName());
		
		if(!systemUserList.isEmpty() && systemUserList.get(0) != null) {
			CardHolderDAO cardHolderDAO = new CardHolderDAO();
			String userId = String.valueOf(systemUserList.get(0)).trim();
			return cardHolderDAO.getCardByNumberAndUserId(cardMB.getCardNumber(), userId);
			
		} else {
			CardHolderDAO cardHolderDAO = new CardHolderDAO();
			return cardHolderDAO.getCardByNumber(cardMB.getCardNumber());
		}
	}
	
	public String getLoggedUserName() {
		Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		
		if (loggedUser instanceof UserDetails) {
		   username= ((UserDetails)loggedUser).getUsername();
		} else {
		   username = loggedUser.toString();
		}
		return username;
	}
	
	public String getLoggedUserId() {
		SystemUserDAO systemUserDAO = new SystemUserDAO();
		List<SystemUserModel> systemUserList = systemUserDAO.getSystemUserByName(getLoggedUserName());
		
		if(!systemUserList.isEmpty() && systemUserList.get(0) != null) {
			String userId = String.valueOf(systemUserList.get(0)).trim();
			return userId;
		} else {
			return "0";
		}
	}
}
