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
import com.stackroute.activitystream.model.UserCircle;

@Repository(value = "userCircleDAO")
@Transactional
public class UserCircleDAOImpl implements UserCircleDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserCircle userCircle;

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
			userCircle = new UserCircle();
			userCircle.setCircleName(circleName);
			userCircle.setSubscriberId(emailId);
			userCircle.setStatus(true);
			sessionFactory.getCurrentSession().save(userCircle);
			return true;
		} catch (HibernateException e) {

			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean deleteUserFromCircle(String emailId, String circleName) {
		try {
			String hql="from UserCircle where subscriberId='"+emailId+"' and circleName='"+circleName+"'";
			
			Query query=sessionFactory.getCurrentSession().createQuery(hql);
			UserCircle userCircle=(UserCircle)query.uniqueResult();
			sessionFactory.getCurrentSession().delete(userCircle);
			return true;
		} catch (HibernateException e) {

			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean deleteCircle(UserCircle circle) {

		try {
			circle.setStatus(false);
			sessionFactory.getCurrentSession().delete(circle);
			return true;

		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}
	@Override
	public boolean updateCircle(UserCircle circle) {

		try {
			userCircle.setCircleName(circle.getCircleName());
			userCircle.setStatus(circle.isStatus());
			sessionFactory.getCurrentSession().update(userCircle);
			return true;

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
		String sql = "from UserCircle where subscriberId='" + ownerId + "'";
		return sessionFactory.getCurrentSession().createQuery(sql).list();
	}

	@Override
	public List<String> getUserByCircle(String circleName) {
		String sql = "from UserCircle where circleName='"+circleName+"'";
		@SuppressWarnings("unchecked")
		List<UserCircle> userCircle = sessionFactory.getCurrentSession().createQuery(sql).list();
		List<String> users = new ArrayList<>();
		for (UserCircle s : userCircle)
			users.add(s.getSubscriberId());
		return users;
	}

	@Override
	public UserCircle getCircleByName(String circleName) {
        String hql="FROM UserCircle where circleName=?";
		return (UserCircle)sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, circleName).uniqueResult();
	}

}
