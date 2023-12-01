package org.example.threadstate;

public class blockstate {


    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("线程1进入同步方法区,预计业务逻辑执行10秒");
                try {
                    Thread.sleep(10000);
                    System.out.println("线程1业务逻辑执行完毕");

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        Thread thread2 = new Thread(() -> {
            System.out.println("线程2开始尝试进入同步方法区");
            synchronized (lock) {
                System.out.println("线程2进入同步方法区");
            }
        });


        thread1.start();

        Thread.sleep(2000);

        thread2.start();


    }


}
