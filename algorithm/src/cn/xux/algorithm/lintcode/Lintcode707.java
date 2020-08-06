package cn.xux.algorithm.lintcode;

import java.util.*;

/**
 * 707. 最优账户结余
 * 中文English
 * 给一有向图,每一条边用一个 三元组 表示,
 * 比如 [u, v, w] 代表权值为 w 的从 u 到 v 的一条边.
 * 计算出保证每个点的权重相等需要添加的最少的边数.
 * 也就是说, 指向这一点的边权重总和等于这个点指向其他点的边权重之和.
 *
 * 样例
 * 样例1
 *
 * 输入: [[0,1,10],[2,0,5]]
 * 输出: 2
 * 说明:
 * 需要添加两条边
 * 它们是 [1,0,5] 以及 [1,2,5]
 * 样例2
 *
 * 输入: [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
 * 输出: 1
 * 说明:
 * 只需要添加一条边 [1,0,4]
 * 注意事项
 * 注意 u ≠ v 且 w > 0
 * 下标不一定是线性的, 比如 顶点下标可以为 0,1,2, 也可以为 0,2,6.
 */
public class Lintcode707 {

    /*
     * @param edges: a directed graph where each edge is represented by a tuple
     * @return: the number of edges
     */
    public int balanceGraph(int[][] edges) {
        // Write your code here
        if(edges==null||edges.length==0) {
            return 0;
        }
        //计算每个节点的权重(出则减，进则加)
        Map<Integer, Integer> heavy = new HashMap<>();
        for(int[] e : edges) {
            heavy.put(e[0], heavy.getOrDefault(e[0], 0)-e[2]);
            heavy.put(e[1], heavy.getOrDefault(e[1], 0)+e[2]);
        }
        //记录所有权重不为0的节点
        int[] nums = new int[heavy.size()];
        int len = 0;
        for(int v : heavy.values()) {
            if(v!=0) {
                nums[len++] = v;
            }
        }
        //dp[i]表示第i个子集(i的二进制表示的第j位为1,则nums[j]在该子集中)达成平衡需要的增加的边数
        int[] dp = new int[1<<len];
        Arrays.fill(dp, -1);
        for(int i=1;i<dp.length;i++) {
            int sum = 0;
            int cnt = 0;
            //计算该子集的和，子集的节点数
            for(int j=0;j<len;j++) {
                if(((1<<j)&i)==1) {
                    sum += nums[j];
                    cnt++;
                }
            }
            if(sum==0) {
                dp[i] = cnt-1;
                for(int j=1;j<i;j++) {
                    // i子集包含j子集
                    if((i&j)==j&&dp[j]!=-1&&dp[i-j]!=-1) {
                        dp[i] = Math.min(dp[i], dp[j]+dp[i-j]);
                    }
                }
            }
        }
        return dp[dp.length-1];
    }

}
