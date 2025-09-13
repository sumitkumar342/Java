package com.project.java.thread;

public class ThreadClass extends Thread{
    @Override
    public void run(){
        for(int i=0; i<5; i++){
            Thread th = Thread.currentThread();
            System.out.println(i + " -> "+ threadId() + ", Name: "+ getName() + ", Priority: "+getPriority());
            setName("king");
            setPriority(10);
        }
    }
}
