package cn.xux.algorithm.leetcode.general.midium;

/**
 * We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.
 * The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].
 * The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].
 * Return true if and only if the number of global inversions is equal to the number of local inversions.
 * Example 1:
 * Input: A = [1,0,2]
 * Output: true
 * Explanation: There is 1 global inversion, and 1 local inversion.
 * Example 2:
 * Input: A = [1,2,0]
 * Output: false
 * Explanation: There are 2 global inversions, and 1 local inversion.
 * Note:
 * A will be a permutation of [0, 1, ..., A.length - 1].
 * A will have length in range [1, 5000].
 * The time limit for this problem has been reduced.
 */
public class GlobalAndLocalInversions {

    /**
     * Runtime: 308 ms, faster than 15.16% of Java online submissions for Global and Local Inversions.
     * Memory Usage: 49.2 MB, less than 35.82% of Java online submissions for Global and Local Inversions.
     */
    public boolean isIdealPermutation1(int[] A) {
        if(A==null||A.length<3) {
            return true;
        }
        if(globalInversion(A)==localInversion(A)) {
            return true;
        }else {
            return false;
        }
    }

    int globalInversion(int[] A) {
        int res = 0;
        for(int i=0;i<A.length-1;i++) {
            if(A[i]>A[i+1]) {
                res++;
            }
        }
        return res;
    }

    int localInversion(int[] A) {
        int res = 0;
        for(int i=0;i<A.length-1;i++) {
            for(int j=i+1;j<A.length;j++) {
                if(A[i]>A[j]) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * Runtime: 2 ms, faster than 95.37% of Java online submissions for Global and Local Inversions.
     * Memory Usage: 41.6 MB, less than 90.23% of Java online submissions for Global and Local Inversions.
     * @param A
     * @return
     */
    public boolean isIdealPermutation12(int[] A) {
        if(A==null||A.length<3) {
            return true;
        }
        int tmp = Integer.MIN_VALUE;
        for(int i=0;i<A.length-1;i++) {
            if(A[i]>A[i+1]) {
                if(i>0&&A[i-1]>A[i+1]) {
                    return false;
                }
                if(i<A.length-2&&A[i]>A[i+2]) {
                    return false;
                }
                if(tmp>A[i+1]) {
                    return false;
                }
                tmp = A[i];
            }
        }
        return true;
    }

}
