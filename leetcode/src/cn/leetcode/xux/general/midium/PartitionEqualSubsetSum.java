package cn.leetcode.xux.general.midium;

/**
 * 416. 分割等和子集
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 *
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * 示例 2:
 * 输入: [1, 2, 3, 5]
 * 输出: false
 *
 * 解释: 数组不能分割成两个元素和相等的子集.
 */
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        if(nums==null||nums.length<2) {
            return false;
        }
        int sum = 0;
        for(int i : nums) {
            sum += i;
        }
        if(sum%2==1) {
            return false;
        }
        int target = sum/2;
        boolean[] dp = new boolean[target+1];
        dp[0] = true;
        for(int num : nums) {
            for(int i=target-num;i>=0;i--) {
                if(dp[i]) {
                    if(i+num<target) {
                        dp[i+num] = true;
                    }else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        PartitionEqualSubsetSum pess = new PartitionEqualSubsetSum();
        System.out.println(pess.canPartition(new int[]{1,2,5}));
    }

}
