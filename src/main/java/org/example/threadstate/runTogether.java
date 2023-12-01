package org.example.threadstate;

public class runTogether {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("thread1: " + i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("thread2: " + i);
            }
        });

        thread1.start();
        thread2.start();

    }
}
