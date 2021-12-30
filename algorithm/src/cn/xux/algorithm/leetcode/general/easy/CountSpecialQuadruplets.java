package cn.xux.algorithm.leetcode.general.easy;

/**
 * 1995. 统计特殊四元组
 * 给你一个 下标从 0 开始 的整数数组 nums ，
 * 返回满足下述条件的 不同 四元组 (a, b, c, d) 的 数目 ：
 * <p>
 * nums[a] + nums[b] + nums[c] == nums[d] ，且
 * a < b < c < d
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,6]
 * 输出：1
 * 解释：满足要求的唯一一个四元组是 (0, 1, 2, 3) 因为 1 + 2 + 3 == 6 。
 * 示例 2：
 * <p>
 * 输入：nums = [3,3,6,4,5]
 * 输出：0
 * 解释：[3,3,6,4,5] 中不存在满足要求的四元组。
 * 示例 3：
 * <p>
 * 输入：nums = [1,1,1,3,5]
 * 输出：4
 * 解释：满足要求的 4 个四元组如下：
 * - (0, 1, 2, 3): 1 + 1 + 1 == 3
 * - (0, 1, 3, 4): 1 + 1 + 3 == 5
 * - (0, 2, 3, 4): 1 + 1 + 3 == 5
 * - (1, 2, 3, 4): 1 + 1 + 3 == 5
 * <p>
 * <p>
 * 提示：
 * <p>
 * 4 <= nums.length <= 50
 * 1 <= nums[i] <= 100
 */
public class CountSpecialQuadruplets {

    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int[] cnts = new int[201];
        for (int i = 1; i < n - 2; i++) {
            for (int j = 0; j < i; j++) {
                cnts[nums[i] + nums[j]] += nums[i] + nums[j] < 201 ? 1 : 0;
            }
            for (int k = i + 2; k < n; k++) {
                int tar = nums[k] - nums[i + 1];
                ans += tar > 0 && tar < 201 ? cnts[tar] : 0;
            }
        }
        return ans;
    }

    public int countQuadruplets1(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int l = k + 1; l < n; l++) {
                        ans += nums[i] + nums[j] + nums[k] == nums[l] ? 1 : 0;
                    }
                }
            }
        }
        return ans;
    }

}
