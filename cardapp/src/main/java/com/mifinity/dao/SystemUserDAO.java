package com.mifinity.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mifinity.model.SystemUserModel;

/**
 * DAO System User
 * @author juliocesaradias
 */
public class SystemUserDAO {
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("users");
	private EntityManager em = factory.createEntityManager();
	
	@SuppressWarnings("unchecked")
	public List<SystemUserModel> getSystemUserByName(String userName) {
		List<SystemUserModel> systemUserModelList = new ArrayList<SystemUserModel>();
		try {
			Query query = em.createQuery("SELECT u.id FROM SystemUserModel as u WHERE username = :user"); 
			query.setParameter("user", userName); 
			systemUserModelList = query.getResultList();
			return systemUserModelList;
		} catch (NoResultException e) {
			e.printStackTrace();
		    return null;
		}
	}
}