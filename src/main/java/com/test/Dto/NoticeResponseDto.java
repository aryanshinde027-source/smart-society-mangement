package com.test.Dto;

public class NoticeResponseDto {
	

	    int noticeid;
	    String title;
	    String description;
	    int noticeDate;

	    SocietyDto society;

	    public int getNoticeid() {
	        return noticeid;
	    }

	    public void setNoticeid(int noticeid) {
	        this.noticeid = noticeid;
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

	    public int getNoticeDate() {
	        return noticeDate;
	    }

	    public void setNoticeDate(int noticeDate) {
	        this.noticeDate = noticeDate;
	    }

	    public SocietyDto getSociety() {
	        return society;
	    }

	    public void setSociety(SocietyDto society) {
	        this.society = society;
	    }

	}

