package com.test.Dto;

import java.util.List;

public class SocietyResponseDto {
	 int societyId;
    String societyname;
    String address;
    String city;
    int pincode;
    int registrationNumber;
    List<FlatDto>flats;
    List<NoticeDto>notices;
    
	

	 public Integer getSocietyId() {
		 return societyId;
	 }
	 public void setSocietyId(Integer societyId) {
		 this.societyId = societyId;
	 }
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
	 public List<FlatDto> getFlats() {
		 return flats;
	 }
	 public void setFlats(List<FlatDto> flats) {
		 this.flats = flats;
	 }
	 public List<NoticeDto> getNotices() {
		 return notices;
	 }
	 public void setNotices(List<NoticeDto> notices) {
		 this.notices = notices;
	 }
	 public void setSocietyId(int societyId) {
		 this.societyId = societyId;
	 }
	 
    
	

}
