package com.mebank.txn.bean;

import java.time.LocalDateTime;

public class Balance {

    String accountNo;
    LocalDateTime startDate;
    LocalDateTime endDate;
    int numberOfTransactions;
    Double balance;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public void setNumberOfTransactions(int numberOfTransactions) {
        this.numberOfTransactions = numberOfTransactions;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }


    @Override
    public String toString() {
        return "Balance{" +
                "accountNo='" + accountNo + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", numberOfTransactions=" + numberOfTransactions +
                ", balance=" + balance +
                '}';
    }
}
