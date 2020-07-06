package cn.leetcode.xux.general.midium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */
public class PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        if(nums==null||nums.length==0) {
            return ans;
        }
        Arrays.sort(nums);
        dfs(new ArrayList<Integer>(), new boolean[nums.length], nums);
        return ans;
    }

    List<List<Integer>> ans = new ArrayList<>();

    public void dfs(List<Integer> curr, boolean[] visited, int[] nums) {
        if(curr.size()==nums.length) {
            ans.add(new ArrayList<>(curr));
        }
        for(int i=0;i<nums.length;i++) {
            if(!visited[i]) {
                curr.add(nums[i]);
                visited[i] = true;
                dfs(curr, visited, nums);
                curr.remove(curr.size()-1);
                visited[i] = false;
                while(i<nums.length-1&&nums[i+1]==nums[i]) {
                    i++;
                }
            }
        }
    }

}
