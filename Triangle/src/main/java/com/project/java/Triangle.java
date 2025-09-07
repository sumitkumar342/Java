package com.project.java;

public class Triangle {
    static boolean checkTriangle(String[] strArr){
        int x1 = Integer.parseInt(strArr[0]);
        int y1 = Integer.parseInt(strArr[1]);
        int x2 = Integer.parseInt(strArr[2]);
        int y2 = Integer.parseInt(strArr[3]);
        int x3 = Integer.parseInt(strArr[4]);
        int y3 = Integer.parseInt(strArr[5]);
        double area = Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0;
        return area > 0;
    }

    static double perimeter(String[] strArr){
        int x1 = Integer.parseInt(strArr[0]);
        int y1 = Integer.parseInt(strArr[1]);
        int x2 = Integer.parseInt(strArr[2]);
        int y2 = Integer.parseInt(strArr[3]);
        int x3 = Integer.parseInt(strArr[4]);
        int y3 = Integer.parseInt(strArr[5]);
        double side1 = Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2));
        double side2 = Math.sqrt(Math.pow((x3-x2), 2) + Math.pow((y3-y2), 2));
        double side3 = Math.sqrt(Math.pow((x1-x3), 2) + Math.pow((y1-y3), 2));
        return side1 + side2 + side3;
    }
}
