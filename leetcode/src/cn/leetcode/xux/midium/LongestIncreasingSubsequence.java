package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *Given an unsorted array of integers, find the length of longest increasing subsequence.
 * Example:
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LongestIncreasingSubsequence {

    /**O(n2)*/
    public static int solution1(int[] array) {
        if(array==null||array.length==0) {
            return 0;
        }
        int[] dp = new int[array.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for(int i=1;i<array.length;i++) {
            for(int j=i-1;j>=0;j--) {
                if(array[i]>array[j]) {
                    dp[i] = Math.max(dp[j]+1, dp[i]);
                    max = Math.max(dp[i], max);
                }
            }
        }
        return max;
    }

    /**O(nlogn)*/
    public static int solution2(int[] array) {
        if(array==null||array.length==0) {
            return 0;
        }
        List<Integer> result = new ArrayList<Integer>();
        result.add(array[0]);
        for(int i=1;i<array.length;i++) {
            int left=0,right=result.size()-1;
            if(result.get(right)<array[i]) {
                result.add(array[i]);
            }else {
                while(left<right) {
                    int mid = (left+right)/2;
                    if(result.get(mid)>array[i]) {
                        right = mid;
                    }else {
                        left = mid+1;
                    }
                }
                result.set(left, array[i]);
            }
        }
        return result.size();
    }

    /**O(nlogn) 和方法2一个逻辑，代码量更少, 原地扫描节约空间*/
    public static int solution3(int[] array) {
        if(array==null||array.length==0) {
            return 0;
        }
//        int[] dp = new int[array.length];
        int len = 0;
        for(int x : array) {
            int index = Arrays.binarySearch(array, 0, len, x);
            if(index<0) {
                index = -(index+1);
            }
            array[index] = x;
            if(index==len) {
                len++;
            }
        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println(solution3(new int[]{10,9,2,5,3,7,101,18}));
    }

}
