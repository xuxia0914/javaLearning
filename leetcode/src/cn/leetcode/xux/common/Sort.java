package cn.leetcode.xux.common;

import java.util.Arrays;

public class Sort {
    /**
     * 冒泡排序
     */
    public static void bubbleSort(int[] ia, boolean isAsc) {
        if(ia==null||ia.length<2) {
            return ;
        }
        int tmp;
        if(isAsc) {
            for(int i=0;i<ia.length-1;i++) {
                for(int j=0;j<ia.length-i-1;j++) {
                    if(ia[j]>ia[j+1]) {
                        tmp = ia[j];
                        ia[j] = ia[j+1];
                        ia[j+1] = tmp;
                    }
                }
            }
        }else {
            for(int i=0;i<ia.length-1;i++) {
                for(int j=0;j<ia.length-i-1;j++) {
                    if(ia[j]<ia[j+1]) {
                        tmp = ia[j];
                        ia[j] = ia[j+1];
                        ia[j+1] = tmp;
                    }
                }
            }
        }
    }

    /**
     * 快速排序
     */
    public static void quickSort(int[] arr,int low,int high) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int i,j,temp,t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        //temp就是基准位
        temp = arr[low];

        while (i<j) {
            //先看右边，依次往左递减
            while (temp<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp>=arr[i]&&i<j) {
                i++;
            }
            //如果满足条件则交换
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);

    }

}
