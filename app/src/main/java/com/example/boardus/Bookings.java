package com.example.boardus;

public class Bookings {
    private String Fname,Lname,phoneno,email,birthday,nofrooms,Gender;

    public Bookings(){

    }

    public Bookings(String fname, String lname, String phoneno, String email, String birthday, String nofrooms, String gender) {
        Fname = fname;
        Lname = lname;
        this.phoneno = phoneno;
        this.email = email;
        this.birthday = birthday;
        this.nofrooms = nofrooms;
        Gender = gender;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNofrooms() {
        return nofrooms;
    }

    public void setNofrooms(String nofrooms) {
        this.nofrooms = nofrooms;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
