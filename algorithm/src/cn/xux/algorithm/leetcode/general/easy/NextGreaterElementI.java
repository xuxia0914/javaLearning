package cn.xux.algorithm.leetcode.general.easy;

import java.util.Arrays;
import java.util.Stack;

/**
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2.
 * Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
 * The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2.
 * If it does not exist, output -1 for this number.
 * Example 1:
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * Output: [-1,3,-1]
 * Explanation:
 *     For number 4 in the first array, you cannot find the next greater number for it in the second array,so output -1.
 *     For number 1 in the first array, the next greater number for it in the second array is 3.
 *     For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
 * Example 2:
 * Input: nums1 = [2,4], nums2 = [1,2,3,4].
 * Output: [3,-1]
 * Explanation:
 *     For number 2 in the first array, the next greater number for it in the second array is 3.
 *     For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
 * Note:
 * All elements in nums1 and nums2 are unique.
 * The length of both nums1 and nums2 would not exceed 1000.
 */
public class NextGreaterElementI {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if(nums1==null||nums1.length==0) {
            return new int[]{};
        }
        int[] greaters = new int[nums2.length];
        Arrays.fill(greaters, -1);
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<nums2.length;i++) {
            while(!stack.isEmpty()&&nums2[i]>nums2[stack.peek()]) {
                greaters[stack.pop()] = nums2[i];
            }
            stack.push(i);
        }
        int[] res = new int[nums1.length];
        for(int i=0;i<nums1.length;i++) {
            for(int j=0;j<nums2.length;j++) {
                if(nums1[i]==nums2[j]) {
                    res[i] = greaters[j];
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new NextGreaterElementI().nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2});
    }

}
