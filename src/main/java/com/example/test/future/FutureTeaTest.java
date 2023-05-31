package com.example.test.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 */
public class FutureTeaTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> ft2 = new FutureTask<>(new TeaTask2());
        FutureTask<String> ft1 = new FutureTask<>(new TeaTask1(ft2));

        Thread thread1 = new Thread(ft1);
        thread1.start();

        Thread thread2 = new Thread(ft2);
        thread2.start();

        System.out.println(ft1.get());

    }



    static class TeaTask1 implements Callable<String> {

        private FutureTask<String> ft2;

        TeaTask1(FutureTask ft2) {
            this.ft2 = ft2;
        }

        @Override
        public String call() throws Exception {

            System.out.println("t1: 洗水壶");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("t1: 烧开水");
            TimeUnit.SECONDS.sleep(15);

            String tf = ft2.get();
            System.out.println("t1: 拿到茶叶");

            System.out.println("T1:泡茶...");
            return "上茶:" + tf;
        }
    }



    static class TeaTask2 implements Callable<String> {


        @Override
        public String call() throws Exception {

            System.out.println("T2:洗茶壶");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T2:洗茶杯...");
            TimeUnit.SECONDS.sleep(2);

            System.out.println("T2:拿茶叶...");
            TimeUnit.SECONDS.sleep(1);
            return "龙井";
        }
    }
}
