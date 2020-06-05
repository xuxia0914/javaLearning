package cn.leetcode.xux.general.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 * 输入: [1,2,3]
 * 输出:
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

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums==null||nums.length==0) {
            return result;
        }
        helper(result, new ArrayList<>(), new boolean[nums.length], nums);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> curr, boolean[] flags, int[] nums) {
        if(curr.size()==nums.length) {
            result.add(curr);
            return;
        }
        for(int i=0;i<flags.length;i++) {
            if(!flags[i]) {
                List<Integer> tmp = new ArrayList<>(curr);
                tmp.add(nums[i]);
                flags[i] = true;
                helper(result, tmp, flags, nums);
                flags[i] = false;
            }
        }
    }

}
