package com.example.homework2.entity;

public class Customer {
    private Long customerId;
    private String customerName;
    private Double totalDebit;

    public Customer() {
    }


    public Customer(String customerName, Double totalDebit) {
        this.customerName = customerName;
        this.totalDebit = totalDebit;
    }

    public Customer(Long customerId, String customerName, Double totalDebit) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.totalDebit = totalDebit;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(Double totalDebit) {
        this.totalDebit = totalDebit;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", totalDebit=" + totalDebit +
                '}';
    }
}
