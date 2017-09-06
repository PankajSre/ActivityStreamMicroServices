package com.stackroute.activitystream.dao;

import java.util.List;

import com.stackroute.activitystream.model.Circle;

public interface CircleDAO {

	boolean addCircle(Circle circle);
	//This method should be in UserCircleDAO.  In circle table/model emailId should not be there.
	boolean addUserToCircle(String emailId,String circleName);
	List<Circle> getAllCircles();
	boolean deleteCircle(String circleName,String ownerId);
	boolean updateCircle(Circle circle);
	public Circle getCircleByName(String circleName);
	
	//This method is not required as getCircleByName is already there.
	public boolean isCircleExists(String circleName);//Remove It
}
