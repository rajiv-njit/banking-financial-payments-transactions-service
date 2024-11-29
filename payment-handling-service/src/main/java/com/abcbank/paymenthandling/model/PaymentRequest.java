package com.abcbank.paymenthandling.model;

public class PaymentRequest {

    private String account;
    private String bankName;
    private String fromAccount;
    private String toAccount;
    private double amount;
 
    
    //Getter and Setters
    public String getAccount(){
        return account;
    }

    public void setAccount(String account){
        this.account=account;
    }

    public String getBankName(){
        return bankName;
    }

    public void setBankName(String bankName){
        this.bankName=bankName;
    }

    public String getFromAccount(){
        return fromAccount;
    }

    public void setFromAccount(String fromAccount){
        this.fromAccount=fromAccount;
    }

    public String getToAccount(){
        return toAccount;
    }

    public void setToAccount(String toAccount){
    this.toAccount=toAccount;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amount){
        this.amount=amount;
    }
}
