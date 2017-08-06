package com.saas.edu.config.quartz;

import javax.annotation.PostConstruct;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.saas.edu.dto.QuartzJobDto;

@Configuration
public class QuartzConfiguration {
	@Autowired
	private ApplicationContext applicationContext;
	
	private AutowireCapableBeanFactory autowireCapableBeanFactory;
	private DefaultListableBeanFactory defaultListableBeanFactory;
	
	@PostConstruct
	public void init(){
		String module = this.getClass().getSimpleName();
		autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
		AbstractApplicationContext abstractApplicationContext= (AbstractApplicationContext) applicationContext;
		defaultListableBeanFactory = (DefaultListableBeanFactory) abstractApplicationContext.getBeanFactory();
		
		
	}
	
	private JobDetail createJobDetail(QuartzJobDto quartzJobDto) throws ClassNotFoundException{
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(JobDetailFactoryBean.class);
		beanDefinitionBuilder.setScope(BeanDefinition.SCOPE_SINGLETON);
		beanDefinitionBuilder.addPropertyValue("durability", true);
		beanDefinitionBuilder.addPropertyValue("name", quartzJobDto.getJobName());
		beanDefinitionBuilder.addPropertyValue("jobClass", Class.forName(quartzJobDto.getJobClass()));
		beanDefinitionBuilder.addPropertyValue("group", quartzJobDto.getSchedulerName());
		defaultListableBeanFactory.registerBeanDefinition(quartzJobDto.getJobName(), beanDefinitionBuilder.getBeanDefinition());
		autowireCapableBeanFactory.autowireBean(beanDefinitionBuilder.getBeanDefinition());
		return (JobDetail) defaultListableBeanFactory.getBean(quartzJobDto.getJobName());
	}
	
	private CronTrigger getCronTrigger(QuartzJobDto quartzJobDto, JobDetail jobDetail){
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(CronTriggerFactoryBean.class);
		beanDefinitionBuilder.setScope(BeanDefinition.SCOPE_SINGLETON);
		beanDefinitionBuilder.addPropertyValue("name", quartzJobDto.getTriggerName());
		beanDefinitionBuilder.addPropertyValue("group", quartzJobDto.getSchedulerName());
		beanDefinitionBuilder.addPropertyValue("beanName", quartzJobDto.getTriggerName());
		beanDefinitionBuilder.addPropertyValue("cronExpression", quartzJobDto.getTriggerValue());
		beanDefinitionBuilder.addPropertyValue("jobDetail", jobDetail);
		defaultListableBeanFactory.registerBeanDefinition(quartzJobDto.getTriggerName(), beanDefinitionBuilder.getBeanDefinition());
		autowireCapableBeanFactory.autowireBean(beanDefinitionBuilder.getBeanDefinition());
		return (CronTrigger) defaultListableBeanFactory.getBean(quartzJobDto.getTriggerName());
	}
	private SimpleTrigger getSimpleTrigger(QuartzJobDto quartzJobDto, JobDetail jobDetail){
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(SimpleTriggerFactoryBean.class);
		beanDefinitionBuilder.setScope(BeanDefinition.SCOPE_SINGLETON);
		beanDefinitionBuilder.addPropertyValue("name", quartzJobDto.getTriggerName());
		beanDefinitionBuilder.addPropertyValue("group", quartzJobDto.getSchedulerName());
		beanDefinitionBuilder.addPropertyValue("beanName", quartzJobDto.getTriggerName());
		beanDefinitionBuilder.addPropertyValue("repeatInterval", Long.parseLong(quartzJobDto.getTriggerValue()));
		beanDefinitionBuilder.addPropertyValue("jobDetail", jobDetail);
		defaultListableBeanFactory.registerBeanDefinition(quartzJobDto.getTriggerName(), beanDefinitionBuilder.getBeanDefinition());
		autowireCapableBeanFactory.autowireBean(beanDefinitionBuilder.getBeanDefinition());
		return (SimpleTrigger) defaultListableBeanFactory.getBean(quartzJobDto.getTriggerName());
	}
	
	}
