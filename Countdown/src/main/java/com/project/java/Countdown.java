package com.project.java;

public class Countdown {
    int value = 0;

    void incr(){
        this.value = value+1;
    }
    void decr(){
        this.value = value-1;
    }
    void incrby(int n){
        this.value += n;
    }
    void decrby(int n){
        this.value -= n;
    }
}
