package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1780. 判断一个数字是否可以表示成三的幂的和
 * 给你一个整数 n ，如果你可以将 n 表示成若干个不同的三的幂之和，
 * 请你返回 true ，否则请返回 false 。
 * 对于一个整数 y ，如果存在整数 x 满足 y == 3x ，我们称这个整数 y 是三的幂。
 *
 * 示例 1：
 * 输入：n = 12
 * 输出：true
 * 解释：12 = 31 + 32
 *
 * 示例 2：
 * 输入：n = 91
 * 输出：true
 * 解释：91 = 30 + 32 + 34
 *
 * 示例 3：
 * 输入：n = 21
 * 输出：false
 *
 * 提示：
 * 1 <= n <= 107
 */
public class CheckIfNumberIsASumOfPowersOfThree {

    public static void main(String[] args) {
        System.out.println(new CheckIfNumberIsASumOfPowersOfThree().checkPowersOfThree(27));
    }

    public boolean checkPowersOfThree(int n) {
        int bit = 1;
        while(bit*3<=n) {
            bit *= 3;
        }
        while(bit>0&&n>0) {
            if(n>=bit) {
                n -= bit;
            }
            bit /= 3;
        }
        return n==0;
    }

}
