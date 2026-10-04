package com.test.Dto;

import java.util.List;

public class FlatResponseDto {
	int flatid;
	int flatnumber;
	String wing;
	String floortype;
	String status;
	SocietyDto society;
	List<ResidentDto>residents;
	List<MaintainceDto>maintainces;
	
	
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
	public SocietyDto getSociety() {
		return society;
	}
	public void setSociety(SocietyDto society) {
		this.society = society;
	}
	public List<ResidentDto> getResidents() {
		return residents;
	}
	public void setResidents(List<ResidentDto> residents) {
		this.residents = residents;
	}
	public List<MaintainceDto> getMaintainces() {
		return maintainces;
	}
	public void setMaintainces(List<MaintainceDto> maintainces) {
		this.maintainces = maintainces;
	}

	
}
