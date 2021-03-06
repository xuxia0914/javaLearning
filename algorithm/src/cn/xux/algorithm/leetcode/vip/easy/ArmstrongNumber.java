package cn.xux.algorithm.leetcode.vip.easy;

/**
 * 1134：阿姆斯特朗数
 * 假设存在一个 k 位数 N，其每一位上的数字的 k 次幂的总和也是 N，那么这个数是阿姆斯特朗数。
 * 给你一个正整数 N，让你来判定他是否是阿姆斯特朗数，是则返回 true，不是则返回 false。
 *
 * 示例 1：
 * 输入：153
 * 输出：true
 * 示例：153 是一个 3 位数，且 153 = 1^3 + 5^3 + 3^3。
 *
 * 示例 2：
 * 输入：123
 * 输出：false
 * 解释：123 是一个 3 位数，且 123 != 1^3 + 2^3 + 3^3 = 36。
 *
 * 提示：
 * 1 <= N <= 10^8
 */
public class ArmstrongNumber {

    public boolean isArmstrong(int N) {
        int k = 0;
        int tmp = N;
        while(tmp>0) {
            k++;
            tmp /= 10;
        }
        tmp = N;
        int sum = 0;
        while(tmp>0) {
            int num = tmp%10;
            tmp /= 10;
            sum += (int)Math.pow(num, k);
        }
        return sum==N;
    }

}
