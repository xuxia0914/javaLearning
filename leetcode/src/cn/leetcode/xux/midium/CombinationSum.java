package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 *
 * 示例 1:
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates==null||candidates.length==0||target<1) {
            return result;
        }
        Arrays.sort(candidates);
        helper(result, new ArrayList<>(), 0, candidates, target);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> currList, int currIndex,int[] candidates, int target) {
        if(target==0) {
            result.add(currList);
            return;
        }
        for(int i=currIndex; i<candidates.length;i++) {
            int candidate = candidates[i];
            if(candidate<=target) {
                List<Integer> newCurrList = new ArrayList<>(currList);
                newCurrList.add(candidate);
                helper(result, newCurrList, i, candidates, target-candidate);
            }else {
                break;
            }
        }
    }

}
