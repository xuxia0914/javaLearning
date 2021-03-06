package cn.xux.algorithm.leetcode.general.midium;

/**
 * 5434. 删掉一个元素以后全为 1 的最长子数组
 * 给你一个二进制数组 nums ，你需要从中删掉一个元素。
 * 请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
 * 如果不存在这样的子数组，请返回 0 。
 *
 * 提示 1：
 * 输入：nums = [1,1,0,1]
 * 输出：3
 * 解释：删掉位置 2 的数后，[1,1,1] 包含 3 个 1 。
 *
 * 示例 2：
 * 输入：nums = [0,1,1,1,0,1,1,0,1]
 * 输出：5
 * 解释：删掉位置 4 的数字后，[0,1,1,1,1,1,0,1] 的最长全 1 子数组为 [1,1,1,1,1] 。
 *
 * 示例 3：
 * 输入：nums = [1,1,1]
 * 输出：2
 * 解释：你必须要删除一个元素。
 *
 * 示例 4：
 * 输入：nums = [1,1,0,0,1,1,1,0,1]
 * 输出：4
 *
 * 示例 5：
 * 输入：nums = [0,0,0]
 * 输出：0
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * nums[i] 要么是 0 要么是 1 。
 */
public class LongestSubarrayOf1sAfterDeletingOneElement {

    public static void main(String[] args) {
        System.out.println(new LongestSubarrayOf1sAfterDeletingOneElement()
            .longestSubarray(new int[]{0,1,1,1,0,1,1,0,1}));
    }

    public int longestSubarray(int[] nums) {
        int ans = 0;
        int n = nums.length;
        int left = 0;
        int cnt0 = nums[0]==0?1:0;
        for(int right=1;right<n;right++) {
            cnt0 += nums[right]==0?1:0;
            if(cnt0<2) {
                ans = Math.max(ans, right-left);
            }
            while(cnt0>1) {
                cnt0 -= nums[left++]==0?1:0;
            }
        }
        return ans;
    }

}
