package cn.xux.algorithm.leetcode.general.midium;

import java.util.*;

/**
 * 90. 子集 II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 * 示例 1：
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 */
public class SubsetsII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if(nums==null) {
            return null;
        }
        Arrays.sort(nums);
        Set<List<Integer>> set = helper(nums, nums.length-1);
        return new ArrayList<>(set);

    }


    public Set<List<Integer>> helper(int[] nums, int end) {
        Set<List<Integer>> res = new HashSet<>();
        if(end==-1) {
            res.add(new ArrayList<Integer>());
            return res;
        }
        Set<List<Integer>> preRse = helper(nums, end-1);
        res.addAll(preRse);
        for(List<Integer> list : preRse) {
            List<Integer> tmp = new ArrayList<>(list);
            tmp.add(nums[end]);
            res.add(tmp);
        }
        return res;
    }

    public static void main(String[] args) {
        SubsetsII s2 = new SubsetsII();
        System.out.println(s2.subsetsWithDup(new int[]{1,2,2}));
        System.out.println(s2.subsetsWithDup(new int[]{4,4,4,1,4}));
    }

}
