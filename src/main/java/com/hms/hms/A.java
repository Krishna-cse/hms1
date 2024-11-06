package com.hms.hms;

public class A {
    public static void main(String[] args) {
        String x1 = "mike";
        String s1 = "mike";
        String x2 = new String("mike");
        System.out.println(x1);
        System.out.println(x2);

        //String pool
        System.out.println(x1==s1);
        System.out.println(x1.equals(x2));
    }
}
