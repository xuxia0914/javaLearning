package cn.xux.algorithm.leetcode.general.midium;

/**
 * 2091. 从数组中移除最大值和最小值
 * 给你一个下标从 0 开始的数组 nums ，数组由若干 互不相同 的整数组成。
 *
 * nums 中有一个值最小的元素和一个值最大的元素。分别称为 最小值 和 最大值 。
 * 你的目标是从数组中移除这两个元素。
 *
 * 一次 删除 操作定义为从数组的 前面 移除一个元素或从数组的 后面 移除一个元素。
 *
 * 返回将数组中最小值和最大值 都 移除需要的最小删除次数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,10,7,5,4,1,8,6]
 * 输出：5
 * 解释：
 * 数组中的最小元素是 nums[5] ，值为 1 。
 * 数组中的最大元素是 nums[1] ，值为 10 。
 * 将最大值和最小值都移除需要从数组前面移除 2 个元素，从数组后面移除 3 个元素。
 * 结果是 2 + 3 = 5 ，这是所有可能情况中的最小删除次数。
 * 示例 2：
 *
 * 输入：nums = [0,-4,19,1,8,-2,-3,5]
 * 输出：3
 * 解释：
 * 数组中的最小元素是 nums[1] ，值为 -4 。
 * 数组中的最大元素是 nums[2] ，值为 19 。
 * 将最大值和最小值都移除需要从数组前面移除 3 个元素。
 * 结果是 3 ，这是所有可能情况中的最小删除次数。
 * 示例 3：
 *
 * 输入：nums = [101]
 * 输出：1
 * 解释：
 * 数组中只有这一个元素，那么它既是数组中的最小值又是数组中的最大值。
 * 移除它只需要 1 次删除操作。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -105 <= nums[i] <= 105
 * nums 中的整数 互不相同
 */
public class RemovingMinimumAndMaximumFromArray {

    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int min = nums[0];
        int minIdx = 0;
        int max = nums[0];
        int maxIdx = 0;
        for(int i=1;i<n;i++) {
            if(nums[i]<min) {
                min = nums[i];
                minIdx = i;
            }
            if(nums[i]>max) {
                max = nums[i];
                maxIdx = i;
            }
        }
        int left = Math.min(minIdx, maxIdx);
        int right = Math.max(minIdx, maxIdx);
        return Math.min(left+1+n-right, Math.min(right+1, n-left));
    }

}
