package com.saas.edu.config.akka;

import static com.saas.edu.akka.ext.SpringExtension.springExtProvider;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.saas.edu.dao.model.ClstrDat;
import com.saas.edu.dao.repository.ClusterServerDataRpository;
import com.saas.edu.service.CacheService;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.cluster.metrics.AdaptiveLoadBalancingGroup;
import akka.cluster.metrics.MixMetricsSelector;
import akka.cluster.routing.ClusterRouterGroup;
import akka.cluster.routing.ClusterRouterGroupSettings;

@Configuration
public class SaasAkkaConfig {

	@Autowired
	private CacheService cacheService;
	@Autowired
	private ClusterServerDataRpository clusterServerDataRpository;
	@Autowired
	private ApplicationContext applicationContext;

	private static final Logger LOGGER = LoggerFactory.getLogger(SaasAkkaConfig.class);

	@Bean
	public Config config() {
		MDC.put("TRAN_ID", "AKKA_CONFIGURATION");
		LOGGER.debug("public Config config()");

		String tcp;
		String seeds = null;
		StringBuilder builder = new StringBuilder();
		try {
			String serverName = InetAddress.getLocalHost().getCanonicalHostName().toLowerCase();
			List<ClstrDat> clusterData = clusterServerDataRpository
					.findByAppNme(cacheService.getLookUpItem("saas.edu.appName", "saas.edu"));
			tcp = "akka.remote.netty.tcp.hostname = " + "192.168.0.2" + ", akka.remote.netty.tcp.port = " + 2552;
			builder.append("akka.cluster.seed-nodes = [");
	//		for (ClstrDat data : clusterData) {
				builder.append("\"akka.tcp://SaasEduCluster@");
				//builder.append(data.getSrvrNme().toLowerCase());
				builder.append("192.168.0.2".toLowerCase());
				builder.append(":");
				builder.append("2552");
		//		builder.append(data.getAkkaPort());
				builder.append("\",");
	//		}
			builder.append("]");
			seeds = builder.toString();
			builder.replace(seeds.lastIndexOf(","), seeds.lastIndexOf(",") + 1, "");
			seeds = builder.toString();
			LOGGER.debug(seeds);
			System.out.println(seeds);

			Config seedString = ConfigFactory.parseString(seeds);
			Config nettyTcpString = ConfigFactory.parseString(tcp);
			Config regularConfig = ConfigFactory.load();
			return nettyTcpString.withFallback(seedString)
					.withFallback(getDefaultDispatcher().withFallback(regularConfig));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Config getDefaultDispatcher() {
		StringBuilder defaultDispatcher = new StringBuilder();
		defaultDispatcher.append("default-dispatcher.executor = \"fork-join-executor\"");

		defaultDispatcher.append(",default-dispatcher.executor. fork-join-executor. parallelism-min = ");
		defaultDispatcher.append(cacheService.getLookUpItem("saas.edu.parallelism-min", "2"));

		defaultDispatcher.append(",default-dispatcher.executor. fork-join-executor. parallelism-factor  = ");
		defaultDispatcher.append(cacheService.getLookUpItem("saas.edu.parallelism-factor ", "2"));

		defaultDispatcher.append(",default-dispatcher.executor. fork-join-executor. parallelism-max  = ");
		defaultDispatcher.append(cacheService.getLookUpItem("saas.edu.parallelism-max ", "2"));

		defaultDispatcher.append(",default-dispatcher.throughput  = ");
		defaultDispatcher.append(cacheService.getLookUpItem("saas.edu.dispatcher.throughput ", "2"));
		Config getDefaultDispatcher = ConfigFactory.parseString(defaultDispatcher.toString());

		return getDefaultDispatcher;

	}

	@Bean
	public ActorSystem actorSystem() {

		ActorSystem actorSystem = ActorSystem.create("SaasEduCluster", config());
		springExtProvider.get(actorSystem).initialize(applicationContext);
		return actorSystem;
	}

	@Bean
	List<String> printRouterPath() {
		List<String> path = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			actorSystem().actorOf(springExtProvider.get(actorSystem()).props("printActor"), "printActor" + i);
			path.add("/user/printActor" + i);
		}
		return path;
	}

	@Bean
	public ActorRef printRouterActorRef() {
		boolean allowRoutees = true;
		int totalInstances = 10000;
		String useRole = "compute";
		ActorRef actorRef = actorSystem().actorOf(new ClusterRouterGroup(
				new AdaptiveLoadBalancingGroup(MixMetricsSelector.getInstance(), printRouterPath()),
				new ClusterRouterGroupSettings(totalInstances, printRouterPath(), allowRoutees, useRole)).props(),
				"printerRouter");
		return actorRef;

	}

}
