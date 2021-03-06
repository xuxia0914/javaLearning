package cn.xux.algorithm.leetcode.general.midium;

/**
 * Write a program to find the nth super ugly number.
 * Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.
 * Example:
 * Input: n = 12, primes = [2,7,13,19]
 * Output: 32
 * Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12
 *              super ugly numbers given primes = [2,7,13,19] of size 4.
 * Note:
 * 1 is a super ugly number for any given primes.
 * The given numbers in primes are in ascending order.
 * 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 * The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 */
public class SuperUglyNumber {

    public int nthSuperUglyNumber(int n, int[] primes) {
        if(n<=0||primes==null||primes.length==0) {
            return 0;
        }
        if(n==1) {
            return 1;
        }
        int len = primes.length;
        int[] ns = new int[len];
        int[] res = new int[n];
        res[0] = 1;
        int tmp;
        for(int i=1;i<n;i++) {
            tmp=Integer.MAX_VALUE;
            for(int j=0;j<len;j++) {
                tmp = Math.min(tmp, res[ns[j]]*primes[j]);
            }
            res[i] = tmp;
            for(int j=0;j<len;j++) {
                if(tmp==res[ns[j]]*primes[j]) {
                    ns[j]++;
                }
            }
        }
        return res[n-1];
    }

}
