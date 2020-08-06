package cn.xux.algorithm.leetcode.general.midium;

/**
 * Given a positive integer a, find the smallest positive integer b
 * whose multiplication of each digit equals to a.
 * If there is no answer or the answer is not fit in 32-bit signed integer, then return 0.
 * Example 1
 * Input:
 * 48
 * Output:
 * 68
 * Example 2
 * Input:
 * 15
 * Output:
 * 35
 */
public class MinimumFactorization {

    public int solution(int a) {
        if(a<10) {
            return a;
        }
        long res = 0l;
        long bits = 1;
        for(int i=9;i>1;i--) {
            while(a%i==0) {
                res += (long)i*bits;
                bits *= 10;
                a = a/i;
            }
        }
        if(a!=1) {
            return 0;
        }else if(res>(long)Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }else {
            return (int)res;
        }
    }

}
