package com.example.anthonsteiness.firebasetest2;

/**
 * Created by Anthon Steiness on 14-05-2017.
 */

public class User
{
    private String mail, cvr, fName, lName;

    public User(String mail, String cvr, String fName, String lName) {
        this.mail = mail;
        this.cvr = cvr;
        this.fName = fName;
        this.lName = lName;
    }

    public String getMail() {
        return mail;
    }

    public String getCvr() {
        return cvr;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
}
