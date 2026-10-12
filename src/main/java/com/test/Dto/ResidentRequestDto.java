package com.test.Dto;

public class ResidentRequestDto {
    String residentName;
    String mobilenumber;
    String email;
    String gender;
    int age;
    String role;
    int flatId;

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

    public int getFlatId() { 
    	return flatId; 
    	}
    
    public void setFlatId(int flatId) { 
    	this.flatId = flatId; 
    	}
}
