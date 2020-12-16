package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1685. 有序数组中差绝对值之和
 * 给你一个 非递减 有序整数数组 nums 。
 * 请你建立并返回一个整数数组 result，它跟 nums 长度相同，
 * 且result[i] 等于 nums[i] 与数组中所有其他元素差的绝对值之和。
 * 换句话说， result[i] 等于 sum(|nums[i]-nums[j]|) ，
 * 其中 0 <= j < nums.length 且 j != i （下标从 0 开始）。
 *
 * 示例 1：
 * 输入：nums = [2,3,5]
 * 输出：[4,3,5]
 * 解释：假设数组下标从 0 开始，那么
 * result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4，
 * result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3，
 * result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5。
 *
 * 示例 2：
 * 输入：nums = [1,4,6,8,10]
 * 输出：[24,15,13,15,21]
 *
 * 提示：
 * 2 <= nums.length <= 105
 * 1 <= nums[i] <= nums[i + 1] <= 104
 */
public class SumOfAbsoluteDifferencesInASortedArray {

    public static void main(String[] args) {
        new SumOfAbsoluteDifferencesInASortedArray().getSumAbsoluteDifferences(
                new int[]{2,3,5}
        );
    }

    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n];
        preSum[0] = nums[0];
        int[] postSum = new int[n];
        postSum[n-1] = nums[n-1];
        for(int i=1;i<n;i++) {
            preSum[i] = preSum[i-1]+nums[i];
            postSum[n-1-i] = postSum[n-i]+nums[n-1-i];
        }
        int[] ans = new int[n];
        ans[0] = postSum[1]-nums[0]*(n-1);
        ans[n-1] = nums[n-1]*(n-1)-preSum[n-2];
        for(int i=1;i<n-1;i++) {
            ans[i] = nums[i]*i-preSum[i-1]+(postSum[i+1]-nums[i]*(n-1-i));
        }
        return ans;
    }

}
