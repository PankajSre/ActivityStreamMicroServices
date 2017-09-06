package com.stackroute.activitystream.dao;

import java.util.List;
import com.stackroute.activitystream.model.UserCircle;

public interface UserCircleDAO {

	boolean addCircle(UserCircle circle);
	boolean addUserToCircle(String emailId,String circleName);
	boolean deleteUserFromCircle(String emailId,String circleName);
	boolean deleteCircle(UserCircle circle);
	List<UserCircle> getAllCircles();
	List<UserCircle> getCircleByUser(String ownerId);
	List<String> getUserByCircle(String circleName);
	public UserCircle getCircleByName(String circleName);
	boolean updateCircle(UserCircle userCircle);
	public boolean isCircleExists(String circleName);
	public boolean isSubscriberExists(String subscriber);
}
