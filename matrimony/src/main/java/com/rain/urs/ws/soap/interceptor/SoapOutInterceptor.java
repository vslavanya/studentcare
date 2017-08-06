package com.rain.urs.ws.soap.interceptor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.cxf.interceptor.AbstractOutDatabindingInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxrs.ext.ResponseHandler;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;



public class SoapOutInterceptor extends AbstractOutDatabindingInterceptor  {
	 public SoapOutInterceptor() {
	        super(Phase.MARSHAL);
	    }

	@Override
	public void handleMessage(Message outMessage) throws Fault {
	
//		Map<String, List<String>> headers = (Map<String, List<String>>)outMessage.get(Message.PROTOCOL_HEADERS);
//		 if (headers != null) {
//			System.out.println(headers);
//	            
//	        }
//		 else{
//			// Creating HTTP headers
//			  headers = new HashMap<String, List<String>>();
//			 headers.put("XXX-SOA-SERVICE-NAME", Arrays.asList("Lal"));
//			 headers.put("XXX-SOA-APP-NAME", Arrays.asList("Vasudevan"));
//			 outMessage.put(Message.PROTOCOL_HEADERS, headers);
//			 System.out.println(" headers are null");
//		 }
	}

	

}
