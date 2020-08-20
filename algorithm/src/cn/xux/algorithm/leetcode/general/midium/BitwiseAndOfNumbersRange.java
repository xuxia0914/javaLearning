package cn.xux.algorithm.leetcode.general.midium;

/**
 * 201. 数字范围按位与
 * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
 *
 * 示例 1:
 * 输入: [5,7]
 * 输出: 4
 *
 * 示例 2:
 * 输入: [0,1]
 * 输出: 0
 */
public class BitwiseAndOfNumbersRange {

    public int rangeBitwiseAnd(int m, int n) {
        int mem = 1;
        while(m!=n) {
            m >>= 1;
            n >>= 1;
            mem *= 2;
        }
        return m*mem;
    }

}