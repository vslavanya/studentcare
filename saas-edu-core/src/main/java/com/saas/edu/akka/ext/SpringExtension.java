package com.saas.edu.akka.ext;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import akka.actor.Props;

@Component
public class SpringExtension extends AbstractExtensionId<SpringExtension.SpringExt> {
	
	public static SpringExtension springExtProvider = new SpringExtension(); 
	
	@Override
	public SpringExt createExtension(ExtendedActorSystem system){
		return new SpringExt();
	}
	
	public static class SpringExt implements Extension{
		public ApplicationContext applicationContext;
		
		public void initialize(ApplicationContext applicationContext){
			this.applicationContext = applicationContext;
		}
		
		public Props props(String actorBeanName){
			return Props.create(SpringActorProducer.class, applicationContext, actorBeanName);
		}
		
		public Props props(String actorBeanName, String consArgs) {
			return Props.create(SpringActorProducer.class, applicationContext, actorBeanName, consArgs);
		}
	}

}
