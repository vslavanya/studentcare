package com.saas.edu.dto;

public class QuartzJobDto {
	
	private String jobName;
	private String jobClass;
	private String triggerName;
	private String triggerType;
	private String triggerValue;
	private String triggerInd;
	private String schedulerName;
	
	
	
	public String getSchedulerName() {
		return schedulerName;
	}
	public void setSchedulerName(String schedulerName) {
		this.schedulerName = schedulerName;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobClass() {
		return jobClass;
	}
	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}
	public String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	public String getTriggerType() {
		return triggerType;
	}
	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}
	public String getTriggerValue() {
		return triggerValue;
	}
	public void setTriggerValue(String triggerValue) {
		this.triggerValue = triggerValue;
	}
	public String getTriggerInd() {
		return triggerInd;
	}
	public void setTriggerInd(String triggerInd) {
		this.triggerInd = triggerInd;
	}

}
