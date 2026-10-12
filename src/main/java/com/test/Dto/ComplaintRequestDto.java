package com.test.Dto;

public class ComplaintRequestDto {
    String title;
    String description;
    String status;
    int complaintDate;
    int residentId;

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

    public int getResidentId() { 
    	return residentId; 
    	}
    
    public void setResidentId(int residentId) { 
    	this.residentId = residentId;
    	}
}
