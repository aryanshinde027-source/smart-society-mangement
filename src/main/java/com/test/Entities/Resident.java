package com.test.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Resident {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int residentid;
	@Column(nullable = false)
	String residentName;
	@Column(nullable = false)
	String mobilenumber;
	@Column(nullable = false)
	String email;
	@Column(nullable = false)
	String gender;
	int age;
	@Column(nullable = false)
	String role;
	@ManyToOne(optional = false)
	@JoinColumn(name = "flat_id", nullable = false)
	Flat flat;
	@OneToMany(mappedBy = "resident",cascade = CascadeType.ALL)
	public List<Complaint>complaints;
	public Resident() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Resident(int residentid, String residentName, String mobilenumber, String email, String gender, int age,
			String role, Flat flat) {
		super();
		this.residentid = residentid;
		this.residentName = residentName;
		this.mobilenumber = mobilenumber;
		this.email = email;
		this.gender = gender;
		this.age = age;
		this.role = role;
		this.flat = flat;
	}
	public int getResidentid() {
		return residentid;
	}
	public void setResidentid(int residentid) {
		this.residentid = residentid;
	}
	public String getResidentName() {
		return residentName;
	}
	public void setResidentName(String residentName) {
		this.residentName = residentName;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Flat getFlat() {
		return flat;
	}
	public void setFlat(Flat flat) {
		this.flat = flat;
	}
	public List<Complaint> getComplaints() {
		return complaints;
	}
	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}
	

	

}
