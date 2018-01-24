package org.restDemo.demo.messengerApp.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.restDemo.demo.messengerApp.database.DatabaseClass;
import org.restDemo.demo.messengerApp.exception.DataNotFoundException;
import org.restDemo.demo.messengerApp.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages=DatabaseClass.getMessages();
	
	public MessageService(){
		messages.put(1L, new Message(1,"Test Message1","Akanksha"));
		messages.put(2L, new Message(2,"Test Message2","Akanksha"));
	}
	
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messagesForYear=new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message: messages.values()){
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR)==year)
				messagesForYear.add(message);
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size){
		ArrayList<Message> messageList = new ArrayList<Message>(messages.values());
		if(start+size>messageList.size())
			return new ArrayList<Message>();
		return messageList.subList(start, start + size);
	}
	
	public Message getMessage(long id){
		Message msg=messages.get(id);
		if(msg==null)
			throw new DataNotFoundException("Message with Id:"+id+" is not found");
		return messages.get(id);
	}
	public Message addMessage(Message message){
		message.setId(messages.size()+1);
		messages.put(Long.parseLong(message.getId()), message);
		return message;
	}
	public Message updateMessage(Message message){
		if(Long.parseLong(message.getId())<=0)
			return null;
		messages.put(Long.parseLong(message.getId()), message);
		return message;
	}
	public Message removeMessage(long id){
		return messages.remove(id);
	}
}
