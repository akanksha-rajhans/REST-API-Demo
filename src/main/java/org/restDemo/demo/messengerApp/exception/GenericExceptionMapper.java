package org.restDemo.demo.messengerApp.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.restDemo.demo.messengerApp.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage err=new ErrorMessage(ex.getMessage(),500, "http://skara.in");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(err)
				.build();
	}
	
	
	
}
