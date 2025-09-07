package com.project.java;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the end point in format(0,0,0,4,3,4)");
        String str = sc.nextLine();
        String[] strArr = str.replaceAll("\\s", "").split(",");
        boolean tringale = Triangle.checkTriangle(strArr);
        if(tringale){
            System.out.println("This is a triangle.");
        } else {
            System.out.println("This is a triangle.");
            System.exit(0);
        }
        System.out.println("Perimeter is: " + Triangle.perimeter(strArr));
    }
}