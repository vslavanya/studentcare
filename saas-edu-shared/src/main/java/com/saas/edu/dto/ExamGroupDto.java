package com.saas.edu.dto;

public class ExamGroupDto extends AuditDto {

	
	public String getExamGroupCode() {
		return examGroupCode;
	}
	public void setExamGroupCode(String examGroupCode) {
		this.examGroupCode = examGroupCode;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	
	private String examGroupCode; 
	private String year;
	private String schoolCode;
	private String description;
	private String examType;
	@Override
	public String toString() {
		return "ExamGroupDto [examGroupCode=" + examGroupCode + ", year=" + year + ", schoolCode=" + schoolCode
				+ ", description=" + description + ", examType=" + examType + "]";
	}
	

}
