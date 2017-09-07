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
	    circle.setCircleName("stackroute");
		circle.setOwnerEmailId("india@gmail.con");
		circle.setStatus(true);
		assertEquals(true, circleDAO.addCircle(circle));
	}
	@Ignore
	@Test
	public void testToGetAllCircles()
	{
		List<Circle> circles=circleDAO.getAllCircles();
		assertEquals(2, circles.size());
		
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
		assertEquals(true, circleDAO.deleteCircle("niit","advik@gmail.com"));
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
