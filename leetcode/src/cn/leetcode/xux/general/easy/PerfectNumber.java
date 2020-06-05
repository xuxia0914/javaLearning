package cn.leetcode.xux.general.easy;

/**
 * 507. 完美数
 * 对于一个 正整数，如果它和除了它自身以外的所有正因子之和相等，我们称它为“完美数”。
 * 给定一个 整数 n， 如果他是完美数，返回 True，否则返回 False
 *
 * 示例：
 * 输入: 28
 * 输出: True
 * 解释: 28 = 1 + 2 + 4 + 7 + 14
 *
 * 提示：
 * 输入的数字 n 不会超过 100,000,000. (1e8)
 */
public class PerfectNumber {

    public boolean checkPerfectNumber(int num) {
        if(num<1) {
            return false;
        }
        int target = 0;
        for(int curr=(int)Math.sqrt(num);curr>0;curr--) {
            if(num%curr==0) {
                if(curr!=num) {
                    target += curr;
                }
                int tmp = num/curr;
                if(tmp!=num&&tmp!=curr) {
                    target += num/curr;
                }
            }
        }
        return target==num;
    }

}
