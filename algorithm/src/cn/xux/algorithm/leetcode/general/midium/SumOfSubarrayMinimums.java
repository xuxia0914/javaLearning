package cn.xux.algorithm.leetcode.general.midium;

import java.util.Stack;

/**
 * Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.
 * Since the answer may be large, return the answer modulo 10^9 + 7.
 * Example 1:
 * Input: [3,1,2,4]
 * Output: 17
 * Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.
 * Note:
 * 1 <= A.length <= 30000
 * 1 <= A[i] <= 30000
 */
public class SumOfSubarrayMinimums {

    public int sumSubarrayMins(int[] A) {
        if(A==null||A.length==0) {
            return 0;
        }
        int len = A.length;
        int[] pres = new int[len];
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<len;i++) {
            while(!stack.isEmpty()&&A[i]<=A[stack.peek()]) {
                stack.pop();
            }
            pres[i] = stack.isEmpty()?-1:stack.peek();
            stack.push(i);
        }

        int[] nexts = new int[len];
        stack = new Stack<>();
        for(int i=len-1;i>=0;i--) {
            while(!stack.isEmpty()&&A[i]<A[stack.peek()]) {
                stack.pop();
            }
            nexts[i] = stack.isEmpty()?len:stack.peek();
            stack.push(i);
        }

        long res = 0;
        for(int i=0;i<len;i++) {
            res += (long)A[i]*(i-pres[i])*(nexts[i]-i);
        }
        return (int)(res%1000000007);
    }

    public int sumSubarrayMins1(int[] A) {
        if(A==null||A.length==0) {
            return 0;
        }
        int len = A.length;
        long res = 0;
        for(int i=0;i<len;i++) {
            int left = i-1;
            while(left>=0&&A[left]>=A[i]) {
                left--;
            }
            int right = i+1;
            while(right<len&&A[right]>A[i]) {
                right++;
            }
            res += (long)A[i]*(i-left)*(right-i);
        }
        return (int)(res%1000000007);
    }

    public static void main(String[] args) {
        SumOfSubarrayMinimums ssm = new SumOfSubarrayMinimums();
//        System.out.println(ssm.sumSubarrayMins(new int[]{3,1,2,4}));  //17
//        System.out.println(ssm.sumSubarrayMins(new int[]{97,61,59,45}));    //576
//        System.out.println(ssm.sumSubarrayMins(new int[]{71,55,82,55}));    //593
        System.out.println(ssm.sumSubarrayMins1(new int[]{48,87,27}));    //264
    }

}
