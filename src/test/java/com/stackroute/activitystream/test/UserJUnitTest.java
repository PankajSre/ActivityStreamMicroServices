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
		boolean validatedUser=userDAO.validateUser("raman@gmail.com","raman");
		assertNotNull(validatedUser);
	}
   @Ignore
    @Test
   	public void testValidateUserByWrongCredentials()
   	{
		boolean validatedUser=userDAO.validateUser("raman@gmail.com","raman@123");
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
	   
   		boolean validatedUser=userDAO.validateUser("raman@gmail.com", "");
   		assertEquals(false,validatedUser);
   	}
   @Ignore
    @Test
   	public void testValidateUserByUppercaseUsername()
   	{
	   boolean validatedUser=userDAO.validateUser("RAMAN@GMAIL.COM","raman");
   		assertNotNull(validatedUser);
   	}
   @Ignore
    @Test
   	public void testValidateUserByUppercasePassword()
   	{
	   boolean validatedUser=userDAO.validateUser("raman@gmail.com","RAMAN");
   		assertNotNull(validatedUser);
   	}
  //*****************************User Login Test cases End*****************************
    @Ignore
	@Test
	public void testGetUserByEmailId()
	{
		User userById=userDAO.getUserByEmailId("deepak@gmail.com");
		assertNotNull(userById);
		System.out.println("User Email ID :"+userById.getEmailId());		
	}
	@Ignore
	@Test
	public void testDeleteUser()
	{
		User deletedUser=userDAO.getUserByEmailId("deepak@gmail.com");
		assertEquals(true, userDAO.deleteUser(deletedUser));
	}
	@Ignore
	@Test
	public void testUpdateUser()
	{
		User updatedUser=userDAO.getUserByEmailId("ram@gmail.com");
		assertNotNull(updatedUser);
		updatedUser.setActive(false);
		assertEquals(true, userDAO.updateUser(updatedUser));
	}
    @Ignore
	@Test
	public void testForGetAllUsers()
	{
		List<User> userList=userDAO.getAllUsers();
		assertNotNull(userList);
		assertEquals(5, userList.size());
	}

}
