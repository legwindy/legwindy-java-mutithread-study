package org.example.threadstate;

public class runAfterAnother {
    public static void main(String[] args) throws InterruptedException {
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
        //学习点：join()方法，让thread1执行完毕后再执行thread2
        thread1.join();
        thread2.start();
    }
}
