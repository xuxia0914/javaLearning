package cn.xux.algorithm.leetcode.general.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 1703. 得到连续 K 个 1 的最少相邻交换次数
 * 给你一个整数数组 nums 和一个整数 k 。
 * nums 仅包含 0 和 1 。每一次移动，你可以选择 相邻 两个数字并将它们交换。
 * 请你返回使 nums 中包含 k 个 连续 1 的 最少 交换次数。
 *
 * 示例 1：
 * 输入：nums = [1,0,0,1,0,1], k = 2
 * 输出：1
 * 解释：在第一次操作时，nums 可以变成 [1,0,0,0,1,1] 得到连续两个 1 。
 *
 * 示例 2：
 * 输入：nums = [1,0,0,0,0,0,1,1], k = 3
 * 输出：5
 * 解释：通过 5 次操作，最左边的 1 可以移到右边直到 nums 变为 [0,0,0,0,0,1,1,1] 。
 *
 * 示例 3：
 * 输入：nums = [1,1,0,1], k = 2
 * 输出：0
 * 解释：nums 已经有连续 2 个 1 了。
 *
 * 提示：
 * 1 <= nums.length <= 105
 * nums[i] 要么是 0 ，要么是 1 。
 * 1 <= k <= sum(nums)
 */
public class MinimumAdjacentSwapsForKConsecutiveOnes {
    // [1,0,0,1,0,1]
    // 2
    public static void main(String[] args) {
        System.out.println(new MinimumAdjacentSwapsForKConsecutiveOnes()
            .minMoves(new int[]{1,0,0,1,0,1}, 2));
    }

    // 数学+贪心+前缀和
    public int minMoves(int[] nums, int k) {
        int n = nums.length;
        List<Long> list = new ArrayList(n);
        for(int i = 0; i < n; i++){
            if(nums[i] == 1) list.add((long)(i - list.size() - 1));
        }
        long[] sum = new long[list.size() + 1];
        for(int i = 1; i <= list.size(); i++){
            sum[i] = sum[i - 1] + list.get(i - 1);
        }
        long min = Long.MAX_VALUE;
        for(int i = 0; i + k - 1 < list.size(); i++){
            int j = i + k - 1;
            int mid = i + j >> 1;
            long left = list.get(mid) * (mid - i) - (sum[mid] - sum[i]);
            long right = sum[j + 1] - sum[mid + 1] - (j - mid) * list.get(mid);
            min = Math.min(min, left + right);
        }
        return (int)min;
    }

    // TLE
    public int minMoves1(int[] nums, int k) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        for(int start=0;start+k-1<n;start++) {
            int end = start+k-1;
            int left = start;
            int right = end;
            int i = start-1;
            int j = end+1;
            int sum = 0;
            while(left<=right) {
                while(left<=right&&nums[left]==1) {
                    left++;
                }
                while(left<=right&&nums[right]==1) {
                    right--;
                }
                if(left<=right) {
                    while(i>=0&&nums[i]==0) {
                        i--;
                    }
                    while(j<n&&nums[j]==0) {
                        j++;
                    }
                    if(i<0||(j<n&&j-right<left-i)) {
                        sum += j++ - right--;
                    }else {
                        sum += left++ - i--;
                    }
                }
            }
            ans = Math.min(ans, sum);
        }
        return ans;
    }

}
