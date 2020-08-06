package cn.xux.algorithm.lintcode;

import java.util.*;

/**
 * 1400. 图的费马点
 * 中文English
 * 有一个无向无环连通图，每条边通过两个顶点x[i],y[i]来描述，每条边的长度通过d[i]来描述。
 * 求这样的一个点p，使得其他点到p的距离和最小，如果有多个这样的点p，返回编号最小的。
 *
 * 样例
 * 样例 1:
 *
 * 给出 x = `[1]`, y = `[2]`, d = `[3]`, 返回 `1`。
 * 输入:
 * [1]
 * [2]
 * [3]
 * 输出:
 * 1
 *
 * 解释：
 * 其他点到 1 的距离和为 3，其他点到 2 的距离和为 3，1 的编号较小。
 * 样例 2:
 *
 * 给出 x = `[1,2,2]`, y = `[2,3,4]`, d = `[1,1,1]`, 返回 `2`。
 * 输入:
 * [1,2,2]
 * [2,3,4]
 * [1,1,1]
 * 输出:
 * 2
 *
 * 解释：
 * 其他点到 1 的距离和为 5，其他点到 2 的距离和为 3，其他点到 3 的距离和为 5，其他点到 4 的距离和为 5。
 * 注意事项
 * 2 <= n, d[i] <= 10^5
 * 1 <= x[i], y[i] <= n
 */
public class Lintcode1440 {

    /**
     * @param x: The end points set of edges
     * @param y: The end points set of edges
     * @param d: The length of edges
     * @return: Return the index of the fermat point
     */
    public int getFermatPoint(int[] x, int[] y, int[] d) {
        // Write your code here
        if(x==null||x.length==0) {
            return 0;
        }
        int n = x.length+1;
        graph = new List[n+1];
        for(int i=1;i<=n;i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i=0;i<x.length;i++) {
            graph[x[i]].add(new int[]{y[i], d[i]});
            graph[y[i]].add(new int[]{x[i], d[i]});
        }
        int ans = 0;
        long heavy = Long.MAX_VALUE;
        for(int i=1;i<=n;i++) {
            long h = 0;
            for(int[] nei : graph[i]) {
                long[] tmp = dfs(nei, i, n+1);
                h += tmp[0]+tmp[1]*nei[1];
            }
            if(h<heavy) {
                ans = i;
                heavy = h;
            }
        }
        return ans;
    }

    List<int[]>[] graph;
    Map<Integer, long[]> mem = new HashMap<>();

    public long[] dfs(int[] curr, int parent, int n) {
        List<int[]> neis = graph[curr[0]];
        if(neis.size()==1) {
            return new long[]{0, 1};
        }
        int key = curr[0]*n+parent;
        if(mem.containsKey(key)) {
            return mem.get(key);
        }
        long[] ans = new long[]{0, 1};
        for(int[] nei : neis) {
            if(nei[0]!=parent) {
                long[] neiAns = dfs(nei, curr[0], n);
                ans[0] += neiAns[0]+neiAns[1]*nei[1];
                ans[1] += neiAns[1];
            }
        }
        mem.put(key, ans);
        return ans;
    }

}
