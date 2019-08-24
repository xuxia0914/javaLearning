package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * Example:
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if(k<1||n<k) {
            return res;
        }
        helper(res, new ArrayList<>(), 1, n, k);
        return res;

    }

    public void helper(List<List<Integer>> res, List<Integer> curr, int start, int n, int k) {
        if(k==0) {
            res.add(new ArrayList(curr));
            return;
        }
        if(n-start+1<k) {
            return;
        }
        curr.add(start);
        helper(res, curr, start+1, n, k-1);
        curr.remove(curr.size()-1);
        helper(res, curr, start+1, n, k);
    }

    public static void main(String[] args) {
        Combinations c = new Combinations();
        System.out.println(c.combine(4, 2));
    }

}
