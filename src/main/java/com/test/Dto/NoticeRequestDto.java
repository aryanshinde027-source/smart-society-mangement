package com.test.Dto;

public class NoticeRequestDto {
    String title;
    String description;
    int noticeDate;
    int societyId;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getNoticeDate() { return noticeDate; }
    public void setNoticeDate(int noticeDate) { this.noticeDate = noticeDate; }

    public int getSocietyId() { return societyId; }
    public void setSocietyId(int societyId) { this.societyId = societyId; }
}
