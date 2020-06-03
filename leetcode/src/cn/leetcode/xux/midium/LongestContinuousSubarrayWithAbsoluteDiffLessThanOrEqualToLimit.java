package cn.leetcode.xux.midium;

import java.util.*;

/**
 * 1438. 绝对差不超过限制的最长连续子数组
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，
 * 该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
 * 如果不存在满足条件的子数组，则返回 0 。
 *
 * 示例 1：
 * 输入：nums = [8,2,4,7], limit = 4
 * 输出：2
 * 解释：所有子数组如下：
 * [8] 最大绝对差 |8-8| = 0 <= 4.
 * [8,2] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
 * [2] 最大绝对差 |2-2| = 0 <= 4.
 * [2,4] 最大绝对差 |2-4| = 2 <= 4.
 * [2,4,7] 最大绝对差 |2-7| = 5 > 4.
 * [4] 最大绝对差 |4-4| = 0 <= 4.
 * [4,7] 最大绝对差 |4-7| = 3 <= 4.
 * [7] 最大绝对差 |7-7| = 0 <= 4.
 * 因此，满足题意的最长子数组的长度为 2 。
 *
 * 示例 2：
 * 输入：nums = [10,1,2,4,7,2], limit = 5
 * 输出：4
 * 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
 *
 * 示例 3：
 * 输入：nums = [4,2,2,2,4,4,2,2], limit = 0
 * 输出：3
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= limit <= 10^9
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    public static void main(String[] args) {
        System.out.println(new LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit()
                .longestSubarray1(new int[]{4,2,2,2,4,4,2,2}, 0));
    }

    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxQ = new LinkedList<>();
        Deque<Integer> minQ = new LinkedList<>();
        int start = 0;
        int ans = 0;
        for(int end=0;end<nums.length;end++) {
            while(!maxQ.isEmpty()&&nums[maxQ.peekLast()]<nums[end]) {
                maxQ.pollLast();
            }
            maxQ.offerLast(end);
            while(!minQ.isEmpty()&&nums[minQ.peekLast()]>nums[end]) {
                minQ.pollLast();
            }
            minQ.offerLast(end);
            while(!maxQ.isEmpty()&&!minQ.isEmpty()&&nums[maxQ.peek()]-nums[minQ.peek()]>limit) {
                if(maxQ.peek()<minQ.peek()) {
                    start = maxQ.poll()+1;
                }else {
                    start = minQ.poll()+1;
                }
            }
            ans = Math.max(ans, end-start+1);
        }
        return ans;
    }

    public int longestSubarray1(int[] nums, int limit) {
        TreeSet<Integer> tree = new TreeSet<>(
                (o1,o2)->nums[o1]==nums[o2]?o1-o2:nums[o1]-nums[o2]);
        int start = 0;
        int ans = 0;
        for(int end=0;end<nums.length;end++) {
            tree.add(end);
            while(nums[tree.last()]-nums[tree.first()]>limit) {
                tree.remove(start++);
            }
            ans = Math.max(ans, end-start+1);
        }
        return ans;
    }

}
