package com.sangeng;

import org.junit.jupiter.api.Test;

/**
 * @Author: junfan
 * @Date: 2023/7/21 17:10
 * @Description:
 */
public class 快排 {

    @Test
    public void test(){
        //
        int[] arr = {1,3,5,7,9,2,4,6,8,10};
        quickSort(arr,0,arr.length-1);
        for (int i : arr) {
            System.out.println(i);
        }
    }

   //实现快排
    public void quickSort(int[] arr,int left,int right){
        if (left < right){
            int partition = partition(arr, left, right);
            quickSort(arr,left,partition-1);
            quickSort(arr,partition+1,right);
        }
    }

    public int partition(int[] arr,int left,int right){
        int pivot = arr[left];
        while (left < right){
            while (left < right && arr[right] >= pivot){
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= pivot){
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }


}
