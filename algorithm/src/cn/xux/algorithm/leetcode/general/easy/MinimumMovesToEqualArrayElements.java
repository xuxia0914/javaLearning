package cn.xux.algorithm.leetcode.general.easy;

import java.util.Arrays;

/**
 * 453. 最小移动次数使数组元素相等
 * 给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。每次移动可以使 n - 1 个元素增加 1。
 *
 * 示例:
 * 输入:[1,2,3]
 * 输出:3
 * 解释:
 * 只需要3次移动（注意每次移动会增加两个元素的值）：
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 */
public class MinimumMovesToEqualArrayElements {

    /**
     * O(n),除了一个元素之外的全部元素+1，等价于将该元素-1
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        if(nums==null||nums.length<2) {
            return 0;
        }
        int min = nums[0];
        for(int num : nums) {
            min = Math.min(min, num);
        }
        int res = 0;
        for(int num : nums) {
            res += num-min;
        }
        return res;
    }

}
