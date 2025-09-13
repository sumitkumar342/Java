package com.project.java.thread;

public class ThreadInterface implements Runnable{

    @Override
    public void run(){
        for (int i=0; i<5; i++){
            System.out.println(Thread.currentThread().threadId() + " " + Thread.currentThread().getName() + " " + Thread.currentThread().getPriority());
        }
    }
}
