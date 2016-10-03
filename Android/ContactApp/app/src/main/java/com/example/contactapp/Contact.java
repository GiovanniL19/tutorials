package com.example.contactapp;

public class Contact {
    long id;
    String name;
    String telno;

    public Contact() {
    }  
    public Contact(String name,String telno) {
        this.telno = telno;
        this.name = name;
    }
    public int compareTo(Contact p)
    {
            return name.compareTo(p.getName());
    }     
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTelno() {
        return telno;
    }
    public void setTelno(String score) {
        this.telno = score;
    }
    public long getId() {
        return id;
     }
    public void setId(long id) {
       this.id = id;
     }
    public String toString() {
	    return name;
	  }
}
