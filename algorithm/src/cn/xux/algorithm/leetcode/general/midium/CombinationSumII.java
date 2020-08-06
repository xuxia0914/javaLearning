package cn.xux.algorithm.leetcode.general.midium;

import java.util.*;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 * Each number in candidates may only be used once in the combination.
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * Example 2:
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
public class CombinationSumII {
    /**
     * Runtime: 14 ms, faster than 14.62% of Java online submissions for Combination Sum II.
     * Memory Usage: 40.9 MB, less than 19.22% of Java online submissions for Combination Sum II.
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Set<List<Integer>> res = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();
        if(candidates==null||candidates.length==0) {
            return result;
        }
        Arrays.sort(candidates);
        helper(candidates, 0, new ArrayList<>(), target, res);
        result.addAll(res);
        return result;
    }

    public void helper(int[] candidates, int start, List<Integer> list, int n, Set<List<Integer>> res) {
        if(n==0) {
            res.add(new ArrayList<>(list));
        }
        for(int i=start;i<candidates.length;i++) {
            if(candidates[i]<=n) {
                List<Integer> tmp = new ArrayList<>(list);
                tmp.add(candidates[i]);
                helper(candidates, i+1, tmp, n-candidates[i], res);
            }
        }
    }

    /**
     * Runtime: 3 ms, faster than 82.48% of Java online submissions for Combination Sum II.
     * Memory Usage: 37.9 MB, less than 88.08% of Java online submissions for Combination Sum II.
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum21(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(candidates==null||candidates.length==0) {
            return res;
        }
        Arrays.sort(candidates);
        helper(candidates, 0, new ArrayList<>(), target, res);
        return res;
    }

    public void helper(int[] candidates, int start, List<Integer> list, int n, List<List<Integer>> res) {
        if(n==0) {
            res.add(new ArrayList<>(list));
        }
        for(int i=start;i<candidates.length;i++) {
            if(i>start&&candidates[i]==candidates[i-1]) {
                continue;
            }
            if(candidates[i]<=n) {
                List<Integer> tmp = new ArrayList<>(list);
                tmp.add(candidates[i]);
                helper(candidates, i+1, tmp, n-candidates[i], res);
            }
        }
    }

}
