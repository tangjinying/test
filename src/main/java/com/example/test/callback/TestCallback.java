package com.example.test.callback;

/**
 * @Description
 */
public class TestCallback {

    public static void main(String[] args){
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        String result = producer.consumer(consumer);
//        System.out.println(result);
    }
}
