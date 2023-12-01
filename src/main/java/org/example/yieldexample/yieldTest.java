package org.example.yieldexample;

public class yieldTest {

    public static void main(String[] args) {
        myThread t = new myThread();
        // 设置为守护线程，知识点主线程退出，守护线程也会退出
        t.setDaemon(true);
        t.start();
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "打印:" + i);
        }
    }

    static class myThread extends Thread {
        public void run() {
            while (true) {
                //yield不一定会执行，看CPU心情，不会每次都打印让出CPU执行权
                yield();
                System.out.println(Thread.currentThread().getName() + "让出CPU执行权");
            }
        }
    }
}
