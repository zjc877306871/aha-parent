package com.aha.tom.entity;

public class User {
    private String name;
    private int age;
    private Double monney;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getMonney() {
        return monney;
    }

    public void setMonney(Double monney) {
        this.monney = monney;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User(String name, int age, Double monney, String address) {
        this.name = name;
        this.age = age;
        this.monney = monney;
        this.address = address;
    }
}