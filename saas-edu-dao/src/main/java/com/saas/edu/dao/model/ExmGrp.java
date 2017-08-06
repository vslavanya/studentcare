package com.saas.edu.dao.model;
// Generated Jul 31, 2017 10:35:22 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * ExmGrp generated by hbm2java
 */
@Entity
@Table(name = "exm_grp", catalog = "saas_core_db", uniqueConstraints = @UniqueConstraint(columnNames = "GRP_CDE_NME"))
public class ExmGrp extends AbstractAuditEntity {

	private Long exmGrpId;
	private Schl schl;
	private String grpCdeNme;
	private String year;
	private String exmGrpDesc;
	private Date crtdDttm;
	private String crtdUsr;
	private Date lstUptdDttm;
	private String lstUptdUsr;
	private String typeNme;
	private Set<Exm> exms = new HashSet<Exm>(0);

	public ExmGrp() {
	}

	public ExmGrp(Schl schl, String exmGrpDesc, Date crtdDttm, String crtdUsr, Date lstUptdDttm, String lstUptdUsr) {
		this.schl = schl;
		this.exmGrpDesc = exmGrpDesc;
		this.crtdDttm = crtdDttm;
		this.crtdUsr = crtdUsr;
		this.lstUptdDttm = lstUptdDttm;
		this.lstUptdUsr = lstUptdUsr;
	}

	public ExmGrp(Schl schl, String grpCdeNme, String year, String exmGrpDesc, Date crtdDttm, String crtdUsr,
			Date lstUptdDttm, String lstUptdUsr, String typeNme, Set<Exm> exms) {
		this.schl = schl;
		this.grpCdeNme = grpCdeNme;
		this.year = year;
		this.exmGrpDesc = exmGrpDesc;
		this.crtdDttm = crtdDttm;
		this.crtdUsr = crtdUsr;
		this.lstUptdDttm = lstUptdDttm;
		this.lstUptdUsr = lstUptdUsr;
		this.typeNme = typeNme;
		this.exms = exms;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "EXM_GRP_ID", unique = true, nullable = false)
	public Long getExmGrpId() {
		return this.exmGrpId;
	}

	public void setExmGrpId(Long exmGrpId) {
		this.exmGrpId = exmGrpId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCHL_ID", nullable = false)
	public Schl getSchl() {
		return this.schl;
	}

	public void setSchl(Schl schl) {
		this.schl = schl;
	}

	@Column(name = "GRP_CDE_NME", unique = true, length = 16)
	public String getGrpCdeNme() {
		return this.grpCdeNme;
	}

	public void setGrpCdeNme(String grpCdeNme) {
		this.grpCdeNme = grpCdeNme;
	}

	@Column(name = "YEAR", length = 8)
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "EXM_GRP_DESC", nullable = false, length = 256)
	public String getExmGrpDesc() {
		return this.exmGrpDesc;
	}

	public void setExmGrpDesc(String exmGrpDesc) {
		this.exmGrpDesc = exmGrpDesc;
	}

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

	@Column(name = "TYPE_NME", length = 16)
	public String getTypeNme() {
		return this.typeNme;
	}

	public void setTypeNme(String typeNme) {
		this.typeNme = typeNme;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "exmGrp")
	public Set<Exm> getExms() {
		return this.exms;
	}

	public void setExms(Set<Exm> exms) {
		this.exms = exms;
	}

}
