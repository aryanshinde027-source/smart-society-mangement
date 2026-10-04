package com.test.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Complaint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int complaintid;
	@Column(nullable = false)
	String title;
	@Column(nullable = false)
	String description;
	@Column(nullable = false)
	String status;
	int complaintDate;
	@ManyToOne(optional = false)
	@JoinColumn(name = "resident_id", nullable = false)
	Resident resident;
	public Complaint() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Complaint(int complaintid, String title, String description, String status, int complaintDate,
			Resident resident) {
		super();
		this.complaintid = complaintid;
		this.title = title;
		this.description = description;
		this.status = status;
		this.complaintDate = complaintDate;
		this.resident = resident;
	}
	public int getComplaintid() {
		return complaintid;
	}
	public void setComplaintid(int complaintid) {
		this.complaintid = complaintid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getComplaintDate() {
		return complaintDate;
	}
	public void setComplaintDate(int complaintDate) {
		this.complaintDate = complaintDate;
	}
	public Resident getResident() {
		return resident;
	}
	public void setResident(Resident resident) {
		this.resident = resident;
	}
	

}
