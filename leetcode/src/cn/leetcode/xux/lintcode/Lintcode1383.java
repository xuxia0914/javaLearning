package cn.leetcode.xux.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1383. 子树计数
 * 中文English
 * 有一棵n个结点以1为根的多叉树，求这棵树的连通子图个数。
 * 由于计算的中途和答案可能会超过long的范围，故答案对10000007取模。
 * (连通子图：任选x个点(1 <= x <= n)，任意两点能相互达到)
 *
 * 样例
 * 样例1
 *
 * 输入:
 * start = [1]
 * end   = [2]
 * 输出: 3
 * 说明:
 * 有[1],[2],[1->2], 3个连通子图。
 * 样例2
 *
 * 输入:
 * start = [1, 1]
 * end   = [2, 3]
 * 输出: 6
 * 说明:
 * 有[1],[2],[3],[1->2],[1->3],[1->2,1->3] 6个连通子图。
 * 样例3
 *
 * 输入:
 * start = [1, 1, 2]
 * end   = [2, 3, 4]
 * 输出: 10
 * 说明:
 * 有[1], [2], [3], [4], [1→2], [1→3], [2→4], [1→2,1→3], [1→2,2→4], [1→3,1→2,2→4] 10个连通子图。
 * 注意事项
 * 1 <= |start|,|end|,n <= 10^5
 * 1 <= start[i],end[i] <= n
 */
public class Lintcode1383 {

    /**
     * @param start: The start of the edges set
     * @param end: The end of the edges set
     * @return: Return the subtree count
     */
    public int getSubtreeCount(int[] start, int[] end) {
        // Write your code here
        int n = start.length+1;
        graph = new List[n+1];
        for(int i=1;i<=n;i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i=0;i<n-1;i++) {
            graph[start[i]].add(end[i]);
            graph[end[i]].add(start[i]);
        }
        dfs(1, 0);
        return (int)ans;
    }

    List<Integer>[] graph;
    long ans = 0;

    public long dfs(int curr, int parent) {
        if(graph[curr].size()==1) {
            ans += 1;
            return 1;
        }
        long cnt = 1;
        for(int nei : graph[curr]) {
            if(nei!=parent) {
                cnt = (cnt*(dfs(nei, curr)+1))%10000007;
            }
        }
        ans = (ans+cnt)%10000007;
        return cnt;
    }

}
