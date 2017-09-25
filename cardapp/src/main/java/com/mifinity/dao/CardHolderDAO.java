package com.mifinity.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mifinity.model.CardHolderModel;

/**
 * DAO Card Holder
 * @author juliocesaradias
 */

public class CardHolderDAO {
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("users");
	private EntityManager em = factory.createEntityManager();
	
	@SuppressWarnings("unchecked")
	public List<CardHolderModel> getCardByNumber(String number) {
		List<CardHolderModel> cardHolderModelList = new ArrayList<CardHolderModel>();
		try {
			Query query = em.createQuery("FROM CardHolderModel WHERE cardNumber like :number"); 
			query.setParameter("number", "%" + number + "%"); 
			cardHolderModelList = query.getResultList();
			return cardHolderModelList;
		} catch (NoResultException e) {
			e.printStackTrace();
		    return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CardHolderModel> getCardByNumberAndUserId(String number, String userId) {
		List<CardHolderModel> cardHolderModelList = new ArrayList<CardHolderModel>();
		try {
			Query query = em.createQuery("FROM CardHolderModel WHERE cardNumber like :number and systemUserId = :userId"); 
			query.setParameter("number", "%" + number + "%");
			query.setParameter("userId", Long.valueOf(userId)); 
			cardHolderModelList = query.getResultList();
			return cardHolderModelList;
		} catch (NoResultException e) {
			e.printStackTrace();
		    return null;
		}
	}
	
	public boolean insertCardHolder(CardHolderModel cardHolderModel) {
	      try {
	    	  	    em.getTransaction().begin();
	            em.persist(cardHolderModel);
	            em.getTransaction().commit();
	            em.close();
	    			factory.close();
	            return true;
	      } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	      }
	}
	
	public boolean updateCardHolder(CardHolderModel cardHolderModel) {
		  try {
		  	    em.getTransaction().begin();
		        em.merge(cardHolderModel);
		        em.getTransaction().commit();
		        em.close();
				factory.close();
		        return true;
		  } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		  }
	}
}