package cn.xux.algorithm.leetcode.general.midium;

import java.util.*;

/**
 * 491. 递增子序列
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 *
 * 示例:
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 *
 * 说明:
 * 给定数组的长度不会超过15。
 * 数组中的整数范围是 [-100,100]。
 * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 */
public class IncreasingSubsequences {

    //迭代
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums==null||nums.length<2) {
            return res;
        }
        Set<List<Integer>> curr = new HashSet<>();
        curr.add(new ArrayList<>());
        for(int num : nums) {
            Set<List<Integer>> next = new HashSet<>();
            for(List<Integer> pre : curr) {
                next.add(pre);
                if(pre.size()==0||num>=pre.get(pre.size()-1)) {
                    List<Integer> tmp = new ArrayList<>(pre);
                    tmp.add(num);
                    next.add(tmp);
                }
            }
            curr = next;
        }
        for(List<Integer> list : curr) {
            if(list.size()>1) {
                res.add(list);
            }
        }
        return res;
    }

    //递归
    public List<List<Integer>> findSubsequences1(int[] nums) {
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
