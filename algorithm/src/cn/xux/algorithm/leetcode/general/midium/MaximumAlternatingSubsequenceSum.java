package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1911. 最大子序列交替和
 * 一个下标从 0 开始的数组的 交替和 定义为 偶数 下标处元素之 和 减去 奇数 下标处元素之 和 。
 * 比方说，数组 [4,2,5,3] 的交替和为 (4 + 5) - (2 + 3) = 4 。
 * 给你一个数组 nums ，请你返回 nums 中任意子序列的 最大交替和 （子序列的下标 重新 从 0 开始编号）。
 * 一个数组的 子序列 是从原数组中删除一些元素后（也可能一个也不删除）剩余元素不改变顺序组成的数组。
 * 比方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的一个子序列（加粗元素），但是 [2,4,2] 不是。
 *
 * 示例 1：
 * 输入：nums = [4,2,5,3]
 * 输出：7
 * 解释：最优子序列为 [4,2,5] ，交替和为 (4 + 5) - 2 = 7 。
 *
 * 示例 2：
 * 输入：nums = [5,6,7,8]
 * 输出：8
 * 解释：最优子序列为 [8] ，交替和为 8 。
 *
 * 示例 3：
 * 输入：nums = [6,2,1,2,4,5]
 * 输出：10
 * 解释：最优子序列为 [6,1,5] ，交替和为 (6 + 5) - 1 = 10 。
 *
 * 提示：
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */
public class MaximumAlternatingSubsequenceSum {

    public static void main(String[] args) {
        System.out.println(new MaximumAlternatingSubsequenceSum().maxAlternatingSum(
                new int[]{12,6,12,12,7,9,3,20,6,18}
        ));
    }

    // 模拟股票交易
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long ans = 0;
        int pre = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > pre) {//价格比原先买入价格高，就卖出一股，并买入
                ans += nums[i] - pre;
                pre = nums[i];
            } else pre = nums[i];//如果遇到价格更低的，则换成该天买入
        }
        return ans;
    }

    public long maxAlternatingSum1(int[] nums) {
        long ans = 0;
        int l = 0;
        int r = nums.length-1;
        // 首尾元素必是偶数位
        boolean even = true;
        while(l<=r) {
            if(even) {
                // 左右元素往中间遍历直到遇到峰值
                while(l<r&&nums[l]<=nums[l+1]) {
                    l++;
                }
                while(r>l&&nums[r]<=nums[r-1]) {
                    r--;
                }
                ans += nums[l]+(l==r?0:nums[r]);
                even = false;
            }else {
                // 左右元素往中间遍历直到遇到谷值
                while(l<r&&nums[l]>=nums[l+1]) {
                    l++;
                }
                while(r>l&&nums[r]>=nums[r-1]) {
                    r--;
                }
                ans -= nums[l]+(l==r?0:nums[r]);
                even = true;
            }
            l++;
            r--;
        }
        return ans;
    }

}
