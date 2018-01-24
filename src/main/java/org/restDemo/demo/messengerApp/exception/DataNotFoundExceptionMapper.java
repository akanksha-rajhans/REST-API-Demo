package org.restDemo.demo.messengerApp.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.restDemo.demo.messengerApp.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage err=new ErrorMessage(ex.getMessage(),404, "http://skara.in");
		return Response.status(Status.NOT_FOUND)
				.entity(err)
				.build();
	}

}
