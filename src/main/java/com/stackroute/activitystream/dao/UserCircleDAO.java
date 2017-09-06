package com.stackroute.activitystream.dao;

import java.util.List;
import com.stackroute.activitystream.model.UserCircle;

public interface UserCircleDAO {

	//What is the use of this method.  A new row should be created when a user added to a circle.
	boolean addCircle(UserCircle circle);
	boolean addUserToCircle(String emailId,String circleName);
	boolean deleteUserFromCircle(String emailId,String circleName);
	
	//UserCircle should not be deleted.  We can unsubscribe.
	boolean deleteCircle(UserCircle circle);
	//What is the use of this method?  We can get all circles from CircleDAO itself(wihtout duplicate)
	//InUserCircle, Circles may be duplicated.
	List<UserCircle> getAllCircles();
	
	//What is this ownerID?  ownerId should be there in circle table. not in usercircle table.
	List<UserCircle> getCircleByUser(String ownerId);
	//method name should be getUsersByCirle OR getCircleUsers.  It should not be singular
	List<String> getUserByCircle(String circleName);
	
	//In the UserCircle table, circleName will be repeated(not primary key).  You won't get single row/value.
	//If you want to get Circle details by circle name, you can get from CircleDAO.
	public UserCircle getCircleByName(String circleName);
	
	//What this method will do. Update UserCircle means unsubscribe to the cicle.  
	boolean updateCircle(UserCircle userCircle);
	
	//Thie method should be in CircleDAO.
	public boolean isCircleExists(String circleName);
	
	//User can subcribe to multiple Circles.  This method will return if user subscribe alteast one circle.
	//If you want to find whether user subscribed to particular circle or not,  you need to send 2 parameters
	//i.e., subscriber id and circle name
	public boolean isSubscriberExists(String subscriber);
}
