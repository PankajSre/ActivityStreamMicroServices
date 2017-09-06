package com.stackroute.activitystream.test;

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

import com.stackroute.activitystream.dao.MessageDAO;
import com.stackroute.activitystream.main.ActivityStreamBackEnd;
import com.stackroute.activitystream.model.Message;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ActivityStreamBackEnd.class)
@EnableAspectJAutoProxy
public class MessageJUnitTest {

	@Autowired
	 private Message message;
	@Autowired
	private MessageDAO messageDAO;
	
	@Ignore
	@Test
	public void test_to_send_message_success()
	{
		message.setMessageId(9);
		message.setMaximumSize(1000000);
		message.setMessageText("Youa are looking Good!");
		message.setMessageSize(message.getMessageText().length());
		message.setMessageType("Text");
		message.setSentDate(new Date());
		message.setSenderEmailId("advik@gmail.com");
		message.setReceiverEmailId("deepak@gmail.com");
		assertEquals(true, messageDAO.sendMessage(message));
	}
	@Ignore
	@Test
	public void test_to_send_message_failure()
	{
		message.setMessageId(9);
		message.setMaximumSize(1000000);
		message.setMessageText("Youa are looking Good!");
		message.setMessageSize(message.getMessageText().length());
		message.setMessageType("Text");
		message.setSentDate(new Date());
		message.setSenderEmailId("advik@gmail.com");
		message.setReceiverEmailId("deepak1@gmail.com");
		assertEquals(false, messageDAO.sendMessage(message));
	}
	@Ignore
	@Test
	public void get_all_messages_by_reciever_success()
	{
		message.setReceiverEmailId("deepak@gmail.com");
		List<Message> messages=messageDAO.getAllMessages(message.getReceiverEmailId());
		assertEquals(1, messages.size());
	}
	@Ignore
	@Test
	public void get_all_messages_by_reciever_failure()
	{
		message.setReceiverEmailId("deepak1@gmail.com");//Reviever Email Id is Not Exists
		List<Message> messages=messageDAO.getAllMessages(message.getReceiverEmailId());
		assertEquals(0, messages.size());
	}
	@Ignore
	@Test
	public void update_message_success()
	{
		message.setMessageId(1);
		Message msg=messageDAO.getMessageById(message.getMessageId());
		msg.setMessageText("I am Sorry..I am trying again");
		msg.setMessageSize(msg.getMessageSize());
		
		assertEquals(true,messageDAO.updateMessage(msg));
	}
	@Ignore
	@Test
	public void update_message_failure()
	{
		message.setMessageId(2);//Id not Available
		Message msg=messageDAO.getMessageById(message.getMessageId());
		msg.setMessageText("I am Sorry..I am trying again");
		msg.setMessageSize(msg.getMessageSize());
		
		assertEquals(false,messageDAO.updateMessage(msg));
	}
	@Ignore
	@Test
	public void message_by_id_success()
	{
		message.setMessageId(1);
		Message msg=messageDAO.getMessageById(message.getMessageId());
		
		assertEquals("Text",msg.getMessageType());
	}
	@Ignore
	@Test
	public void message_by_id_failure()
	{
		message.setMessageId(21);
		Message msg=messageDAO.getMessageById(message.getMessageId());
		
		assertEquals("Text",msg.getMessageType());
	}
}
