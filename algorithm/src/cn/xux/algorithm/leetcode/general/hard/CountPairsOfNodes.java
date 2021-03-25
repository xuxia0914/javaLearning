package cn.xux.algorithm.leetcode.general.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1782. 统计点对的数目
 * 给你一个无向图，无向图由整数 n  ，表示图中节点的数目，和 edges 组成，
 * 其中 edges[i] = [ui, vi] 表示 ui 和 vi 之间有一条无向边。
 * 同时给你一个代表查询的整数数组 queries 。
 * 第 j 个查询的答案是满足如下条件的点对 (a, b) 的数目：a < b
 * cnt 是与 a 或者 b 相连的边的数目，且 cnt 严格大于 queries[j] 。
 * 请你返回一个数组 answers ，
 * 其中 answers.length == queries.length 且 answers[j] 是第 j 个查询的答案。
 * 请注意，图中可能会有 重复边 。
 *
 * 示例 1：
 * 输入：n = 4, edges = [[1,2],[2,4],[1,3],[2,3],[2,1]], queries = [2,3]
 * 输出：[6,5]
 * 解释：每个点对中，与至少一个点相连的边的数目如上图所示。
 *
 * 示例 2：
 * 输入：n = 5, edges = [[1,5],[1,5],[3,4],[2,5],[1,3],[5,1],[2,3],[2,5]], queries = [1,2,3,4,5]
 * 输出：[10,10,9,8,6]
 *
 * 提示：
 * 2 <= n <= 2 * 104
 * 1 <= edges.length <= 105
 * 1 <= ui, vi <= n
 * ui != vi
 * 1 <= queries.length <= 20
 * 0 <= queries[j] < edges.length
 */
public class CountPairsOfNodes {

    int P = 100000;
    public int[] countPairs(int n, int[][] edges, int[] queries) {
        int[] io = new int[n+1];
        int[] arr = new int[n+1];
        int m = queries.length;
        int[] ans = new int[m];
        Map<Integer,Integer> map = new HashMap<>();
        for(int[] e:edges){
            int u = Math.min(e[0],e[1]);
            int v = Math.max(e[0],e[1]);
            io[u]++;
            io[v]++;
            int tmp = u*P + v;
            map.put(tmp,map.getOrDefault(tmp,0)+1);
        }
        for(int i=1;i<=n;i++){
            arr[i] = io[i];
        }
        Arrays.sort(arr);
        for(int i=0;i<m;i++){
            int l=1,r=n;
            while(l<r){
                while(l<r && arr[l]+arr[r]<=queries[i]) {
                    l++;
                }
                if(l<r){
                    ans[i] += r-l;
                }
                r--;
            }
            if(ans[i] == 0) {
                continue;
            }
            for(int key:map.keySet()){
                int u = key/P;
                int v = key%P;
                if(io[u]+io[v]>queries[i] && io[u]+io[v]-map.get(key)<=queries[i]){
                    ans[i]--;
                }
            }
        }
        return ans;
    }

}
