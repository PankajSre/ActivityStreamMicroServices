package com.stackroute.activitystream.test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.activitystream.dao.UserMessageDAO;
import com.stackroute.activitystream.main.ActivityStreamBackEnd;
import com.stackroute.activitystream.model.Message;
import com.stackroute.activitystream.model.UserMessage;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,classes=ActivityStreamBackEnd.class)
@EnableAspectJAutoProxy
public class UserMessageJUnitTest {

	@Autowired
	private UserMessage userMessage;
	
	@Autowired
	private UserMessageDAO userMessageDAO;
	
	@Ignore
	@Test
	public void test_to_send_user_message_success()
	{
		userMessage.setMessageId(9);
		userMessage.setMaximumSize(1000000);
		userMessage.setMessageText("Youa are looking Good!");
		userMessage.setMessageSize(userMessage.getMessageText().length());
		userMessage.setMessageType("Text");
		userMessage.setSentDate(new Date());
		userMessage.setSenderEmailId("advik@gmail.com");
		userMessage.setReceiverEmailId("deepak@gmail.com");
		assertEquals(true, userMessageDAO.sendMessage(userMessage));
	}
	@Ignore
	@Test
	public void test_to_send_user_message_faliure()
	{
		userMessage.setMessageId(9);
		userMessage.setMaximumSize(1000000);
		userMessage.setMessageText("Youa are looking Good!");
		userMessage.setMessageSize(userMessage.getMessageText().length());
		userMessage.setMessageType("Text");
		userMessage.setSentDate(new Date());
		userMessage.setSenderEmailId("advik@gmail.com");
		userMessage.setReceiverEmailId("deepak1@gmail.com");//Reciever Email Id does not exists
		assertEquals(false, userMessageDAO.sendMessage(userMessage));
	}
	@Ignore
	@Test
	public void test_to_delete_user_message_success()
	{
		userMessage.setMessageId(10);
		userMessage.setReceiverEmailId("deepak@gmail.com");
		
		
		assertEquals(true, userMessageDAO.deleteMessage(userMessage.getMessageId(), userMessage.getReceiverEmailId()));
	}
	@Ignore
	@Test
	public void test_to_delete_user_message_faliure()
	{
		userMessage.setMessageId(8);
		userMessage.setReceiverEmailId("deepak@gmail.com");
		assertEquals(false, userMessageDAO.deleteMessage(userMessage.getMessageId(), userMessage.getReceiverEmailId()));
	}
	
    @Ignore
	@Test
	public void get_all_user_messages_by_reciever_success()
	{
		userMessage.setReceiverEmailId("advik@gmail.com");
		List<UserMessage> messages=userMessageDAO.getMyMessages(userMessage.getReceiverEmailId());
		assertEquals(6, messages.size());
	}
	@Ignore
	@Test
	public void get_all_user_messages_by_reciever_failure()
	{
		userMessage.setReceiverEmailId("deepak1@gmail.com");//Reviever Email Id is Not Exists
		List<UserMessage> messages=userMessageDAO.getMyMessages(userMessage.getReceiverEmailId());
		assertEquals(0, messages.size());
	}
	@Ignore
	@Test
	public void get_user_messages_by_message_id_success()
	{
		userMessage.setMessageId(5);//Reviever Email Id is Not Exists
		UserMessage message=userMessageDAO.getMessageByMessageId(userMessage.getMessageId());
		assertEquals("general", message.getCircleName());
	}
	@Ignore
	@Test
	public void get_user_messages_by_message_id_failure()
	{
		userMessage.setMessageId(10);//Reviever Email Id is Not Exists
		UserMessage message=userMessageDAO.getMessageByMessageId(userMessage.getMessageId());
		assertNull(message);
	}
	@Ignore
	@Test
	public void get_all_messages_by_circle_name_success()
	{
		userMessage.setCircleName("general");
		List<UserMessage> messages=userMessageDAO.getAllMessageByCircleName(userMessage.getCircleName());
		assertEquals(6, messages.size());
	}
	@Test
	public void get_all_messages_by_circle_name_failure()
	{
		userMessage.setCircleName("DevOps");
		List<UserMessage> messages=userMessageDAO.getAllMessageByCircleName(userMessage.getCircleName());
		assertEquals(0, messages.size());
	}
	
}
