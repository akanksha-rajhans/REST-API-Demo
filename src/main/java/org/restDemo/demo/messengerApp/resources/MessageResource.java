package org.restDemo.demo.messengerApp.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.restDemo.demo.messengerApp.exception.DataNotFoundException;
import org.restDemo.demo.messengerApp.model.Message;
import org.restDemo.demo.messengerApp.resources.bean.MessageFilterBean;
import org.restDemo.demo.messengerApp.service.MessageService;

@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class MessageResource {
	
	MessageService messageService= new MessageService();
	
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean){
		if(filterBean.getYear()>0)
			return messageService.getAllMessagesForYear(filterBean.getYear());
		if(filterBean.getStart()>=0 && filterBean.getSize()>0)
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessagesById(@PathParam("messageId") long messageId, @Context UriInfo uriInfo){
		Message message=messageService.getMessage(messageId);
		
		message.addLink(getUriInfo(uriInfo, message), "self");
		return message;
	}
	
	private String getUriInfo(UriInfo uriInfo, Message message){
		String uri=uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(message.getId())
				.build()
				.toString();
		return uri;
	}
	
	@POST
	public Response addMessage(Message message,@Context UriInfo uriInfo){
		Message msg=messageService.addMessage(message);
		String newId=String.valueOf(message.getId());
		URI uri=uriInfo.getAbsolutePathBuilder().path(newId).build();
		return	Response.created(uri)
					.entity(msg)
					.build();
			//return messageService.addMessage(message);
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long messageId,Message message){
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long messageId){
		messageService.removeMessage(messageId);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
	
}

