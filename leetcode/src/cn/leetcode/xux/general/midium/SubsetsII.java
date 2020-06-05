package cn.leetcode.xux.general.midium;

import java.util.*;

/**
 * Given a collection of integers that might contain duplicates, nums,
 * return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 * Example:
 * Input: [1,2,2]
 * Output:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
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
