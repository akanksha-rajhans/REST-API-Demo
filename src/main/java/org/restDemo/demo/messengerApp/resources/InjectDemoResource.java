package org.restDemo.demo.messengerApp.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)

public class InjectDemoResource {
	
	@GET
	@Path("annotations")
	//more annotations are @CookieParam & @FormParam
	public String getParamsUsingAnnotations(@MatrixParam("matrixparam") String matrixparam,
											@HeaderParam("header") String header){
		return "Test Result Matrix Param: "+matrixparam + "++++++++++++ Header Param: "+header;
	}
	
	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo){
		String path=uriInfo.getAbsolutePath().toString();
		return path;
			
	}
}
