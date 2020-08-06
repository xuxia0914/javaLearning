package cn.xux.algorithm.leetcode.general.midium;

/**
 * 264. 丑数 II
 * 编写一个程序，找出第 n 个丑数。
 * 丑数就是只包含质因数 2, 3, 5 的正整数。
 *
 * 示例:
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 *
 * 说明:
 * 1 是丑数。
 * n 不超过1690。
 *
 * 分析
 * 根据提示中的信息，我们知道丑陋序列可以拆分成3个子序列：
 * 1x2, 2x2, 3x2, 4x2, 5x2, …
 * 1x3, 2x3, 3x3, 4x3, 5x3, …
 * 1x5, 2x5, 3x5, 4x5, 5x5, …
 * 每次从三个列表中取出当前最小的那个加入序列，直到第n个为止。
 */
public class UglyNumberII {

    public static int nthUglyNumber(int n) {
        if(n<0) {
            return 0;
        }
        if(n<2) {
            return n;
        }
        int[] res = new int[n];
        res[0] = 1;
        int n2=0, n3=0, n5=0;
        for(int i=1;i<n;i++) {
            res[i] = Math.min(Math.min(res[n2]*2, res[n3]*3), res[n5]*5);
            if(res[i]==res[n2]*2) {
                n2++;
            }
            if(res[i]==res[n3]*3) {
                n3++;
            }
            if(res[i]==res[n5]*5) {
                n5++;
            }
        }
        return res[n-1];
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(11));
    }

}
