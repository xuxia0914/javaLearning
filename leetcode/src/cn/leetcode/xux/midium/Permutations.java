package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.
 * Example:
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class Permutations {

    public static List<List<Integer>> solution(List<Integer> intList) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(intList.size()<2) {
            result.add(intList);
            return result;
        }
        Integer value = intList.get(0);
        List<List<Integer>> pre = solution(intList.subList(1, intList.size()));
        for(int i=0;i<pre.size();i++) {
            List<Integer> tmp1 = pre.get(i);
            for(int j=0;j<=tmp1.size();j++) {
                List<Integer> tmp2 = new ArrayList<Integer>();
                tmp2.addAll(tmp1);
                tmp2.add(j, value);
                result.add(tmp2);
            }
        }
        return result;
    }

    public static List<List<Integer>> solution2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums==null||nums.length==0) {
            return res;
        }
        helper(res, new ArrayList<Integer>(), new boolean[nums.length], nums);
        return res;
    }

    public static void helper(List<List<Integer>> res, List<Integer> curr, boolean[] flags, int[] nums) {
        if(curr.size()==nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for(int i=0;i<nums.length;i++) {
            if(!flags[i]) {
                curr.add(nums[i]);
                flags[i] = true;
                helper(res, curr, flags, nums);
                curr.remove(curr.size()-1);
                flags[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        /*List<Integer> intList = new ArrayList<Integer>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
        System.out.println(solution(intList));*/
        System.out.println(solution2(new int[]{1,2,3,4}));
    }

}
