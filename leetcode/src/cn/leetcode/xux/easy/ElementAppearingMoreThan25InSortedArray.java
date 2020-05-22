package cn.leetcode.xux.easy;

import java.util.Arrays;

/**
 * 1287. 有序数组中出现次数超过25%的元素
 * 给你一个非递减的 有序 整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。
 * 请你找到并返回这个整数
 *
 * 示例：
 * 输入：arr = [1,2,2,6,6,6,6,7,10]
 * 输出：6
 *
 * 提示：
 * 1 <= arr.length <= 10^4
 * 0 <= arr[i] <= 10^5
 */
public class ElementAppearingMoreThan25InSortedArray {

    //O(logn)
    public int findSpecialInteger(int[] arr) {
        int n = arr.length;
        int target = n/4+1;
        for(int i=0;i<n;i+=target) {
            int left = 0;
            int right = n-1;
            while(left<right) {
                int mid = (left+right)/2;
                if(arr[mid]<arr[i]) {
                    left = mid+1;
                }else {
                    right = mid;
                }
            }
            int start = left;
            left = 0;
            right = n-1;
            while(left<right) {
                int mid = (left+right+1)/2;
                if(arr[mid]>arr[i]) {
                    right = mid-1;
                }else {
                    left = mid;
                }
            }
            int end = left;
            if(end-start+1>=target) {
                return arr[i];
            }
        }
        return -1;
    }

    //O(n)
    public int findSpecialInteger1(int[] arr) {
        int n = arr.length;
        int target = n/4+1;
        int cnt = 1;
        for(int i=1;i<n;i++) {
            if(arr[i]==arr[i-1]) {
                cnt++;
            }else {
                cnt = 1;
            }
            if(cnt==target) {
                return arr[i-1];
            }
        }
        return arr[n-1];
    }

}
