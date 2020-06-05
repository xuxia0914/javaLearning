package cn.leetcode.xux.general.midium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * Example:
 * Input: [1,1,2]
 * Output:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */
public class PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums==null||nums.length==0) {
            return res;
        }
        Arrays.sort(nums);
        helper(res, new ArrayList<Integer>(), new boolean[nums.length], nums);
        return res;

    }

    public static void helper(List<List<Integer>> res, List<Integer> curr, boolean[] flags, int[] nums) {
        if(curr.size()==nums.length) {
            res.add(new ArrayList<>(curr));
        }
        for(int i=0;i<nums.length;i++) {
            if(!flags[i]) {
                curr.add(nums[i]);
                flags[i] = true;
                helper(res, curr, flags, nums);
                curr.remove(curr.size()-1);
                flags[i] = false;
                while(i<nums.length-1&&nums[i+1]==nums[i]) {
                    i++;
                }
            }
        }
    }

}
