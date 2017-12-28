package com.learning.springmvc.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.learning.springmvc.model.PersistentLogin;

@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenRepositoryImpl extends
		AbstractDao<String, PersistentLogin> implements
		PersistentTokenRepository {
	
	static final Logger logger = LoggerFactory.getLogger(HibernateTokenRepositoryImpl.class);
	static final String TAG=HibernateTokenRepositoryImpl.class.getSimpleName();

	public void createNewToken(PersistentRememberMeToken rememberMeToken) {
		logger.info(TAG+"/Creating Token for user : {}", rememberMeToken.getUsername());
		PersistentLogin persistentLogin=new PersistentLogin();
		persistentLogin.setUsername(rememberMeToken.getUsername());
		persistentLogin.setSeries(rememberMeToken.getSeries());
		persistentLogin.setToken(rememberMeToken.getTokenValue());
		persistentLogin.setLast_used(rememberMeToken.getDate());
		persist(persistentLogin);
	}

	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		logger.info(TAG+"/Fetch Token if any for seriesId : {}", seriesId);
		Criteria criteria=createEntityCriteria();
		criteria.add(Restrictions.eq("series", seriesId));
		PersistentLogin persistentLogin=(PersistentLogin) criteria.uniqueResult();
		return new PersistentRememberMeToken(persistentLogin.getUsername(), 
				persistentLogin.getSeries(), persistentLogin.getToken(), persistentLogin.getLast_used());
		
	}

	public void removeUserTokens(String userName) {
		logger.info(TAG+"/Removing Token if any for user : {}", userName);
		Criteria criteria=createEntityCriteria();
		criteria.add(Restrictions.eq("username", userName));
		PersistentLogin persistentLogin=(PersistentLogin) criteria.uniqueResult();
		if(persistentLogin!=null){
			logger.info(TAG+"/rememberMe was selected");
			delete(persistentLogin);
		}
	}

	public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
		logger.info(TAG+"/Updating Token for seriesId : {}", seriesId);
		PersistentLogin persistentLogin=getByKey(seriesId);
		persistentLogin.setSeries(seriesId);
		persistentLogin.setToken(tokenValue);
		persistentLogin.setLast_used(lastUsed);
		update(persistentLogin);
	}

}
