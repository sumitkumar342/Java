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
        System.out.println(area);
        return area > 0;
    }
}
