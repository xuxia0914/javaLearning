package cn.leetcode.xux.general.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
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
        if(nums==null||nums.length==0) {
            return result;
        }
        helper(new ArrayList<>(), new boolean[nums.length], nums);
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();

    public void helper(List<Integer> curr, boolean[] visited, int[] nums) {
        if(curr.size()==nums.length) {
            result.add(curr);
            return;
        }
        for(int i=0;i<visited.length;i++) {
            if(!visited[i]) {
                List<Integer> next = new ArrayList<>(curr);
                next.add(nums[i]);
                visited[i] = true;
                helper(next, visited, nums);
                visited[i] = false;
            }
        }
    }

}
