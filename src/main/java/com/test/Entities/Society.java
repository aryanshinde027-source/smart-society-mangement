package com.test.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Society {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	int id;
	@Column(nullable = false)
	String Societyname;
	@Column(nullable = false)
	String address;
	@Column(nullable = false)
	String city;
	int pincode;
	int registrationnumber;
	
	@OneToMany(mappedBy = "societyId",cascade  = CascadeType.ALL)
	public List<Flat>flats;
	@OneToMany(mappedBy = "society",cascade = CascadeType.ALL)
	public List<Notice>notices;
	public Society() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Society(int id, String societyname, String address, String city, int pincode, int registrationnumber) {
		super();
		this.id = id;
		Societyname = societyname;
		this.address = address;
		this.city = city;
		this.pincode = pincode;
		this.registrationnumber = registrationnumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSocietyname() {
		return Societyname;
	}
	public void setSocietyname(String societyname) {
		Societyname = societyname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public int getRegistrationnumber() {
		return registrationnumber;
	}
	public void setRegistrationnumber(int registrationnumber) {
		this.registrationnumber = registrationnumber;
	}
	public List<Flat> getFlats() {
		return flats;
	}
	public void setFlats(List<Flat> flats) {
		this.flats = flats;
	}
	public List<Notice>getNotices(){
		return notices;
	}
	public void setNotices(List<Notice>notices) {
		this.notices=notices;
	}
	
}

