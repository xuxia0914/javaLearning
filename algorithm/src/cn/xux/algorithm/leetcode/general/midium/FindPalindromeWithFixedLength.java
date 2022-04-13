package cn.xux.algorithm.leetcode.general.midium;

/**
 * 2217. 找到指定长度的回文数
 * 给你一个整数数组 queries 和一个 正 整数 intLength ，
 * 请你返回一个数组 answer ，
 * 其中 answer[i] 是长度为 intLength 的 正回文数 中第 queries[i] 小的数字，
 * 如果不存在这样的回文数，则为 -1 。
 * <p>
 * 回文数 指的是从前往后和从后往前读一模一样的数字。回文数不能有前导 0 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：queries = [1,2,3,4,5,90], intLength = 3
 * 输出：[101,111,121,131,141,999]
 * 解释：
 * 长度为 3 的最小回文数依次是：
 * 101, 111, 121, 131, 141, 151, 161, 171, 181, 191, 202, ...
 * 第 90 个长度为 3 的回文数是 999 。
 * 示例 2：
 * <p>
 * 输入：queries = [2,4,6], intLength = 4
 * 输出：[1111,1331,1551]
 * 解释：
 * 长度为 4 的前 6 个回文数是：
 * 1001, 1111, 1221, 1331, 1441 和 1551 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= queries.length <= 5 * 104
 * 1 <= queries[i] <= 109
 * 1 <= intLength <= 15
 */
public class FindPalindromeWithFixedLength {

    public long[] kthPalindrome(int[] queries, int intLength) {
        int n = queries.length;
        long[] ans = new long[n];
        int start = (int) Math.pow(10, (intLength - 1) / 2);
        int limit = start * 9;
        for (int i = 0; i < n; i++) {
            if (queries[i] > limit) {
                ans[i] = -1;
            } else {
                String leftValue = String.valueOf(start + queries[i] - 1);
                String revStr = new StringBuilder(leftValue).reverse().toString();
                String rightValue = (intLength & 1) == 0 ? revStr : revStr.substring(1);
                ans[i] = Long.parseLong(leftValue + rightValue);
            }
        }
        return ans;
    }

}
