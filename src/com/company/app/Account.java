package com.company.app;

import com.company.database.NotNull;

import java.lang.reflect.Field;

public class Account {

    private String accountnumber;
    @NotNull(message = "must be a name")
    private String accountHolder;
    private String ssn;
    private String balance = "0";

    public Account(){

    }

    public Account(String accountnumber, String accountHolder, String ssn) {
        this.accountnumber = accountnumber;
        this.accountHolder = accountHolder;
        this.ssn = ssn;
    }

    public Account(String accountnumber, String accountHolder, String ssn, String balance) {
        this.accountnumber = accountnumber;
        this.accountHolder = accountHolder;
        this.ssn = ssn;
        this.balance = balance;
    }

    public void withdraw(double amount){
        this.balance =String.valueOf(Double.parseDouble(balance)-amount);
    }

    public void deposit(double amount){
        this.balance = String.valueOf(Double.parseDouble(balance)+amount);

    }

    public void transfer(Account account, double amount){
        this.withdraw(amount);
        account.deposit(amount);
    }

    public String getBalance() {
        return balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getSsn() {
        return ssn;
    }

    @Override
    public String toString(){
        return  accountnumber
                + "/" + accountHolder
                + "/" + ssn
                + "/" + balance;
    }

    public void getFields (Object obj) {
        try {
            Field[] fields = obj.getClass().getDeclaredFields();

            for (Field f : fields) {
                NotNull fieldAnnotation = f.getAnnotation(NotNull.class);
                if (fieldAnnotation != null) {
                    f.get(obj);
                }
            }
        }catch (NullPointerException | IllegalAccessException ne ){

        }

        }


}
