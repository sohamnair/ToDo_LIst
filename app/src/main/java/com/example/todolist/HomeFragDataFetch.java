package com.example.todolist;

public class HomeFragDataFetch {

    String Name, Dob, Age, Contact, Email, Pass;

    HomeFragDataFetch() {}

    public HomeFragDataFetch(String name, String dob, String age, String contact, String email, String pass) {
        this.Name = name;
        this.Dob = dob;
        this.Age = age;
        this.Contact = contact;
        this.Email = email;
        this.Pass = pass;
    }

    public String getName() {
        return Name;
    }

    public String getDob() {
        return Dob;
    }

    public String getAge() {
        return Age;
    }

    public String getContact() {
        return Contact;
    }

    public String getEmail() {
        return Email;
    }

    public String getPass() {
        return Pass;
    }
}
