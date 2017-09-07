package com.stackroute.activitystream.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.activitystream.dao.UserCircleDAO;
import com.stackroute.activitystream.dao.UserDAO;
import com.stackroute.activitystream.model.Circle;
import com.stackroute.activitystream.model.User;
import com.stackroute.activitystream.model.UserCircle;

@Repository(value = "userCircleDAO")
@Transactional
public class UserCircleDAOImpl implements UserCircleDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserCircle userCircle;
	
	@Autowired
	private User user;
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public boolean addCircle(UserCircle circle) {

		try {
			sessionFactory.getCurrentSession().save(circle);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean addUserToCircle(String emailId, String circleName) {

		try {
			
			if(!isCircleExists(circleName) && !isSubscriberExists(emailId))
			{
			userCircle = new UserCircle();
			userCircle.setCircleName(circleName);
			userCircle.setSubscriberId(emailId);
			userCircle.setStatus(true);
			sessionFactory.getCurrentSession().save(userCircle);
			return true;
			}
			else
			{
				return false;
			}
		} catch (HibernateException e) {

			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean deleteUserFromCircle(String emailId, String circleName) {
		try {
			user=userDAO.getUserByEmailId(emailId);
			if(user!=null)
			{
			String hql="from UserCircle where subscriberId='"+emailId+"' and circleName='"+circleName+"'";
			
			Query query=sessionFactory.getCurrentSession().createQuery(hql);
			UserCircle userCircle=(UserCircle)query.uniqueResult();
			sessionFactory.getCurrentSession().delete(userCircle);
			return true;
			}
			else
			{
				return false;
			}
		} catch (HibernateException e) {

			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean deleteCircle(UserCircle circle) {

		try {
			if(isCircleExists(circle.getCircleName()))
			{
			circle.setStatus(false);
			sessionFactory.getCurrentSession().delete(circle);
			return true;
			}
			else
			{
				return false;
			}

		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}
	@Override
	public boolean updateCircle(UserCircle circle) {

		try {
			if(isCircleExists(circle.getCircleName()))
			{
			userCircle.setCircleName(circle.getCircleName());
			userCircle.setStatus(circle.isStatus());
			sessionFactory.getCurrentSession().update(userCircle);
			return true;
			}
			else
			{
				return false;
			}

		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserCircle> getAllCircles() {
		String hql = "from UserCircle where status=true";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserCircle> getCircleByUser(String ownerId) {
		
		if(isSubscriberExists(ownerId))
		{
		String sql = "from UserCircle where subscriberId='" + ownerId + "'";
		Query query=sessionFactory.getCurrentSession().createQuery(sql);
		List<UserCircle> userCircles=query.list();
		return userCircles;
		}else
		{
			return null;
		}
	}

	@Override
	public List<String> getUserByCircle(String circleName) {
		if(isCircleExists(circleName))
		{
		String sql = "from UserCircle where circleName='"+circleName+"'";
		@SuppressWarnings("unchecked")
		List<UserCircle> userCircle = sessionFactory.getCurrentSession().createQuery(sql).list();
		List<String> users = new ArrayList<>();
		for (UserCircle s : userCircle)
			users.add(s.getSubscriberId());
		return users;
		}
		else
		{
			return null;
		}
	}

	@Override
	public UserCircle getCircleByName(String circleName) {
		if(isCircleExists(circleName))
		{
        String hql="FROM UserCircle where circleName=?";
		return (UserCircle)sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, circleName).uniqueResult();
		}
		else
		{
			return null;
		}
	}

	@Override
	public boolean isCircleExists(String circleName) {
		String hql = "from UserCircle where circleName='"+circleName+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		userCircle  = (UserCircle) query.uniqueResult();
		if (userCircle != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean isSubscriberExists(String subscriber) {
		String hql="from UserCircle where subscriberId='"+subscriber+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		userCircle=(UserCircle)query.uniqueResult();
		if(userCircle !=null)
			return true;
		else
		return false;
	}

}
