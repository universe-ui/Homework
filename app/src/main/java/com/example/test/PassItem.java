package com.example.test;

public class PassItem {
    private String account;
    private String password;
    public PassItem(){
        super();
        account = "";
        password = "";
    }
    public PassItem(String account,String password){
        super();
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
