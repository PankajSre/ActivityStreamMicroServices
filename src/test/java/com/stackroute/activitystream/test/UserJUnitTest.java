package com.stackroute.activitystream.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.activitystream.dao.UserDAO;
import com.stackroute.activitystream.main.ActivityStreamBackEnd;
import com.stackroute.activitystream.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,classes=ActivityStreamBackEnd.class)
@EnableAspectJAutoProxy
public class UserJUnitTest {

	@Autowired
	private  User user;
	@Autowired
	private  UserDAO userDAO;
	
	@Ignore
	@Test
	public void testSaveUser()
	{
		user.setUsername("raman");
		user.setPassword("raman");
		user.setEmailId("raman@gmail.com");
		user.setMobileNumber(800724072L);
		user.setActive(true);
		assertEquals(true, userDAO.saveUser(user));
	}
	@Ignore
	@Test(expected=Exception.class)
	public void testDuplicateSaveUser()
	{
		user.setUsername("raman");
		user.setPassword("raman");
		user.setEmailId("raman@gmail.com");
		user.setMobileNumber(800724072L);
		user.setActive(true);
		assertEquals(false, userDAO.saveUser(user));
	}
//*****************************User Login Test cases*****************************
   @Ignore
	@Test
	public void testValidateUser()
	{
		//why to set the parameters to user?  Directly you can send mailid and password to validateUser method
		//The same is there in other test cases.
	   user.setEmailId("raman@gmail.com");
	   user.setPassword("raman");
		boolean validatedUser=userDAO.validateUser(user.getEmailId(),user.getPassword());
		assertNotNull(validatedUser);
	}
   @Ignore
    @Test
   	public void testValidateUserByWrongCredentials()
   	{
	   
	   user.setEmailId("raman@gmail.com");
	   user.setPassword("raman@123");
		boolean validatedUser=userDAO.validateUser(user.getEmailId(),user.getPassword());
   		assertNull(validatedUser);
   	}
    @Ignore
    @Test
   	public void testValidateUserByNullUsername()
   	{
   		boolean validatedUser=userDAO.validateUser("", "raman");
   		assertEquals(false,validatedUser);
   	}
   @Ignore
    @Test
   	public void testValidateUserByNullPassword()
   	{
	   user.setEmailId("raman@gmail.com");
   		boolean validatedUser=userDAO.validateUser(user.getEmailId(), "");
   		assertEquals(false,validatedUser);
   	}
   @Ignore
    @Test
   	public void testValidateUserByUppercaseUsername()
   	{
	   user.setEmailId("RAMAN@GMAIL.COM");
	   user.setPassword("raman");
	   boolean validatedUser=userDAO.validateUser(user.getEmailId(),user.getPassword());
   		assertNotNull(validatedUser);
   	}
   @Ignore
    @Test
   	public void testValidateUserByUppercasePassword()
   	{
	   user.setEmailId("raman@gmail.com");
	   user.setPassword("RAMAN");
	   boolean validatedUser=userDAO.validateUser(user.getEmailId(),user.getPassword());
   		assertNotNull(validatedUser);
   	}
  //*****************************User Login Test cases End*****************************
    @Ignore
	@Test
	public void testGetUserByEmailId()
	{
		user.setEmailId("deepak@gmail.com");
		User userById=userDAO.getUserByEmailId(user.getEmailId());
		assertNotNull(userById);
		System.out.println("User Email ID :"+userById.getEmailId());		
	}
	@Ignore
	@Test
	public void testDeleteUser()
	{
		user.setEmailId("deepak@gmail.com");
		User deletedUser=userDAO.getUserByEmailId(user.getEmailId());
		assertEquals(true, userDAO.deleteUser(deletedUser));
	}
	@Ignore
	@Test
	public void testUpdateUser()
	{
		user.setEmailId("ram@gmail.com");
		User updatedUser=userDAO.getUserByEmailId(user.getEmailId());
		assertNotNull(updatedUser);
		//email id should not be updatable as it is primary key.
		updatedUser.setEmailId("Pankaj.saini85@gmail.com");
		assertEquals(true, userDAO.updateUser(updatedUser));
	}
   @Ignore
	@Test
	public void testForGetAllUsers()
	{
		List<User> userList=userDAO.getAllUsers();
		
	
		assertNotNull(userList);
	        //user assert statements instead of loop and SOP.
		for(User userData : userList)
		{
			System.out.println(userData.getUsername()+"  :  "+userData.getMobileNumber());
		}
	}

}
