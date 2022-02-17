package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 2165. 重排数字的最小值
 * 给你一个整数 num 。重排 num 中的各位数字，
 * 使其值 最小化 且不含 任何 前导零。
 * <p>
 * 返回不含前导零且值最小的重排数字。
 * <p>
 * 注意，重排各位数字后，num 的符号不会改变。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：num = 310
 * 输出：103
 * 解释：310 中各位数字的可行排列有：013、031、103、130、301、310 。
 * 不含任何前导零且值最小的重排数字是 103 。
 * 示例 2：
 * <p>
 * 输入：num = -7605
 * 输出：-7650
 * 解释：-7605 中各位数字的部分可行排列为：-7650、-6705、-5076、-0567。
 * 不含任何前导零且值最小的重排数字是 -7650 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * -1015 <= num <= 1015
 */
public class SmallestValueOfTheRearrangedNumber {

    public long smallestNumber(long num) {
        if (num == 0) {
            return num;
        }
        long ans = 0;
        if (num > 0) {
            int n = Long.toString(num).length();
            int[] digit = new int[n];
            for (int i = 0; i < n; i++) {
                digit[i] = (int) (num % 10);
                num /= 10;
            }
            Arrays.sort(digit);
            int no0 = 0;
            while (digit[no0] == 0) {
                no0++;
            }
            int tmp = digit[0];
            digit[0] = digit[no0];
            digit[no0] = tmp;
            for (int j = 0; j < n; j++) {
                ans = ans * 10 + digit[j];
            }
        } else {
            num = -num;
            int n = Long.toString(num).length();
            int[] digit = new int[n];
            for (int i = 0; i < n; i++) {
                digit[i] = (int) (num % 10);
                num /= 10;
            }
            Arrays.sort(digit);
            for (int j = n - 1; j >= 0; j--) {
                ans = ans * 10 + digit[j];
            }
            ans = -ans;
        }
        return ans;
    }

}
