package cn.leetcode.xux.general.midium;

import cn.leetcode.xux.common.DSU;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 947. 移除最多的同行或同列石头
 * 在二维平面上，我们将石头放置在一些整数坐标点上。每个坐标点上最多只能有一块石头。
 * 现在，move 操作将会移除与网格上的某一块石头共享一列或一行的一块石头。
 * 我们最多能执行多少次 move 操作？
 *
 * 示例 1：
 * 输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * 输出：5
 * 示例 2：
 * 输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * 输出：3
 * 示例 3：
 * 输入：stones = [[0,0]]
 * 输出：0
 *
 * 提示：
 * 1 <= stones.length <= 1000
 * 0 <= stones[i][j] < 10000
 */
public class MostStonesRemovedWithSameRowOrColumn {

    /**
     * dsf
     * 执行用时 :228 ms, 在所有 Java 提交中击败了16.95%的用户
     * 内存消耗 :58.5 MB, 在所有 Java 提交中击败了12.00%的用户
     * @param stones
     * @return
     */
    public int removeStones(int[][] stones) {
        if(stones==null||stones.length<2) {
            return 0;
        }
        int n = stones.length;
        Stack<int[]> stack = new Stack<>();
        boolean[] visited = new boolean[n];
        int res = 0;
        for(int i=0;i<n;i++) {
            if(!visited[i]) {
                int cnt = 0;
                stack.add(stones[i]);
                visited[i] = true;
                cnt++;
                while(!stack.isEmpty()) {
                    int[] curr = stack.pop();
                    for(int j=0;j<n;j++) {
                        if(!visited[j]&&(stones[j][0]==curr[0]||stones[j][1]==curr[1])) {
                            stack.add(stones[j]);
                            visited[j] = true;
                            cnt++;
                        }
                    }
                }
                res += cnt-1;
            }
        }
        return res;
    }

    /**
     * DSU 并查集
     * 石子的坐标不要简单看成坐标，如坐标(a, b)要看成域(x)的点(a)和域(y)的点(b)存在联通
     * 因为a,b的数据规模不超过10000，所以可以用b+10000来隔离两个域，这样思考就可以使用一维并查集来做题了。
     *
     * 执行用时 :19 ms, 在所有 Java 提交中击败了81.36%的用户
     * 内存消耗 :49.9 MB, 在所有 Java 提交中击败了60.00%的用户
     */
    public int removeStones1(int[][] stones) {
        if(stones==null||stones.length<2) {
            return 0;
        }
        DSU dsu = new DSU(20000);
        for(int[] stone : stones) {
            dsu.union(stone[0], stone[1]+10000);
        }
        Set<Integer> set = new HashSet<>();
        for(int[] stone : stones) {
            set.add(dsu.find(stone[0]));
        }
        return stones.length-set.size();
    }

}