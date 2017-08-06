package com.saas.edu.akka.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.saas.edu.dto.StatusDto;
import com.saas.edu.service.ResponseStatusService;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrintActor extends SaasActor{

	@Autowired
	private ResponseStatusService responseStatusService;
	
	public PrintActor(){
		receive(ReceiveBuilder.match(String.class, message-> print(message)).matchAny(message -> System.out.println(message)).build());
	}

	@Override
	public void preStart() throws Exception {
		// TODO Auto-generated method stub
		super.preStart();
	}

	private StatusDto print(String message) {
		return responseStatusService.getStatusDto();
	}
}
