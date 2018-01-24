package org.restDemo.demo.messengerApp.database;

import java.util.HashMap;
import java.util.Map;

import org.restDemo.demo.messengerApp.model.Message;
import org.restDemo.demo.messengerApp.model.Profile;

public class DatabaseClass {
	private static Map<Long, Message> messages=new HashMap<>();
	private static Map<String, Profile> profiles=new HashMap<>();
	
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	/*public static void setMessages(Map<Long, Message> messages) {
		DatabaseClass.messages = messages;
	}*/
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
	/*public static void setProfiles(Map<Long, Profile> profiles) {
		DatabaseClass.profiles = profiles;
	}*/
	
	
}
