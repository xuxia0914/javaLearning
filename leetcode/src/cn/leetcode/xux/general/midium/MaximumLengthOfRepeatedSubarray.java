package cn.leetcode.xux.general.midium;

/**
 * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
 * Example 1:
 * Input:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * Output: 3
 * Explanation:
 * The repeated subarray with maximum length is [3, 2, 1].
 * Note:
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 */
public class MaximumLengthOfRepeatedSubarray {

    /**TLE*/
    public int findLength(int[] A, int[] B) {
        if(A==null||A.length==0||B==null||B.length==0) {
            return 0;
        }
        int lenA = A.length;
        int lenB = B.length;
        for(int i=Math.min(lenA, lenB);i>0;i--) {
            for(int j=0;j<=lenA-i;j++) {
                for(int k=0;k<=lenB-i;k++) {
                    if(helper(A, B, j, k, i)) {
                        return i;
                    }
                }
            }
        }
        return 0;
    }

    public boolean helper(int[] A, int[] B, int startA, int startB, int len) {
        for(int i=0;i<len;i++) {
            if(A[startA++]!=B[startB++]) {
                return false;
            }
        }
        return true;
    }

    public int findLength1(int[] A, int[] B) {
        if(A==null||A.length==0||B==null||B.length==0) {
            return 0;
        }
        int lenA = A.length;
        int lenB = B.length;
        int res = 0;
        int[][] dp = new int[lenA+1][lenB+1];
        for(int i=1;i<=lenA;i++) {
            for(int j=1;j<=lenB;j++) {
                if(A[i-1]==B[j-1]) {
                    dp[i][j] = 1+dp[i-1][j-1];
                    res = Math.max(res, dp[i][j]);
                }else {
                    dp[i][j] = 0;
                }
            }
        }
        return res;
    }

}
