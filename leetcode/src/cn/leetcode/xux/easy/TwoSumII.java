package cn.leetcode.xux.easy;

import java.util.Arrays;

/**
 * Given an array of integers that is already sorted in ascending order,
 * find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target,
 * where index1 must be less than index2.
 * Please note that your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution.
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 */
public class TwoSumII {

    public static int[] theResultArray(int[] A, int sum) {
        int left = 0, right = A.length-1;
        while(left<right) {
            int mid = (left+right)/2;
            if(A[left]+A[right]==sum) {
                return new int[]{left, right};
            }else if(A[left]+A[right]>sum) {
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(theResultArray(new int[]{2,3,4,6,7,11,12,14,15}, 30)));
    }

}
