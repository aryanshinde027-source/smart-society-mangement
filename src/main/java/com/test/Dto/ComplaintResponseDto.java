package com.test.Dto;

public class ComplaintResponseDto {
	 private int complaintid;
	    private String title;
	    private String description;
	    private String status;
	    private int complaintDate;

	    private ResidentDto resident;

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

	    public ResidentDto getResident() {
	        return resident;
	    }

	    public void setResident(ResidentDto resident) {
	        this.resident = resident;
	    }

}
