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
		if(!exists)
		{
		circle.setCircleName("stackroute");
		circle.setOwnerEmailId("india@gmail.con");
		circle.setStatus(true);
		assertEquals(true, circleDAO.addCircle(circle));
		}else
		{
			System.out.println("Circle Already Existx");
		}
	}
	@Ignore
	@Test
	public void testToGetAllCircles()
	{
		List<Circle> circles=circleDAO.getAllCircles();
		assertNotNull(circles);
		
	}
	@Ignore
	@Test
	public void testToDeleteCircle()
	{
		circle=circleDAO.getCircleByName("general");
		assertEquals(true, circleDAO.deleteCircle(circle.getCircleName(),circle.getOwnerEmailId()));
	}
	@Ignore
	@Test(expected=NullPointerException.class)
	public void testToDeleteCircleNotExists()
	{
		circle=circleDAO.getCircleByName("niit");
		assertEquals(true, circleDAO.deleteCircle(circle.getCircleName(),circle.getOwnerEmailId()));
	}
  @Ignore
  @Test
  public void testToUpdateCircle()
  {
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
