package cn.xux.algorithm.leetcode.general.easy;

/**
 * 507. 完美数
 * 对于一个 正整数，如果它和除了它自身以外的所有正因子之和相等，我们称它为“完美数”。
 * 给定一个 整数 n， 如果他是完美数，返回 True，否则返回 False
 * <p>
 * 示例：
 * 输入: 28
 * 输出: True
 * 解释: 28 = 1 + 2 + 4 + 7 + 14
 * <p>
 * 提示：
 * 输入的数字 n 不会超过 100,000,000. (1e8)
 */
public class PerfectNumber {

    public boolean checkPerfectNumber(int num) {
        int sum = 0;
        for (int i = (int) Math.sqrt(num); i >= 1; i--) {
            if (num % i == 0) {
                sum += i == num ? 0 : i;
                int j = num / i;
                sum += j == num ? 0 : j;
            }
        }
        return sum == num;
    }

}
