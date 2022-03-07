package com.codefilms.webresource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;


/**
 * Root resource (exposed at "myresource" path)
 */
//web resource path
//-> http://localhost:8080/{school-name}/{student}/{report}/{name-of-student}


// why api is used in the uri?
//@Path(value="/apple-valley-school/student/report/{studentID}")
@Path(value="/console")
public class CustomSmsWebHook {
	
	
	 // Find your Account SID and Auth Token at twilio.com/console
   // and set the environment variables. See http://twil.io/secure
   public static final String ACCOUNT_SID = "ACa065a2548ed6d4485d375d93901aa3c7";
   		
   		
   public static final String AUTH_TOKEN = "f0bd7ccbefad10d75e3f49397e0324d4";



	
	@GET
	@Produces("text/plain")
	public String index()
	{
		return "index";
	}
	
	@SuppressWarnings("null")
	@GET
	@Path(value="/sms/generate/students")
	@Produces("*/*")
	public Response sendSms(@QueryParam("query") String userQuery ) {
		
		
			   final String EXPECTED_QUERY = "--all";
	
				System.out.println("Send Sms Demo App started...");
				
				
				// to send sms to all students, pass an expected query of all
				if(!userQuery.equals(EXPECTED_QUERY))
				{
					
					String errorResponse = "invalid command:" + userQuery;
					ResponseBuilder errorRb = Response.ok(errorResponse, MediaType.TEXT_PLAIN);
					errorRb.entity(errorResponse);
					return  errorRb.build();
				}
				// for the right query flag passed send sms to all parents.
				else {
					String msgBody = "Hello Mrs Santos, your child, Akua Santos Terminal Report for the year is ready."
							+ "Kindly find report here https://report-card-jax-rs.herokuapp.com/webresources/school/apple-valley-school/student/report/1 for your attention";

					System.out.println(msgBody.length());
					
					//+19106599370
				        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
				        Message message = Message.creator(
				                new com.twilio.type.PhoneNumber("+233243173165"),
				                new com.twilio.type.PhoneNumber("+19106599370"),
				                msgBody)
				            .create();

				        System.out.println(message.getSid()); 
				        
						System.out.println("Send Sms Demo App ended...");
				}
				
					String successResponse = "sms sent";
					ResponseBuilder successRb = Response.ok(successResponse,MediaType.TEXT_PLAIN);
					successRb.entity(successResponse);
					return successRb.build();
							

		
				
	}

		
		
	 
	}

	


 
	

				
		
    	
 
    

