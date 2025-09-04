package com.project.java;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Countdown countdown = new Countdown();
        countdown.incr();
        System.out.println("Value: " + countdown.value);
        countdown.decr();
        System.out.println("Value: " + countdown.value);
        countdown.incrby(5);
        System.out.println("Value: " + countdown.value);
        countdown.decrby(3);
        System.out.println("Value: " + countdown.value);

    }
}