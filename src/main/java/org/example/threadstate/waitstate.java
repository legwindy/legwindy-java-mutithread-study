package org.example.threadstate;

public class waitstate {
    private static int state = 0;

    public static void main(String[] args) {
        Object lock = new Object();


        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                synchronized (lock) {
                    //等待状态只释放锁，变为等待态（sleep,join,wait状态一致），除非别人叫醒否则一直睡下去，wait()和notify()要承租出现在同步代码块中
                    while (state != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println(0);
                    state = 1;
                    //要用notifyall()，因为有可能有多个线程在等待，notify只能叫醒一个
                    lock.notifyAll();
                }
            }
        });


        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                synchronized (lock) {
                    //while 配合wait检查边界条件,如果非当前线程执行,则wait，让出锁
                    while (state != 1) {
                        try {
                            //等待状态只释放锁，变为等待态（sleep,join,wait状态一致），除非别人叫醒否则一直睡下去，wait()和notify()要承租出现在同步代码块中
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println(1);
                    state = 0;
                    //要用notifyall()，因为有可能有多个线程在等待，notify只能叫醒一个
                    lock.notifyAll();
                }
            }
        });

        thread1.start();
        thread2.start();
    }


}
