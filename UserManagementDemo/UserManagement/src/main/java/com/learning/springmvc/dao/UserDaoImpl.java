package com.learning.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.learning.springmvc.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public User findById(int id) {
		User user=getByKey(id);
		if(user!=null){
			Hibernate.initialize(user.getUserRoles());
		}
		return user;
	}

	public User findBySSO(String sso) {
		Criteria criteria=createEntityCriteria();
		criteria.add(Restrictions.eq("ssoId", sso));
		User user=(User) criteria.uniqueResult();
		if(user!=null){
			Hibernate.initialize(user.getUserRoles());
		}
		return user;
	}

	public void save(User user) {
		persist(user);
		
	}

	public void deleteBySSO(String sso) {
		Criteria criteria=createEntityCriteria();
		criteria.add(Restrictions.eq("ssoId", sso));
		User user=(User)criteria.uniqueResult();
		delete(user);
	}

	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<User> users = (List<User>) criteria.list();
         
        // No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
        // Uncomment below lines for eagerly fetching of userProfiles if you want.
        /*
        for(User user : users){
            Hibernate.initialize(user.getUserProfiles());
        }*/
        return users;
	}

}
