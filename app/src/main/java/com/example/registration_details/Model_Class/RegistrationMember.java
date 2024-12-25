package com.example.registration_details.Model_Class;

public class RegistrationMember {
    private String name;
    private String mobileNumber;
    private String role;
    private double subscriptionFee;
    private double depositAmount;
    private double loanAmount;

    public RegistrationMember(String name, String mobileNumber, String role, double subscriptionFee, double depositAmount, double loanAmount) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.role = role;
        this.subscriptionFee = subscriptionFee;
        this.depositAmount = depositAmount;
        this.loanAmount = loanAmount;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getRole() {
        return role;
    }

    public double getSubscriptionFee() {
        return subscriptionFee;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public double getLoanAmount() {
        return loanAmount;
    }
}