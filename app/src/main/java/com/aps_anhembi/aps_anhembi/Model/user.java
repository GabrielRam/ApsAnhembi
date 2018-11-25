package com.aps_anhembi.aps_anhembi.Model;

public class user {
    private String Name;
    private String mail;
    private Settings settings;
    private String Password;
    public user(){

    }
    public user(String Name, String mail,String Password){
        this.mail=mail;
        this.Name=Name;
        this.setPassword(Password);
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
