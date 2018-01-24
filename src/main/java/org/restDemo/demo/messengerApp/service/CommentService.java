package org.restDemo.demo.messengerApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.restDemo.demo.messengerApp.database.DatabaseClass;
import org.restDemo.demo.messengerApp.model.Comment;
import org.restDemo.demo.messengerApp.model.ErrorMessage;
import org.restDemo.demo.messengerApp.model.Message;

public class CommentService {
	
	private Map<Long, Message> messages=DatabaseClass.getMessages();
	
	public List<Comment> getAllComments(long messageId){
		Map<Long, Comment> comments=messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId){
		ErrorMessage err=new ErrorMessage("Not Found!!!",404, "http://skara.in");
		Response res=Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(err)
				.build();
		Message message=messages.get(messageId);
		if(message==null)
			throw new WebApplicationException(res);
		Map<Long, Comment> comments=messages.get(messageId).getComments();
		Comment comment=comments.get(commentId);
		if(comment==null)
			throw new WebApplicationException(res);
		return comment;
	}
	
	public Comment addComment(long messageId, Comment comment){
		Map<Long, Comment> comments=messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment){
		Map<Long, Comment> comments=messages.get(messageId).getComments();
		if(comment.getId()<=0)
			return null;
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId){
		Map<Long, Comment> comments=messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
	
}
