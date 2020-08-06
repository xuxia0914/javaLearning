package cn.xux.algorithm.leetcode.general.midium;

import java.util.*;

/**
 * 254 Factor Combinations 因子组合
 * Numbers can be regarded as product of its factors. For example,
 * 8 = 2 x 2 x 2;
 *   = 2 x 4.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 *
 * Note:
 * Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
 * You may assume that n is always positive.
 * Factors should be greater than 1 and less than n.
 *
 * Examples:
 * input: 1
 * output: []
 *
 * input: 37
 * output:[]
 *
 * input: 12
 * output:
 * [
 *   [2, 6],
 *   [2, 2, 3],
 *   [3, 4]
 * ]
 *
 * input: 32
 * output:
 * [
 *   [2, 16],
 *   [2, 2, 8],
 *   [2, 2, 2, 4],
 *   [2, 2, 2, 2, 2],
 *   [2, 4, 4],
 *   [4, 8]
 * ]
 */
public class FactorCombinations {

    Map<String, List<List<Integer>>> map = new HashMap<>();

    public  List<List<Integer>> getFactors(int n) {
        return helper(n, 2);
    }

    public List<List<Integer>> helper(int n, int pre) {
        List<List<Integer>> res = new LinkedList<>();
        String key = new StringBuilder().append(n).append(",").append(pre).toString();
        if(map.containsKey(key)) {
            return map.get(key);
        }
        for(int i=pre;i<=(int)Math.sqrt(n);i++) {
            if(n%i==0) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                list.add(n/i);
                res.add(list);
                List<List<Integer>> postRes = helper(n/i, i);
                for(List<Integer> tmp : postRes) {
                    tmp.add(0, i);
                    res.add(tmp);
                }
            }
        }
        map.put(key, res);
        return res;
    }

    public static void main(String[] args) {
        FactorCombinations fc = new FactorCombinations();
        System.out.println(fc.getFactors(1));
        System.out.println(fc.getFactors(2));
        System.out.println(fc.getFactors(37));
        System.out.println(fc.getFactors(12));
        System.out.println(fc.getFactors(32));
    }

}
