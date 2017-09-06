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

import com.stackroute.activitystream.dao.UserCircleDAO;
import com.stackroute.activitystream.main.ActivityStreamBackEnd;
import com.stackroute.activitystream.model.UserCircle;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ActivityStreamBackEnd.class)
@EnableAspectJAutoProxy
public class UserCircleJUnitTest {

	@Autowired
	private UserCircle userCircle;

	@Autowired
	private UserCircleDAO userCircleDAO;
	
	@Ignore
	@Test
	public void add_user_to_circle_success()
	{
		userCircle.setSubscriberId("deepak@gmail.com");
		userCircle.setCircleName("stackroute");
		assertEquals(true, userCircleDAO.addUserToCircle(userCircle.getSubscriberId(), userCircle.getCircleName()));
	}
	@Ignore
	@Test
	public void add_user_to_circle_failure()
	{
		userCircle.setSubscriberId("deepak@gmail.com");
		userCircle.setCircleName("stackroute");
		assertEquals(false, userCircleDAO.addUserToCircle(userCircle.getSubscriberId(), userCircle.getCircleName()));
	}
	@Ignore
	@Test
	public void delete_user_from_circle_success()
	{
		userCircle.setSubscriberId("deepak@gmail.com");
		userCircle.setCircleName("stackroute");
		assertEquals(true, userCircleDAO.deleteUserFromCircle(userCircle.getSubscriberId(), userCircle.getCircleName()));
	}
	@Ignore
	@Test(expected=Exception.class)
	public void delete_user_from_circle_failure()
	{
		userCircle.setSubscriberId("deepak@gmail.com");
		userCircle.setCircleName("stackroute");//Circle Does not Exists
		assertEquals(true, userCircleDAO.deleteUserFromCircle(userCircle.getSubscriberId(), userCircle.getCircleName()));
	}
	
	@Ignore
	@Test
	public void delete_circle_success()
	{
		userCircle.setCircleName("stackroute");
		assertEquals(true, userCircleDAO.deleteCircle(userCircle));
	}
	@Ignore
	@Test(expected=Exception.class)
	public void delete_circle_failure()
	{
		userCircle.setCircleName("stackroute");
		assertEquals(true, userCircleDAO.deleteCircle(userCircle));
	}
	@Ignore
	@Test
	public void get_all_circles_success()
	{
		List<UserCircle> userCircles=userCircleDAO.getAllCircles();
		assertEquals(4, userCircles.size());
	}
	
	@Ignore
	@Test
	public void get_circle_by_ownerId_success()
	{
		userCircle.setSubscriberId("advik@gmail.com");
		assertEquals(2, userCircleDAO.getCircleByUser(userCircle.getSubscriberId()).size());
	}
	@Ignore
	@Test(expected=Exception.class)
	public void get_circle_by_ownerId_failure()
	{
		userCircle.setSubscriberId("advik1@gmail.com");
		assertEquals(2, userCircleDAO.getCircleByUser(userCircle.getSubscriberId()).size());
	}
	
    @Ignore
	@Test
	public void get_users_by_circle_name_success()
	{
		userCircle.setCircleName("general");
		assertEquals(1, userCircleDAO.getUserByCircle(userCircle.getCircleName()).size());
	}
    @Ignore
	@Test(expected=Exception.class)
	public void get_users_by_circle_name_failure()
	{
		userCircle.setCircleName("test");
		assertEquals(1, userCircleDAO.getUserByCircle(userCircle.getCircleName()).size());
	}
	@Ignore
	@Test
	public void get__circle_name_success()
	{
		userCircle.setCircleName("general");
		assertEquals("advik@gmail.com", userCircleDAO.getCircleByName(userCircle.getCircleName()).getSubscriberId());
	}
	@Ignore
	@Test(expected=Exception.class)
	public void get__circle_name_failure()
	{
		userCircle.setCircleName("test");
		assertEquals("advik@gmail.com", userCircleDAO.getCircleByName(userCircle.getCircleName()).getSubscriberId());
	}
	@Ignore
	@Test
	public void update_user_circle_success()
	{
		userCircle.setCircleName("Hadoop");
		UserCircle circle = userCircleDAO.getCircleByName(userCircle.getCircleName());
		circle.setStatus(false);
		assertEquals(true,userCircleDAO.updateCircle(circle));
	}
	@Ignore
	@Test(expected=NullPointerException.class)
	public void update_user_circle_failure()
	{
		userCircle.setCircleName("test");
		UserCircle circle = userCircleDAO.getCircleByName(userCircle.getCircleName());
		circle.setStatus(false);
		assertEquals(true,userCircleDAO.updateCircle(circle));
	}
}
