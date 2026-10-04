package com.test.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Notice {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 int noticeid;
 @Column(nullable = false)
 String title;
 @Column(nullable = false)
 String description;
 int noticeDate;
 @ManyToOne(optional = false)
 @JoinColumn(name = "society_id", nullable = false)
 Society society;
 public Notice() {
	super();
	// TODO Auto-generated constructor stub
 }
 public Notice(int noticeid, String title, String description, int noticeDate, Society society) {
	super();
	this.noticeid = noticeid;
	this.title = title;
	this.description = description;
	this.noticeDate = noticeDate;
	this.society = society;
 }
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
 public Society getSociety() {
	return society;
 }
 public void setSociety(Society society) {
	this.society = society;
 }
 
}
