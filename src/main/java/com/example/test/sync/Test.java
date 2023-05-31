package com.example.test.sync;

/**
 * @Description
 */
public class Test {



    public static void main(String[] args) {
        Sync sync = new Sync();

        Thread thread = new Thread(() -> sync.lock3());
        thread.start();

        sync.lock4();
    }

}
