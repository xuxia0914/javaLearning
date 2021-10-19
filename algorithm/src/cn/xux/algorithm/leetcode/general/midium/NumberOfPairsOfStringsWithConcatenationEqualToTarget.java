package cn.xux.algorithm.leetcode.general.midium;

/**
 * 2023. 连接后等于目标字符串的字符串对
 * 给你一个 数字 字符串数组 nums 和一个 数字 字符串 target ，
 * 请你返回 nums[i] + nums[j] （两个字符串连接）结果等于 target 的下标 (i, j)
 * （需满足 i != j）的数目。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = ["777","7","77","77"], target = "7777"
 * 输出：4
 * 解释：符合要求的下标对包括：
 * - (0, 1)："777" + "7"
 * - (1, 0)："7" + "777"
 * - (2, 3)："77" + "77"
 * - (3, 2)："77" + "77"
 * 示例 2：
 * <p>
 * 输入：nums = ["123","4","12","34"], target = "1234"
 * 输出：2
 * 解释：符合要求的下标对包括
 * - (0, 1)："123" + "4"
 * - (2, 3)："12" + "34"
 * 示例 3：
 * <p>
 * 输入：nums = ["1","1","1"], target = "11"
 * 输出：6
 * 解释：符合要求的下标对包括
 * - (0, 1)："1" + "1"
 * - (1, 0)："1" + "1"
 * - (0, 2)："1" + "1"
 * - (2, 0)："1" + "1"
 * - (1, 2)："1" + "1"
 * - (2, 1)："1" + "1"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 100
 * 1 <= nums[i].length <= 100
 * 2 <= target.length <= 100
 * nums[i] 和 target 只包含数字。
 * nums[i] 和 target 不含有任何前导 0 。
 */
public class NumberOfPairsOfStringsWithConcatenationEqualToTarget {

    public int numOfPairs(String[] nums, String target) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && target.equals(nums[i] + nums[j])) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
