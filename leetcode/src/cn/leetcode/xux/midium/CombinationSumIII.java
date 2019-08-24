package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 * Note:
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Example 2:
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSumIII {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), k, n, 1);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> curr, int k, int n, int start) {
        if(k==0) {
            if(n==0) {
                res.add(new ArrayList<>(curr));
            }
            return;
        }
        if(start>9||start>n) {
            return;
        }
        curr.add(start);
        helper(res, curr, k-1, n-start, start+1);
        curr.remove(curr.size()-1);
        helper(res, curr, k, n, start+1);
    }

}
