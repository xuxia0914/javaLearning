package cn.xux.algorithm.leetcode.general.hard;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 239. 滑动窗口最大值
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 *
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * 提示：
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 *
 * 进阶：
 * 你能在线性时间复杂度内解决此题吗？
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums==null||nums.length==0||k<=0||k>nums.length) {
            return new int[]{};
        }
        int len = nums.length;
        Deque<Integer> queue = new LinkedList<>();
        int[] result = new int[len-k+1];
        for(int i=0;i<k-1;i++) {
            while(!queue.isEmpty()&&nums[queue.peekLast()]<nums[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        for(int i=k-1;i<len;i++) {
            while(!queue.isEmpty()&&nums[queue.peekLast()]<nums[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);
            if(i-queue.peekFirst()>=k) {
                queue.pollFirst();
            }
            result[i-k+1] = nums[queue.peekFirst()];
        }
        return result;
    }

}
