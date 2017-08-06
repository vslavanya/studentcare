package com.saas.edu.akka.actor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import akka.actor.AbstractActor;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import akka.cluster.ClusterEvent.MemberEvent;
import akka.cluster.ClusterEvent.ReachabilityEvent;
import akka.cluster.ClusterEvent.ReachableMember;
import akka.cluster.ClusterEvent.UnreachableMember;

public class SaasActor extends AbstractActor{
	@Override
	public void postStop() throws Exception {
		cluster.unsubscribe(self());
	}

	protected Cluster cluster = Cluster.get(getContext().system());
	private static final Logger LOGGER = LoggerFactory.getLogger(SaasActor.class);
@Override
	public void preStart() throws Exception {
	LOGGER.debug("starting actor={}, parent={}",self().path(), getContext().parent());
	cluster.subscribe(self(), ClusterEvent.initialStateAsEvents(),MemberEvent.class,UnreachableMember.class,ReachableMember.class,ReachabilityEvent.class);

	}

protected void onReceiveLocal(Object message){
	System.out.println("unhandled Message");
}
}
