package com.saas.edu.akka.ext;

import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spring.SpringCamelContext;
import static com.saas.edu.akka.ext.SpringExtension.springExtProvider;

import akka.actor.ExtendedActorSystem;
import akka.camel.ContextProvider;

public class CustomContextProvider implements ContextProvider{
	@Override
	public DefaultCamelContext getContext(ExtendedActorSystem system){
		SpringCamelContext camelContext = new SpringCamelContext(springExtProvider.get(system).applicationContext);
		return camelContext;
	}

}
