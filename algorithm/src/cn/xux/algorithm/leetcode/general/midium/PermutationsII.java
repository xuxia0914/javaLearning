package cn.xux.algorithm.leetcode.general.midium;

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
        n = nums.length;
        int[] cnts = new int[21];
        for(int num : nums) {
            cnts[num+10]++;
        }
        dfs(cnts, new ArrayList<>());
        return ans;
    }

    int n;
    List<List<Integer>> ans = new ArrayList<>();

    private void dfs(int[] nums, List<Integer> curr) {
        if(curr.size()==n) {
            ans.add(curr);
            return;
        }
        for(int i=0;i<21;i++) {
            if(nums[i]>0) {
                List<Integer> next = new ArrayList<>(curr);
                next.add(i-10);
                nums[i]--;
                dfs(nums, next);
                nums[i]++;
            }
        }
    }

}
