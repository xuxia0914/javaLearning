package cn.leetcode.xux.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).
 * Example 1:
 * Input: [1,3,5,4,7]
 * Output: 3
 * Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
 * Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.
 * Example 2:
 * Input: [2,2,2,2,2]
 * Output: 1
 * Explanation: The longest continuous increasing subsequence is [2], its length is 1.
 * Note: Length of the array will not exceed 10,000.
 */
public class LongestContinuousIncreasingSubsequence {

    public static List<Integer> solution(int[] array) {
        List<Integer> result = new ArrayList<Integer>();
        if(array==null||array.length==0) {
            return result;
        }
        List<Integer> list = new ArrayList<Integer>();
        list.add(array[0]);
        for(int i=1;i<array.length;i++) {
            if(array[i]>array[i-1]) {
                list.add(array[i]);
            }else if(list.size()>result.size()) {
                result = new ArrayList<Integer>(list);
                list.clear();
                list.add(array[i]);
            }
        }
        if(list.size()>result.size()) {
            result = new ArrayList<Integer>(list);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,3,5,4,7}));
        System.out.println(solution(new int[]{2,2,2,2,2}));
    }

}
