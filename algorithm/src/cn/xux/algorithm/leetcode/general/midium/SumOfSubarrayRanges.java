package cn.xux.algorithm.leetcode.general.midium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 2104. 子数组范围和
 * 给你一个整数数组 nums 。nums 中，子数组的 范围 是子数组中最大元素和最小元素的差值。
 * 返回 nums 中 所有 子数组范围的 和 。
 * 子数组是数组中一个连续 非空 的元素序列。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：4
 * 解释：nums 的 6 个子数组如下所示：
 * [1]，范围 = 最大 - 最小 = 1 - 1 = 0
 * [2]，范围 = 2 - 2 = 0
 * [3]，范围 = 3 - 3 = 0
 * [1,2]，范围 = 2 - 1 = 1
 * [2,3]，范围 = 3 - 2 = 1
 * [1,2,3]，范围 = 3 - 1 = 2
 * 所有范围的和是 0 + 0 + 0 + 1 + 1 + 2 = 4
 *
 * 示例 2：
 * 输入：nums = [1,3,3]
 * 输出：4
 * 解释：nums 的 6 个子数组如下所示：
 * [1]，范围 = 最大 - 最小 = 1 - 1 = 0
 * [3]，范围 = 3 - 3 = 0
 * [3]，范围 = 3 - 3 = 0
 * [1,3]，范围 = 3 - 1 = 2
 * [3,3]，范围 = 3 - 3 = 0
 * [1,3,3]，范围 = 3 - 1 = 2
 * 所有范围的和是 0 + 0 + 0 + 2 + 0 + 2 = 4
 *
 * 示例 3：
 * 输入：nums = [4,-2,-3,4,1]
 * 输出：59
 * 解释：nums 中所有子数组范围的和是 59
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * -109 <= nums[i] <= 109
 */
public class SumOfSubarrayRanges {

    public static void main(String[] args) {
        System.out.println(new SumOfSubarrayRanges().subArrayRanges(
                new int[]{1,2,3}
        ));
    }

    // o(n) o(n)
    // 单调栈+贡献度
    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        Deque<Integer> deque1 = new LinkedList<>();
        Deque<Integer> deque2 = new LinkedList<>();
        // 单调严格递增栈
        deque1.offerLast(-1);
        // 单调严格递减栈
        deque2.offerLast(-1);
        // leftSmaller[i] 表示nums[i]的紧左边有多少个连续的数是小于nums[i]的(+1)
        int[] leftSmaller = new int[n];
        // leftBigger[i] 表示nums[i]的紧左边有多少个连续的数是大于nums[i]的(+1)
        int[] leftBigger = new int[n];
        for(int i=0;i<n;i++) {
            while(deque1.peekLast()!=-1&&nums[deque1.peekLast()]<nums[i]) {
                deque1.pollLast();
            }
            leftSmaller[i] = i-deque1.peekLast();
            deque1.offerLast(i);

            while(deque2.peekLast()!=-1&&nums[deque2.peekLast()]>nums[i]) {
                deque2.pollLast();
            }
            leftBigger[i] = i-deque2.peekLast();
            deque2.offerLast(i);
        }
        deque1 = new LinkedList<>();
        deque2 = new LinkedList<>();
        deque1.offerLast(n);
        deque2.offerLast(n);
        // rightSmaller[i] 表示nums[i]的紧右边有多少个连续的数是小于等于nums[i]的(+1)
        int[] rightSmaller = new int[n];
        // rightBigger[i] 表示nums[i]的紧右边有多少个连续的数是大于等于nums[i]的(+1)
        int[] rightBigger = new int[n];
        for(int i=n-1;i>=0;i--) {
            // 因为nums中有重复元素，所以当遇到相等的元素时，
            // 我们统一取左边的元素(即这里设定为小于等于而不是小于)，避免重复计算贡献度
            while(deque1.peekLast()!=n&&nums[deque1.peekLast()]<=nums[i]) {
                deque1.pollLast();
            }
            rightSmaller[i] = deque1.peekLast()-i;
            deque1.offerLast(i);

            while(deque2.peekLast()!=n&&nums[deque2.peekLast()]>=nums[i]) {
                deque2.pollLast();
            }
            rightBigger[i] = deque2.peekLast()-i;
            deque2.offerLast(i);
        }
        long ans = 0;
        for(int i=0;i<n;i++) {
            ans += (long)nums[i]*(leftSmaller[i]*rightSmaller[i]-leftBigger[i]*rightBigger[i]);
        }
        return ans;
    }

    // o(n^2) o(1)
    public long subArrayRanges1(int[] nums) {
        int n = nums.length;
        long ans = 0;
        for(int i=0;i<n-1;i++) {
            int max = nums[i];
            int min = nums[i];
            for(int j=i+1;j<n;j++) {
                max = Math.max(max, nums[j]);
                min = Math.min(min, nums[j]);
                ans += max-min;
            }
        }
        return ans;
    }

}
