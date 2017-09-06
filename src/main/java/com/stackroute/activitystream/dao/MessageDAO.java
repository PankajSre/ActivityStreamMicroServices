package com.stackroute.activitystream.dao;

import java.util.List;

import com.stackroute.activitystream.model.Message;
//1
//Actually two daos are not required - MessageDao and UserMessageDao
//  But two tables are required. ( message and user_message)
//  At the time of sending the message, store the information in both the tables.
//  At the time of reading/deleting use user_message table.

//2
//If you want to keep two separate daos
//use MessageDao for sending the message (but store in both the tables.)
//use UserMEssageDao for retreving and deleting the message.

public interface MessageDAO {

	boolean sendMessage(Message message);
	List<Message> getAllMessages(String emailId);
	boolean updateMessage(Message message);
	Message getMessageById(int messageId);
	boolean isReceiverExists(String receiverEmailId);
}
