package com.craft;

public class RegularStudent implements StudentInt {
    public RegularStudent() {
        System.out.println("RegularStudent object created");
    }

    @Override
    public void getHello() {
        System.out.println("Hello Students");
    }
}