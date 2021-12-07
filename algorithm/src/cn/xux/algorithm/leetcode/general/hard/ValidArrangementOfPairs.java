package cn.xux.algorithm.leetcode.general.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2097. 合法重新排列数对
 * 给你一个下标从 0 开始的二维整数数组 pairs ，
 * 其中 pairs[i] = [starti, endi] 。
 * 如果 pairs 的一个重新排列，
 * 满足对每一个下标 i （ 1 <= i < pairs.length ）
 * 都有 end[i-1] == start[i] ，
 * 那么我们就认为这个重新排列是 pairs 的一个 合法重新排列 。
 *
 * 请你返回 任意一个 pairs 的合法重新排列。
 *
 * 注意：数据保证至少存在一个 pairs 的合法重新排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：pairs = [[5,1],[4,5],[11,9],[9,4]]
 * 输出：[[11,9],[9,4],[4,5],[5,1]]
 * 解释：
 * 输出的是一个合法重新排列，因为每一个 endi-1 都等于 starti 。
 * end0 = 9 == 9 = start1
 * end1 = 4 == 4 = start2
 * end2 = 5 == 5 = start3
 * 示例 2：
 *
 * 输入：pairs = [[1,3],[3,2],[2,1]]
 * 输出：[[1,3],[3,2],[2,1]]
 * 解释：
 * 输出的是一个合法重新排列，因为每一个 endi-1 都等于 starti 。
 * end0 = 3 == 3 = start1
 * end1 = 2 == 2 = start2
 * 重新排列后的数组 [[2,1],[1,3],[3,2]] 和 [[3,2],[2,1],[1,3]] 都是合法的。
 * 示例 3：
 *
 * 输入：pairs = [[1,2],[1,3],[2,1]]
 * 输出：[[1,2],[2,1],[1,3]]
 * 解释：
 * 输出的是一个合法重新排列，因为每一个 endi-1 都等于 starti 。
 * end0 = 2 == 2 = start1
 * end1 = 1 == 1 = start2
 *
 *
 * 提示：
 *
 * 1 <= pairs.length <= 105
 * pairs[i].length == 2
 * 0 <= starti, endi <= 109
 * starti != endi
 * pairs 中不存在一模一样的数对。
 * 至少 存在 一个合法的 pairs 重新排列。
 */
public class ValidArrangementOfPairs {

    public static void main(String[] args) {
        new ValidArrangementOfPairs().validArrangement(
                new int[][]{{5,1},{4,5},{11,9},{9,4}}
        );
    }

    Map<Integer, List<Integer>> graph = new HashMap<>();
    List<Integer> ret = new ArrayList<>();

    // 欧拉路径
    // 入度减去出度为1的点就是起始点
    // 如果没有，那么就是一个环，任意一个点都可以作为起始点
    public int[][] validArrangement(int[][] pairs) {
        int n = pairs.length;
        Map<Integer, Integer> in = new HashMap<>();
        Map<Integer, Integer> out = new HashMap<>();
        for(int[] p : pairs) {
            graph.putIfAbsent(p[0], new ArrayList<>());
            graph.get(p[0]).add(p[1]);
            in.put(p[0], in.getOrDefault(p[0], 0)+1);
            out.put(p[1], out.getOrDefault(p[1], 0)+1);
        }
        int start = pairs[0][0];
        for(int key : in.keySet()) {
            if(in.getOrDefault(key, 0)-out.getOrDefault(key, 0)==1) {
                start = key;
                break;
            }
        }
        dfs(start);
        int[][] ans = new int[n][2];
        for(int i=n;i>0;i--) {
            ans[n-i][0] = ret.get(i);
            ans[n-i][1] = ret.get(i-1);
        }
        return ans;
    }

    private void dfs(int start) {
        List<Integer> list = graph.getOrDefault(start, new ArrayList<>());
        while(list.size()>0) {
            int next = list.remove(list.size()-1);
            dfs(next);
        }
        ret.add(start);
    }

}
