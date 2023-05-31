package com.example.test.sort;

import java.util.Arrays;

/**
 * @Description
 */
public class Sort {


    public static void main(String[] args) {
        int arr[] = {1, 4, 5, 3, 7, 9, 6};
        BubbleSort(arr);
    }

    /* 冒泡排序 */
    static void BubbleSort(int arr[]) { // arr： 需要排序的数组； length： 数组长度 注： int cnt = sizeof(a) / sizeof(a[0]);获取数组长度

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp;
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println("冒泡排序结果：" + Arrays.toString(arr));
    }



    /* 选择排序 */
    static void selectSort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
        }
        System.out.println("选择排序结果：" + Arrays.toString(arr));
    }
}
