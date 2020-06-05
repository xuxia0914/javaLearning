package cn.leetcode.xux.general.midium;

import java.util.*;

/**
 * Given an integer array,
 * your task is to find all the different possible increasing subsequences of the given array,
 * and the length of an increasing subsequence should be at least 2.
 * Example:
 * Input: [4, 6, 7, 7]
 * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * Note:
 * The length of the given array will not exceed 15.
 * The range of integer in the given array is [-100,100].
 * The given array may contain duplicates,
 * and two equal integers should also be considered as a special case of increasing sequence.
 */
public class IncreasingSubsequences {

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums==null||nums.length<2) {
            return res;
        }
        List<Integer> numList = new ArrayList<>();
        for(int i : nums) {
            numList.add(i);
        }
        for(List<Integer> list : helper(numList)) {
            if(list.size()>1) {
                res.add(list);
            }
        }
        return res;
    }

    public Set<List<Integer>> helper(List<Integer> nums) {
        Set<List<Integer>> res = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        int num = nums.get(nums.size()-1);
        list.add(num);
        res.add(list);
        if(nums.size()==1) {
            return res;
        }
        Set<List<Integer>> pre = helper(nums.subList(0, nums.size()-1));
        for(List<Integer> curr : pre) {
            res.add(curr);
            if(num>=curr.get(curr.size()-1)) {
                List<Integer> newList = new ArrayList<>(curr);
                newList.add(num);
                res.add(newList);
            }
        }
        return res;
    }

}
