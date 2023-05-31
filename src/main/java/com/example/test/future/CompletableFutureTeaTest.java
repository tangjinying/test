package com.example.test.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 */
public class CompletableFutureTeaTest {

    public static void main(String[] args) {

        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {

            System.out.println("T1:洗水壶...");
            sleep(1);

            System.out.println("T1:烧水...");
            sleep(15);
        });


        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2:洗茶壶...");
            sleep(1);

            System.out.println("T2:洗茶杯...");
            sleep(2);

            System.out.println("T2:拿茶叶...");
            sleep(1);

            return "龙井";
        });

        CompletableFuture<String> f3 = f1.thenCombine(f2, (non, tea) -> {
            System.out.println("T1:烧好水...");
            System.out.println("T1:拿到茶叶:" + tea);
            System.out.println("T1:泡茶...");
            return "上茶:" + tea;
        });

//        CompletableFuture<Void> f3 = f1.thenAcceptBoth(f2, (non, tea) -> {
//            System.out.println(tea);
//        });

        try {
            System.out.println(f3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    static void sleep(int t){
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) {
        }
    }

}
