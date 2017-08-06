package com.saas.edu.akka.ext;

import org.springframework.context.ApplicationContext;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;

public class SpringActorProducer implements IndirectActorProducer{
	
	private final ApplicationContext applicationContext;
	
	private final String actorBeanName;
	private final String conargs;
	
	public SpringActorProducer(ApplicationContext applicationContext, String actorBeanName, String conargs) {
		this.applicationContext = applicationContext;
		this.actorBeanName = actorBeanName;
		this.conargs = conargs;
	}
	
	public SpringActorProducer(ApplicationContext applicationContext, String actorBeanName) {
		this.conargs = null;
		this.applicationContext = applicationContext;
		this.actorBeanName = actorBeanName;
	}
	
	@Override
	public Actor produce(){
		if(null != conargs){
			return (Actor) applicationContext.getBean(actorBeanName,conargs);
		}else{
			return (Actor) applicationContext.getBean(actorBeanName);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends Actor> actorClass() {
		return (Class<? extends Actor>) applicationContext.getType(actorBeanName);
	}
	
	

}
