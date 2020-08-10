package cn.xux.algorithm.leetcode.vip.easy;

import cn.xux.algorithm.common.NestedInteger;

import java.util.List;

/**
 * 339. 嵌套列表权重和
 * 给定一个嵌套的整数列表，请返回该列表按深度加权后所有整数的总和。
 * 每个元素要么是整数，要么是列表。同时，列表中元素同样也可以是整数或者是另一个列表。
 *
 * 示例 1:
 * 输入: [[1,1],2,[1,1]]
 * 输出: 10
 * 解释: 因为列表中有四个深度为 2 的 1 ，和一个深度为 1 的 2。
 *
 * 示例 2:
 * 输入: [1,[4,[6]]]
 * 输出: 27
 * 解释: 一个深度为 1 的 1，一个深度为 2 的 4，一个深度为 3 的 6。所以，1 + 4*2 + 6*3 = 27。
 */
public class NestedListWeightSum {

    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    public int dfs(List<NestedInteger> nestedList, int depth) {
        if(nestedList==null||nestedList.size()==0) {
            return 0;
        }
        int res = 0;
        for(NestedInteger nestedInteger : nestedList) {
            if(nestedInteger.isInteger()) {
                res +=  nestedInteger.getInteger()*depth;
            }else {
                res += dfs(nestedInteger.getList(), depth+1);
            }
        }
        return res;
    }

}
