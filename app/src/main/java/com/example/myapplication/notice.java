package com.example.myapplication;

public class notice {
    String userid;
    String name;
    String age;
    String gender;
    String test;
    String urine;


    public notice(String userid, String name, String age, String gender, String test, String urine) {
        this.userid = userid;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.test = test;
        this.urine = urine;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getUrine() {
        return urine;
    }

    public void setUrine(String urine) {
        this.urine = urine;
    }
}
