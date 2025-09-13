package com.project.java.thread;

import java.lang.reflect.Executable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ThreadClass threadClass = new ThreadClass();
        threadClass.start();
        ThreadClass threadClass1 = new ThreadClass();
        threadClass1.start();

//        ThreadInterface threadInterface = new ThreadInterface();
//        Thread th = new Thread(threadInterface);
//        th.start();
//        ExecutorService executable = Executors.newFixedThreadPool(2);
//        executable.submit(new ThreadInterface());
//        executable.submit(new ThreadInterface());
//        executable.submit(new ThreadInterface());
//        executable.submit(new ThreadInterface());

    }
}
