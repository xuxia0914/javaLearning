package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 1674. 使数组互补的最少操作次数
 * 给你一个长度为 偶数 n 的整数数组 nums 和一个整数 limit 。
 * 每一次操作，你可以将 nums 中的任何整数替换为 1 到 limit 之间的另一个整数。
 * 如果对于所有下标 i（下标从 0 开始），nums[i] + nums[n - 1 - i] 都等于同一个数，
 * 则数组 nums 是 互补的 。例如，数组 [1,2,3,4] 是互补的，因为对于所有下标 i ，
 * nums[i] + nums[n - 1 - i] = 5 。
 * 返回使数组 互补 的 最少 操作次数。
 *
 * 示例 1：
 * 输入：nums = [1,2,4,3], limit = 4
 * 输出：1
 * 解释：经过 1 次操作，你可以将数组 nums 变成 [1,2,2,3]（加粗元素是变更的数字）：
 * nums[0] + nums[3] = 1 + 3 = 4.
 * nums[1] + nums[2] = 2 + 2 = 4.
 * nums[2] + nums[1] = 2 + 2 = 4.
 * nums[3] + nums[0] = 3 + 1 = 4.
 * 对于每个 i ，nums[i] + nums[n-1-i] = 4 ，所以 nums 是互补的。
 *
 * 示例 2：
 * 输入：nums = [1,2,2,1], limit = 2
 * 输出：2
 * 解释：经过 2 次操作，你可以将数组 nums 变成 [2,2,2,2] 。你不能将任何数字变更为 3 ，因为 3 > limit 。
 *
 * 示例 3：
 * 输入：nums = [1,2,1,2], limit = 2
 * 输出：0
 * 解释：nums 已经是互补的。
 *
 * 提示：
 * n == nums.length
 * 2 <= n <= 105
 * 1 <= nums[i] <= limit <= 105
 * n 是偶数。
 */
public class MinimumMovesToMakeArrayComplementary {

    // 差分数组+前缀和 O(n+limit)
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        // cnts[i] 表示使互补数组nums[i]+nums[n-i-1]==i时需要的操作次数的差分数组
        // (差分数组是前缀和的逆运算)
        int[] cnts = new int[2*limit+2];
        for(int i=0;i<n/2;i++) {
            int a = Math.min(nums[i], nums[n-i-1]);
            int b = Math.max(nums[i], nums[n-i-1]);
            // 当前数对操作一次之后的和的范围是（a+1, b+limit）
            cnts[a+1]--;
            cnts[b+limit+1]++;
            // 当前数对操作零次之后的和的范围是 (a+b, a+b)
            cnts[a+b] --;
            cnts[a+b+1] ++;
        }
        int ans = 0;
        int preSum = 0;
        for(int cnt : cnts) {
            preSum += cnt;
            ans = Math.min(ans, preSum);
        }
        return ans+n;
    }

    // 暴力 O(n*limit) TLE
    public int minMoves1(int[] nums, int limit) {
        int n= nums.length;
        int[] moves = new int[limit*2+1];
        int ans = n;
        for(int i=2;i<=limit*2;i++) {
            for(int j=0;j<n/2;j++) {
                int a = Math.min(nums[j], nums[n-1-j]);
                int b = Math.max(nums[j], nums[n-1-j]);
                if(a+b!=i) {
                    if(i>=a+1&&i<=b+limit) {
                        moves[i]++;
                    }else {
                        moves[i] += 2;
                    }
                }
            }
            ans = Math.min(ans, moves[i]);
        }
        return ans;
    }

}
