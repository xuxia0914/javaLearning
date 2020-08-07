package cn.xux.algorithm.leetcode.vip.midium;

import java.util.*;

/**
 * 254. 因子的组合
 * 整数可以被看作是其因子的乘积。
 * 例如：
 * 8 = 2 x 2 x 2;
 *   = 2 x 4.
 * 请实现一个函数，该函数接收一个整数 n 并返回该整数所有的因子组合。
 *
 * 注意：
 * 你可以假定 n 为永远为正数。
 * 因子必须大于 1 并且小于 n。
 *
 * 示例 1：
 * 输入: 1
 * 输出: []
 *
 * 示例 2：
 * 输入: 37
 * 输出: []
 *
 * 示例 3：
 * 输入: 12
 * 输出:
 * [
 *   [2, 6],
 *   [2, 2, 3],
 *   [3, 4]
 * ]
 *
 * 示例 4:
 * 输入: 32
 * 输出:
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
