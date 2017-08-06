package com.saas.edu.dto;

import java.util.Date;

public class ExamDto extends AuditDto{
	private String examGroupCode; 
	private String examCode;
	private String subject;
	private Date examDate;
	private String year;
	private String grade;
	private String maximumMark;
	private String schoolCode;

	
	public String getExamGroupCode() {
		return examGroupCode;
	}
	public void setExamGroupCode(String examGroupCode) {
		this.examGroupCode = examGroupCode;
	}
	public String getExamCode() {
		return examCode;
	}
	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getMaximumMark() {
		return maximumMark;
	}
	public void setMaximumMark(String maximumMark) {
		this.maximumMark = maximumMark;
	}
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	@Override
	public String toString() {
		return "ExamDto [examGroupCode=" + examGroupCode + ", examCode=" + examCode + ", subject=" + subject
				+ ", examDate=" + examDate + ", year=" + year + ", grade=" + grade + "]";
	}
	
	
	

}
