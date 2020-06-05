package cn.leetcode.xux.general.midium;

import java.util.Arrays;

/**
 * Given an array A of integers, for each integer A[i] we need to choose either x = -K or x = K,
 * and add x to A[i] (only once).
 * After this process, we have some array B.
 * Return the smallest possible difference between the maximum value of B and the minimum value of B.
 * Example 1:
 * Input: A = [1], K = 0
 * Output: 0
 * Explanation: B = [1]
 * Example 2:
 * Input: A = [0,10], K = 2
 * Output: 6
 * Explanation: B = [2,8]
 * Example 3:
 * Input: A = [1,3,6], K = 3
 * Output: 3
 * Explanation: B = [4,6,3]
 * Note:
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * 0 <= K <= 10000
 */
public class SmallestRangeII {

    public static int solution(int[] A, int K) {
        if(A==null||A.length<2) {
            return 0;
        }
        Arrays.sort(A);
        int n= A.length;
        int min = A[0];
        int max = A[n-1];
        int res = max-min;
        if(K<0) {
            K = -K;
        }
        if(K==0) {
            return res;
        }
        for(int i=0;i<n-1;i++) {
            max = Math.max(A[i]+K, A[n-1]-K);
            min = Math.min(A[0]+K, A[i+1]-K);
            res = Math.min(max-min, res);
        }
        return res;
    }

}
