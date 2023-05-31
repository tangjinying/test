package com.example.test.future;

import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @Description
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        completableFutureTime();
        System.out.println(new Random(100).nextInt(10));
        System.out.println(new Random(100).nextInt(10));
    }


    //统计进度
    public static void completableFutureTime() throws ExecutionException, InterruptedException {
        AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i < 10; i++) {
            CompletableFuture.runAsync(() -> {
                int time = new Random().nextInt(11);
                sleep(time);
                System.out.println("任务执行结束");
            }).thenRun(() -> count.getAndIncrement());
        }

        while (count.get() < 10) {
            sleep(1);
            System.out.println("进度：" + count);
        }
    }




    public static void completableFutureTest8() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1完成！");
            return "future1完成！";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2完成！");
            return "future2完成！";
        });

        CompletableFuture<Void> combindFuture = CompletableFuture.allOf(future1, future2);

        try {
            combindFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }



    //applyToEither 谁快使用谁的结果
    public static void completableFutureTest7() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture
                .supplyAsync(() -> 1)
                .thenApply(integer -> {
                    try {
                        TimeUnit.SECONDS.sleep(new Random(5).nextInt());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return integer * 3;
                });

        CompletableFuture<Integer> future2 = CompletableFuture
                .supplyAsync(() -> 2)
                .thenApply(integer -> {
                    try {
                        TimeUnit.SECONDS.sleep(new Random(5).nextInt());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return integer * 3;
                });

        System.out.println(future1.applyToEither(future2, integer -> integer * 3).get());
    }


    //thenAcceptBoth 消费两个线程任务的结果
    public static void completableFutureTest6() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture
                .supplyAsync(() -> 1)
                .thenCompose(integer -> CompletableFuture.supplyAsync(() -> integer * 3));

        CompletableFuture<Integer> future2 = CompletableFuture
                .supplyAsync(() -> 2)
                .thenCompose(integer -> CompletableFuture.supplyAsync(() -> integer * 3));

        future1.thenAcceptBoth(future2, (x, y) -> {
            System.out.println(x * y);
        });
    }


    //thenAccept
    public static void completableFutureTest5() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture
                .supplyAsync(() -> 1)
                .thenCompose(integer -> CompletableFuture.supplyAsync(() -> integer * 3))
                .thenAccept(integer -> System.out.println(integer * 3));
    }


    //thenCompose
    //thenCompose将内部的CompletableFuture调用展开来并使用上一个CompletableFuture调用的结果在下一步的CompletableFuture调用中进行运算，是生成一个新的CompletableFuture
    public static void completableFutureTest4() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture
                .supplyAsync(() -> 1)
                .thenCompose(integer -> CompletableFuture.supplyAsync(() -> integer * 3));
        System.out.println(future.get());
    }


    //thenApply
    //thenApply转换的是泛型中的类型，返回的是同一个CompletableFuture
    public static void completableFutureTest3() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture
                .supplyAsync(() -> 1)
                .thenApply(i -> {
                    System.out.println(i);
                    return i * 3;
                });
        System.out.println(future.get());
    }


    //exceptionally
    public static void completableFutureTest2() throws ExecutionException, InterruptedException {
        CompletableFuture<Object> future = CompletableFuture.supplyAsync(() -> {
            if (new Random().nextInt(10) % 2 == 0) {
                int i = 12 / 0;
            }
            System.out.println("执行结束！");
            return "hello world!";
        });

        future.whenComplete((o, throwable) -> {
            System.out.println(o + "执行完成！");
        });

        future.exceptionally(throwable -> {
            System.out.println("执行异常：" + throwable.getMessage());
            return "异常";
        });

        future.get();

    }

    //runAsync、supplyAsync
    public static void completableFutureTest1() throws ExecutionException, InterruptedException {
        Runnable runnable = () -> System.out.println("无返回结果异步任务");
        CompletableFuture.runAsync(runnable);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("有返回值的异步任务");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello World";
        });
        String result = future.get();
        System.out.println(result);
    }


    public static void completableFutureTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
        CompletableFuture<String> completableFuture = CompletableFuture
                .supplyAsync(() -> Thread.currentThread().getName(), executorService)
                .thenApply(s -> Thread.currentThread().getName());
        System.out.println(completableFuture.get());
    }


    public static void futureTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
        Future<String> futureA = executorService.submit(() -> Thread.currentThread().getName());
        System.out.println(futureA.get());
        if (futureA.isDone()) {
            Future<String> futureB = executorService.submit(() -> Thread.currentThread().getName());
            System.out.println(futureB.get());
        }
        executorService.shutdown();
    }


    public static void countDownLatchTest() {
        List<Integer> resultList = new ArrayList<>(100);
        IntStream.range(0, 100).forEach(resultList::add);

        List<List<Integer>> split = CollUtil.split(resultList, 10);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1), new ThreadPoolExecutor.CallerRunsPolicy());
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (List<Integer> list : split) {
            executor.execute(() -> {
                list.forEach(i -> {
                    try {
                        // 模拟业务操作
                        Thread.sleep(500);
                        System.out.println("任务进入");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    } finally {
                        System.out.println(countDownLatch.getCount());
                        countDownLatch.countDown();
                    }
                });
            });
        }
        try {
            countDownLatch.await();
            System.out.println("countDownLatch.await()");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(int time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
