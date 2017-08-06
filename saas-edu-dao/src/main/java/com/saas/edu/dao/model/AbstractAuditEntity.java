package com.saas.edu.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public abstract class AbstractAuditEntity implements Serializable{
	private Date crtdDttm;
	private String crtdUsr;
	private Date lstUptdDttm;
	private String lstUptdUsr;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRTD_DTTM", nullable = false, length = 19)
	public Date getCrtdDttm() {
		return this.crtdDttm;
	}

	public void setCrtdDttm(Date crtdDttm) {
		this.crtdDttm = crtdDttm;
	}

	@Column(name = "CRTD_USR", nullable = false, length = 45)
	public String getCrtdUsr() {
		return this.crtdUsr;
	}

	public void setCrtdUsr(String crtdUsr) {
		this.crtdUsr = crtdUsr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LST_UPTD_DTTM", nullable = false, length = 19)
	public Date getLstUptdDttm() {
		return this.lstUptdDttm;
	}

	public void setLstUptdDttm(Date lstUptdDttm) {
		this.lstUptdDttm = lstUptdDttm;
	}

	@Column(name = "LST_UPTD_USR", nullable = false, length = 45)
	public String getLstUptdUsr() {
		return this.lstUptdUsr;
	}

	public void setLstUptdUsr(String lstUptdUsr) {
		this.lstUptdUsr = lstUptdUsr;
	}
}
