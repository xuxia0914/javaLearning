package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * ]
 */
public class Combinations {

    List<List<Integer>> res;
    List<Integer> curr;

    public List<List<Integer>> combine(int n, int k) {
        if(k<1||n<k) {
            return res;
        }
        res = new ArrayList<>();
        curr = new ArrayList<>();
        dfs(1, n, k);
        return res;

    }

    public void dfs(int start, int n, int k) {
        if(k==0) {
            res.add(new ArrayList(curr));
            return;
        }
        if(n-start+1<k) {
            return;
        }
        dfs(start+1, n, k);
        curr.add(start);
        dfs(start+1, n, k-1);
        curr.remove(curr.size()-1);
    }

    public static void main(String[] args) {
        Combinations c = new Combinations();
        System.out.println(c.combine(4, 2));
    }

}
