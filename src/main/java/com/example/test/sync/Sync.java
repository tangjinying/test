package com.example.test.sync;

/**
 * @Description
 */
public class Sync {

    private Object lock = new Object();
    private Object lock2 = new Object();

    //synchronized修饰方法。同一个类实例中的其他同步方法会被阻塞。（用了同一个锁）
    public synchronized void lock1() {
        System.out.println("lock1 start" + System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("lock1 end.." + System.currentTimeMillis());
    }


    public synchronized void lock2() {
        System.out.println("lock2 start" + System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("lock2 end.." + System.currentTimeMillis());
    }

    public void lock3() {
        synchronized (lock) {
            System.out.println("lock3 start" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("lock3 end.." + System.currentTimeMillis());
        }
    }


    public void lock4() {
        synchronized (lock2) {
            System.out.println("lock4 start" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("lock4 end.." + System.currentTimeMillis());
        }
    }
}
