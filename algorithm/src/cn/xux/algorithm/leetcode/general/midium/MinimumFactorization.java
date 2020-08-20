package cn.xux.algorithm.leetcode.general.midium;

/**
 * 625. 最小因式分解（贪心）
 * 给定一个正整数 a，找出最小的正整数 b 使得 b 的所有数位相乘恰好等于 a。
 * 如果不存在这样的结果或者结果不是 32 位有符号整数，返回 0。
 *
 * 样例 1
 * 输入：48
 * 输出：68
 *
 * 样例 2
 * 输入：15
 * 输出：35
 */
public class MinimumFactorization {

    public int smallestFactorization(int a) {
        if(a<10) {
            return a;
        }
        long res = 0L;
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
