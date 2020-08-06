package cn.xux.algorithm.leetcode.general.hard;

/**
 * 483. 最小好进制
 * 对于给定的整数 n, 如果n的k（k>=2）进制数的所有数位全为1，
 * 则称 k（k>=2）是 n 的一个好进制。
 * 以字符串的形式给出 n, 以字符串的形式返回 n 的最小好进制。
 *
 * 示例 1：
 * 输入："13"
 * 输出："3"
 * 解释：13 的 3 进制是 111。
 *
 * 示例 2：
 * 输入："4681"
 * 输出："8"
 * 解释：4681 的 8 进制是 11111。
 *
 * 示例 3：
 * 输入："1000000000000000000"
 * 输出："999999999999999999"
 * 解释：1000000000000000000 的 999999999999999999 进制是 11。
 *
 * 提示：
 * n的取值范围是 [3, 10^18]。
 * 输入总是有效且没有前导 0。
 */
public class SmallestGoodBase {

    public String smallestGoodBase(String n) {
        long N = Long.parseLong(n);
        for (int m = 59; m > 1; m--) {
            long k = (long) Math.pow(N, 1.0 / m);
            //不存在1进制，如果k<=1，直接下一次
            if (k <= 1)
                continue;
            long s = 0;
            for (int i = 0; i <= m; i++)
                s = s * k + 1;
            if (s == N)
                return String.valueOf(k);
        }
        return String.valueOf(N - 1);
    }

}
