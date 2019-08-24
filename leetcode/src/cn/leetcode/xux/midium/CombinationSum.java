package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(candidates==null||candidates.length==0) {
            return res;
        }
        helper(0, new ArrayList<>(), target, candidates, res);
        return res;
    }

    public void helper(int start, List<Integer> list, int n, int[] candidates, List<List<Integer>> res) {
        if(n==0) {
            res.add(new ArrayList<>(list));
            return;
        }
        int curr;
        for(int i=start;i<candidates.length;i++) {
            curr = candidates[i];
            if(curr<=n) {
                List<Integer> tmp = new ArrayList<>(list);
                tmp.add(curr);
                helper(i, tmp, n-curr, candidates, res);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(0b11111111111111111111111111111111);
    }

}
