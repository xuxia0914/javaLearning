package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1712. 将数组分成三个子数组的方案数
 * 我们称一个分割整数数组的方案是 好的 ，当它满足：
 * 数组被分成三个 非空 连续子数组，从左至右分别命名为 left ， mid ， right 。
 * left 中元素和小于等于 mid 中元素和，mid 中元素和小于等于 right 中元素和。
 * 给你一个 非负 整数数组 nums ，请你返回 好的 分割 nums 方案数目。
 * 由于答案可能会很大，请你将结果对 109 + 7 取余后返回。
 *
 * 示例 1：
 * 输入：nums = [1,1,1]
 * 输出：1
 * 解释：唯一一种好的分割方案是将 nums 分成 [1] [1] [1] 。
 *
 * 示例 2：
 * 输入：nums = [1,2,2,2,5,0]
 * 输出：3
 * 解释：nums 总共有 3 种好的分割方案：
 * [1] [2] [2,2,5,0]
 * [1] [2,2] [2,5,0]
 * [1,2] [2,2] [5,0]
 *
 * 示例 3：
 * 输入：nums = [3,2,1]
 * 输出：0
 * 解释：没有好的分割方案。
 *
 * 提示：
 * 3 <= nums.length <= 105
 * 0 <= nums[i] <= 104
 */
public class WaysToSplitArrayIntoThreeSubarrays {

    public static void main(String[] args) {
        System.out.println(new WaysToSplitArrayIntoThreeSubarrays()
                .waysToSplit(new int[]{1,2,2,2,5,0}));
    }

    public int waysToSplit(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n];
        preSum[0] = nums[0];
        for(int i=1;i<n;i++) {
            preSum[i] = preSum[i-1]+nums[i];
        }
        long ans = 0;
        int i = 0;
        while(i<n-2&&preSum[i]<=preSum[n-1]/3) {
            // 二分查询找到最左边符合条件的分段位置
            int left = i+1;
            int right = n-1;
            int mid1;
            while(left<right) {
                mid1 = (left+right)/2;
                if(preSum[mid1]-preSum[i]>=preSum[i]) {
                    right = mid1;
                }else {
                    left = mid1+1;
                }
            }
            mid1 = left;
            // 二分查询找到最右边符合条件的分段位置
            left = i+1;
            right = n-1;
            int mid2;
            while(left<right) {
                mid2 = (left+right+1)/2;
                if(preSum[n-1]-preSum[mid2]>=preSum[mid2]-preSum[i]) {
                    left = mid2;
                }else {
                    right = mid2-1;
                }
            }
            mid2 = left;
            // 分段的位置为 mid1~mid2 时都是“好的”结果
            // 当mid2的位置的n-1时，mid2(即n-1)这个分段位置不符合条件
            if(mid2>=mid1) {
                ans = (ans+mid2-mid1+(mid2==n-1?0:1))%1000000007;
            }
            i++;
        }
        return (int)ans;
    }

}
