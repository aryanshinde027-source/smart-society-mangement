package com.test.Dto;

public class SocietyRequestDto {
	
    String societyname;
    String address;
    String city;
    int pincode;
    int registrationNumber;
	

	
	 public String getSocietyname() {
		 return societyname;
	 }
	 public void setSocietyname(String societyname) {
		 this.societyname = societyname;
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
	 public int getRegistrationNumber() {
		 return registrationNumber;
	 }
	 public void setRegistrationNumber(int registrationNumber) {
		 this.registrationNumber = registrationNumber;
	 }
    

}
