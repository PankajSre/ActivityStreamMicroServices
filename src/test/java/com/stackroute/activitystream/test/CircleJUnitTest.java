package com.stackroute.activitystream.test;

import static org.junit.Assert.*;


import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.activitystream.dao.CircleDAO;
import com.stackroute.activitystream.main.ActivityStreamBackEnd;
import com.stackroute.activitystream.model.Circle;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,classes=ActivityStreamBackEnd.class)
@EnableAspectJAutoProxy
public class CircleJUnitTest {
    
	@Autowired
	private CircleDAO circleDAO;
	@Autowired
	private Circle circle;
	
   @Ignore
	@Test
	public void addToCircle()
	{
		boolean exists=circleDAO.isCircleExists("stackroute");
		//avoid using 'if' conditions in JUnitTest cases.
		//You can use assert statements you want.
		//addCircle method should return true/false based on circle exist or not.
		//You should not test .
		if(!exists)
		{
		circle.setCircleName("stackroute");
		circle.setOwnerEmailId("india@gmail.con");
		circle.setStatus(true);
		assertEquals(true, circleDAO.addCircle(circle));
		}
		//what is the use of this else sattement?  Avoid SOPs
		else
		{
			System.out.println("Circle Already Existx");
		}
	}
	@Ignore
	@Test
	public void testToGetAllCircles()
	{
		//elaborate the test case.
		//you can test number of circles.
		//circle name of the particular circle etc.,
		List<Circle> circles=circleDAO.getAllCircles();
		assertNotNull(circles);
		
	}
	@Ignore
	@Test
	public void testToDeleteCircle()
	{
		//what will happen if the circle is not created by me?
		circle=circleDAO.getCircleByName("general");
		//deleteCircle method should take two parameters(circle name and created by
		assertEquals(true, circleDAO.deleteCircle(circle.getCircleName(),circle.getOwnerEmailId()));
	}
	@Ignore
	@Test(expected=NullPointerException.class)
	public void testToDeleteCircleNotExists()
	{
		//this method itself return null/empty. then why you want to call deleteCircle
		//You should call directly deleteCircle
		circle=circleDAO.getCircleByName("niit");
		
		assertEquals(true, circleDAO.deleteCircle(circle.getCircleName(),circle.getOwnerEmailId()));
	}
  @Ignore
  @Test
  public void testToUpdateCircle()
  {
	 //avoid updating circle name as it is referring in UserCircle table.
	  //else you need to update all the references in USserCircle
	  circle.setCircleName("general");
	  Circle updatableCircle=circleDAO.getCircleByName(circle.getCircleName());
	  updatableCircle.setStatus(false);
	  assertEquals(true, circleDAO.updateCircle(updatableCircle));
	  
	  
  }

  @Test(expected=NullPointerException.class)
  public void testToUpdateNotExistingCircle()
  {
	  circle.setCircleName("niit");
	  Circle updatableCircle=circleDAO.getCircleByName(circle.getCircleName());
	  updatableCircle.setStatus(false);
	  assertEquals(true, circleDAO.updateCircle(updatableCircle));
	  
	  
  }
}
