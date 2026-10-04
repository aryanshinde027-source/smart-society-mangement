package com.test.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Maintaince {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int maintainceid;
	int amount;
	@Column(nullable = false)
	String amountdue;
	int paymentDate;
	@Column(nullable = false)
	String paymentStatus;
	@ManyToOne(optional = false)
	@JoinColumn(name = "flat_id", nullable = false)
	Flat flat;
	public Maintaince() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Maintaince(int maintainceid, int amount, String amountdue, int paymentDate, String paymentStatus,
			Flat flat) {
		super();
		this.maintainceid = maintainceid;
		this.amount = amount;
		this.amountdue = amountdue;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
		this.flat = flat;
	}
	public int getMaintainceid() {
		return maintainceid;
	}
	public void setMaintainceid(int maintainceid) {
		this.maintainceid = maintainceid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getAmountdue() {
		return amountdue;
	}
	public void setAmountdue(String amountdue) {
		this.amountdue = amountdue;
	}
	public int getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(int paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Flat getFlat() {
		return flat;
	}
	public void setFlat(Flat flat) {
		this.flat = flat;
	}

}
