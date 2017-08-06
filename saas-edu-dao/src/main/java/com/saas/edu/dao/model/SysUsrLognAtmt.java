package com.saas.edu.dao.model;
// Generated Jul 31, 2017 10:35:22 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SysUsrLognAtmt generated by hbm2java
 */
@Entity
@Table(name = "sys_usr_logn_atmt", catalog = "saas_core_db")
public class SysUsrLognAtmt extends AbstractAuditEntity {

	private int sysUsrLognId;
	private SysUsr sysUsr;
	private Character usrLoginStatus;
	private String crtdByUssr;
	private Date crtdDttm;
	private String uptdByUsr;
	private Date uptdDttm;

	public SysUsrLognAtmt() {
	}

	public SysUsrLognAtmt(int sysUsrLognId, SysUsr sysUsr) {
		this.sysUsrLognId = sysUsrLognId;
		this.sysUsr = sysUsr;
	}

	public SysUsrLognAtmt(int sysUsrLognId, SysUsr sysUsr, Character usrLoginStatus, String crtdByUssr, Date crtdDttm,
			String uptdByUsr, Date uptdDttm) {
		this.sysUsrLognId = sysUsrLognId;
		this.sysUsr = sysUsr;
		this.usrLoginStatus = usrLoginStatus;
		this.crtdByUssr = crtdByUssr;
		this.crtdDttm = crtdDttm;
		this.uptdByUsr = uptdByUsr;
		this.uptdDttm = uptdDttm;
	}

	@Id

	@Column(name = "SYS_USR_LOGN_ID", unique = true, nullable = false)
	public int getSysUsrLognId() {
		return this.sysUsrLognId;
	}

	public void setSysUsrLognId(int sysUsrLognId) {
		this.sysUsrLognId = sysUsrLognId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SYS_USR_ID", nullable = false)
	public SysUsr getSysUsr() {
		return this.sysUsr;
	}

	public void setSysUsr(SysUsr sysUsr) {
		this.sysUsr = sysUsr;
	}

	@Column(name = "USR_LOGIN_STATUS", length = 1)
	public Character getUsrLoginStatus() {
		return this.usrLoginStatus;
	}

	public void setUsrLoginStatus(Character usrLoginStatus) {
		this.usrLoginStatus = usrLoginStatus;
	}

	@Column(name = "CRTD_BY_USSR", length = 15)
	public String getCrtdByUssr() {
		return this.crtdByUssr;
	}

	public void setCrtdByUssr(String crtdByUssr) {
		this.crtdByUssr = crtdByUssr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRTD_DTTM", length = 19)
	public Date getCrtdDttm() {
		return this.crtdDttm;
	}

	public void setCrtdDttm(Date crtdDttm) {
		this.crtdDttm = crtdDttm;
	}

	@Column(name = "UPTD_BY_USR", length = 15)
	public String getUptdByUsr() {
		return this.uptdByUsr;
	}

	public void setUptdByUsr(String uptdByUsr) {
		this.uptdByUsr = uptdByUsr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPTD_DTTM", length = 19)
	public Date getUptdDttm() {
		return this.uptdDttm;
	}

	public void setUptdDttm(Date uptdDttm) {
		this.uptdDttm = uptdDttm;
	}

}
