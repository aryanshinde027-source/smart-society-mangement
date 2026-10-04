package com.test.Dto;

public class MaintainceRequestDto {
    int maintainceid;
    int amount;
    String amountdue;
    int paymentDate;
    String paymentStatus;
    int flatId;

    public int getMaintainceid() { return maintainceid; }
    public void setMaintainceid(int maintainceid) { this.maintainceid = maintainceid; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public String getAmountdue() { return amountdue; }
    public void setAmountdue(String amountdue) { this.amountdue = amountdue; }

    public int getPaymentDate() { return paymentDate; }
    public void setPaymentDate(int paymentDate) { this.paymentDate = paymentDate; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public int getFlatId() { return flatId; }
    public void setFlatId(int flatId) { this.flatId = flatId; }
}
