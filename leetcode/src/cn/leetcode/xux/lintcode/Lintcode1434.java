package cn.leetcode.xux.lintcode;

import java.util.*;

/**
 * 1434. 树中距离的总和
 * 中文English
 * An undirected, connected tree with N nodes labelled 0...N-1 and N-1 edges are given.
 *
 * The ith edge connects nodes edges[i][0] and edges[i][1] together.
 *
 * Return a list ans, where ans[I] is the sum of the distances between node I and all other nodes.
 *
 * 样例
 * Example 1:
 *
 * Input: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * Output: [8,12,6,10,10,10]
 *
 * Explanation:
 * Here is a diagram of the given tree:
 *   0
 *  / \
 * 1   2
 *    /|\
 *   3 4 5
 * We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.
 * Example 2:
 *
 * Input:
 * 5
 * [[0,1],[1,2],[2,3],[3,4]]
 * Output:
 * [10,7,6,7,10]
 *
 * Explanation:
 *                 0
 *               /
 *             1
 *           /
 *         2
 *       /
 *     3
 *   /
 * 4
 * answer[0] = 1 + 2 + 3 + 4 = 10
 * answer[1] = 1 + 1 + 2 + 3 = 7
 * answer[2] = 1 + 2 + 1 + 2 = 6,and so on.
 * 注意事项
 * 1 <= N <= 10000
 */
public class Lintcode1434 {

    int[] ans, count;
    List<Set<Integer>> graph;
    int N;

    /**
     * @param N: an integer
     * @param edges: the edges
     * @return: a list ans, where ans[i] is the sum of the distances between node i and all other nodes
     */
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        // Write your code here
        this.N = N;
        graph = new ArrayList<Set<Integer>>();
        ans = new int[N];
        count = new int[N];
        Arrays.fill(count, 1);

        for (int i = 0; i < N; ++i)
            graph.add(new HashSet<Integer>());
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        dfs(0, -1);
        dfs2(0, -1);
        return ans;
    }

    public void dfs(int node, int parent) {
        for (int child: graph.get(node))
            if (child != parent) {
                dfs(child, node);
                //count[node]是以node为根的节点个数
                count[node] += count[child];
                //ans[node]是所有结点道node的距离
                //就是ans[child]+child与node的距离
                ans[node] += ans[child] + count[child];
            }
    }

    public void dfs2(int node, int parent) {
        for (int child: graph.get(node))
            if (child != parent) {
                // ans[node] += ans[child] + count[child];
                //这是ans[child]的前半部分，
                //后半部分是，不是以child为根的节点个数
                //后半部分是，因为，我们可以发现，每一个count都是多加了自己本身的，
                //也就是我们前面的child是多加了自己本身的，所以不是child的根节点都没加上
                ans[child] = ans[node] - count[child] + N - count[child];
                dfs2(child, node);
            }
    }

    //MLE
    public int[] sumOfDistancesInTree1(int N, int[][] edges) {
        // Write your code here
        int[][] dis = new int[N][N];
        for(int[] edge : edges) {
            dis[edge[0]][edge[1]] = 1;
            dis[edge[1]][edge[0]] = 1;
        }
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(i!=j&&dis[i][j]==0) {
                    dis[i][j] = N-1;
                }
            }
        }
        for(int k=0;k<N;k++) {
            for(int i=0;i<N-1;i++) {
                for(int j=i+1;j<N;j++) {
                    dis[i][j] = Math.min(dis[i][j], dis[i][k]+dis[k][j]);
                    dis[j][i] = dis[i][j];
                }
            }
        }
        int[] ans = new int[N];
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                ans[i] += dis[i][j];
            }
        }
        return ans;
    }

}
