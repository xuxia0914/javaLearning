package cn.xux.algorithm.leetcode.vip.midium;

import cn.xux.algorithm.common.NestedInteger;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 364. 加权嵌套序列和 II（重复叠加）
 * 给一个嵌套整数序列，请你返回每个数字在序列中的加权和，它们的权重由它们的深度决定。
 * 序列中的每一个元素要么是一个整数，要么是一个序列（这个序列中的每个元素也同样是整数或序列）。
 * 与 前一个问题 不同的是，前一题的权重按照从根到叶逐一增加，而本题的权重从叶到根逐一增加。
 * 也就是说，在本题中，叶子的权重为1，而根拥有最大的权重。
 *
 * 示例 1:
 * 输入: [[1,1],2,[1,1]]
 * 输出: 8
 * 解释: 四个 1 在深度为 1 的位置， 一个 2 在深度为 2 的位置。
 *
 * 示例 2:
 * 输入: [1,[4,[6]]]
 * 输出: 17
 * 解释: 一个 1 在深度为 3 的位置， 一个 4 在深度为 2 的位置，
 * 一个 6 在深度为 1 的位置。 1*3 + 4*2 + 6*1 = 17。
 */
public class NestedListWeightSumII {

    public int depthSumInverse(List<NestedInteger> nestedList) {
        if(nestedList==null||nestedList.size()==0) {
            return 0;
        }
        int pre = 0;
        Queue<NestedInteger> queue = new LinkedList<>();
        for(NestedInteger ni : nestedList) {
            queue.offer(ni);
        }
        int ans = 0;
        while(!queue.isEmpty()) {
            ans += pre;
            int cnt = 0;
            int size = queue.size();
            while(size-->0) {
                NestedInteger curr = queue.poll();
                if(curr.isInteger()) {
                    cnt += curr.getInteger();
                    ans += curr.getInteger();
                }else {
                    for(NestedInteger ni : curr.getList()) {
                        queue.offer(ni);
                    }
                }
            }
            pre += cnt;
        }
        return ans;
    }

}