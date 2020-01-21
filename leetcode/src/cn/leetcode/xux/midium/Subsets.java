package cn.leetcode.xux.midium;

import java.util.LinkedList;
import java.util.List;

/**
 * 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if(nums==null||nums.length==0) {
            return result;
        }
        subsets(result, new LinkedList<Integer>(), nums, 0);
        return result;
    }

    private void subsets(List<List<Integer>> result, List<Integer> curr, int[] nums, int index) {
        if(index==nums.length) {
            result.add(curr);
            return;
        }
        List<Integer> list1 = new LinkedList<>(curr);
        subsets(result, list1, nums, index+1);
        List<Integer> list2 = new LinkedList<>(curr);
        list2.add(nums[index]);
        subsets(result, list2, nums, index+1);
    }

}
