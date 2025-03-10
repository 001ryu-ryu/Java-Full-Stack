package org.example.proxies.classes;

public class Man implements Person{

    private String name;
    private int age;
    private String city;
    private String country;

    public Man(String name, int age, String city, String country) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    @Override
    public void introduce(String name) {
        System.out.printf("My name is %s", name);
    }

    @Override
    public void sayAge(int age) {
        System.out.printf("My age is %d", age);
    }

    @Override
    public void sayWhereFrom(String city, String country) {
        System.out.printf("My city is %s and country is %s", city, country);
    }
}
