package com.test.Entities;



import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Flat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int flatid;
	int flatnumber;
	@Column(nullable = false)
	String wing;
	@Column(nullable = false)
	String floortype;
	@Column(nullable = false)
	String status;
	@ManyToOne(optional = false)
	@JoinColumn(name = "society_id", nullable = false)
	Society societyId;
	@OneToMany(mappedBy = "flat",cascade  = CascadeType.ALL)
	public List<Resident>residents;
	@OneToMany(mappedBy = "flat",cascade = CascadeType.ALL)
	public List<Maintaince>maintainces;
	
	public Flat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Flat(int flatid, int flatnumber, String wing, String floortype, String status, Society societyId) {
		super();
		this.flatid = flatid;
		this.flatnumber = flatnumber;
		this.wing = wing;
		this.floortype = floortype;
		this.status = status;
		this.societyId = societyId;
	}

	public int getFlatid() {
		return flatid;
	}

	public void setFlatid(int flatid) {
		this.flatid = flatid;
	}

	public int getFlatnumber() {
		return flatnumber;
	}

	public void setFlatnumber(int flatnumber) {
		this.flatnumber = flatnumber;
	}

	public String getWing() {
		return wing;
	}

	public void setWing(String wing) {
		this.wing = wing;
	}

	public String getFloortype() {
		return floortype;
	}

	public void setFloortype(String floortype) {
		this.floortype = floortype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Society getSocietyId() {
		return societyId;
	}

	public void setSocietyId(Society societyId) {
		this.societyId = societyId;
	}

	public List<Resident> getResidents() {
		return residents;
	}

	public void setResidents(List<Resident> residents) {
		this.residents = residents;
	}

	public List<Maintaince> getMaintainces() {
		return maintainces;
	}

	public void setMaintainces(List<Maintaince> maintainces) {
		this.maintainces = maintainces;
	}
	

}
