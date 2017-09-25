package com.mifinity.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.context.FacesContext;

import com.mifinity.bean.CardMB;
import com.mifinity.dao.CardHolderDAO;
import com.mifinity.model.CardHolderModel;
import com.mifinity.util.MifinityUtils;

/**
 * Controller Manage Screen
 * @author juliocesaradias
 */

public class ManageCardController extends AbstractCardController {
	
	private CardMB createCardMB;
	
	public ManageCardController() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    ELContext elContext = facesContext.getELContext();
	    ExpressionFactory factory = facesContext.getApplication().getExpressionFactory();
		this.createCardMB = (CardMB)factory.createValueExpression(elContext, "#{CardMB}", Object.class).getValue(elContext);
	}
	
	public void add() {
		CardHolderModel cardHolderModel = new CardHolderModel();
		cardHolderModel.setCardNumber(createCardMB.getCardNumber());
		cardHolderModel.setName(createCardMB.getName());
		cardHolderModel.setExp(MifinityUtils.formatDate(createCardMB.getExp()));
		cardHolderModel.setSystemUserId(Long.valueOf(this.getLoggedUserId()));
		CardHolderDAO cardHolderDAO = new CardHolderDAO();
		cardHolderDAO.insertCardHolder(cardHolderModel);
	}
	
	public void update(CardHolderModel cardHolderModel) {
		cardHolderModel.setCardNumber(cardHolderModel.getCardNumber());
		cardHolderModel.setName(createCardMB.getName());
		cardHolderModel.setExp(MifinityUtils.stringToCalendar(createCardMB.getExp().concat("/01")));
		cardHolderModel.setSystemUserId(Long.valueOf(this.getLoggedUserId()));
		CardHolderDAO cardHolderDAO = new CardHolderDAO();
		cardHolderDAO.updateCardHolder(cardHolderModel);
	} 
	
	public Calendar formatDate(String dateToConvert) {
		Calendar cal = Calendar.getInstance();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("YY/MM");
			cal.setTime(sdf.parse(dateToConvert));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cal;
	}
}
