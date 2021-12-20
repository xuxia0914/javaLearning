package cn.xux.algorithm.leetcode.lcp.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LCP 05. 发 LeetCoin
 * 力扣决定给一个刷题团队发LeetCoin作为奖励。
 * 同时，为了监控给大家发了多少LeetCoin，力扣有时候也会进行查询。
 * <p>
 * <p>
 * <p>
 * 该刷题团队的管理模式可以用一棵树表示：
 * <p>
 * 团队只有一个负责人，编号为1。除了该负责人外，每个人有且仅有一个领导（负责人没有领导）；
 * 不存在循环管理的情况，如A管理B，B管理C，C管理A。
 * <p>
 * <p>
 * 力扣想进行的操作有以下三种：
 * <p>
 * 给团队的一个成员（也可以是负责人）发一定数量的LeetCoin；
 * 给团队的一个成员（也可以是负责人），以及他/她管理的所有人（即他/她的下属、他/她下属的下属，……），发一定数量的LeetCoin；
 * 查询某一个成员（也可以是负责人），以及他/她管理的所有人被发到的LeetCoin之和。
 * <p>
 * <p>
 * 输入：
 * <p>
 * N表示团队成员的个数（编号为1～N，负责人为1）；
 * leadership是大小为(N - 1) * 2的二维数组，其中每个元素[a, b]代表b是a的下属；
 * operations是一个长度为Q的二维数组，代表以时间排序的操作，格式如下：
 * operations[i][0] = 1: 代表第一种操作，operations[i][1]代表成员的编号，operations[i][2]代表LeetCoin的数量；
 * operations[i][0] = 2: 代表第二种操作，operations[i][1]代表成员的编号，operations[i][2]代表LeetCoin的数量；
 * operations[i][0] = 3: 代表第三种操作，operations[i][1]代表成员的编号；
 * 输出：
 * <p>
 * 返回一个数组，数组里是每次查询的返回值（发LeetCoin的操作不需要任何返回值）。
 * 由于发的LeetCoin很多，请把每次查询的结果模1e9+7 (1000000007)。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：N = 6, leadership = [[1, 2], [1, 6], [2, 3], [2, 5], [1, 4]], operations = [[1, 1, 500], [2, 2, 50], [3, 1], [2, 6, 15], [3, 1]]
 * 输出：[650, 665]
 * 解释：团队的管理关系见下图。
 * 第一次查询时，每个成员得到的LeetCoin的数量分别为（按编号顺序）：500, 50, 50, 0, 50, 0;
 * 第二次查询时，每个成员得到的LeetCoin的数量分别为（按编号顺序）：500, 50, 50, 0, 50, 15.
 * <p>
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= N <= 50000
 * 1 <= Q <= 50000
 * operations[i][0] != 3 时，1 <= operations[i][2] <= 5000
 */
public class Lcp005 {

    public static void main(String[] args) {
        // 6
        // [[1, 2], [1, 6], [2, 3], [2, 5], [1, 4]]
        // [[1, 1, 500], [2, 2, 50], [3, 1], [2, 6, 15], [3, 1]]
        System.out.println(Arrays.toString(
                new Lcp005().bonus(6,
                        new int[][]{{1, 2}, {1, 6}, {2, 3}, {2, 5}, {1, 4}},
                        new int[][]{{1, 1, 500}, {2, 2, 50}, {3, 1}, {2, 6, 15}, {3, 1}})
        ));
    }

    int mod = (int) 1e9 + 7;

    // preOrder是树的前序遍历
    int[] preOrder;
    // map[i] = j表示节点i在preOrder位置是j
    int[] map;
    // cnt[i]表示以节点i为根节点的树的节点个数
    int[] cnt;
    // 原数组的差分数组的树状数组
    long[] treeArr1;
    // 原数组的差分数组的树状数组c[i]*(i-1)
    long[] treeArr2;

    // 树状数组的区间修改和区间查询
    public int[] bonus(int n, int[][] leadership, int[][] operations) {
        // 邻居接表
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] ls : leadership) {
            graph[ls[0]].add(ls[1]);
        }
        preOrder = new int[n + 1];
        map = new int[n + 1];
        cnt = new int[n + 1];
        dfs(1, 1, graph);
        for (int i = 1; i <= n; i++) {
            map[preOrder[i]] = i;
        }
        treeArr1 = new long[n + 1];
        treeArr2 = new long[n + 1];
        List<Integer> list = new ArrayList<>();
        for (int[] oper : operations) {
            if (oper[0] == 3) {
                int left = map[oper[1]];
                int right = map[oper[1]] + cnt[oper[1]] - 1;
                list.add((int) ((query(right) - query(left - 1)) % mod));
            } else if (oper[0] == 1) {
                int left = map[oper[1]];
                int right = map[oper[1]];
                add(left, oper[2]);
                if (right + 1 <= n) {
                    add(right + 1, -oper[2]);
                }
            } else {
                int left = map[oper[1]];
                int right = map[oper[1]] + cnt[oper[1]] - 1;
                add(left, oper[2]);
                if (right + 1 <= n) {
                    add(right + 1, -oper[2]);
                }
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    private int dfs(int idx, int curr, List<Integer>[] graph) {
        preOrder[idx] = curr;
        cnt[curr] = 1;
        int start = idx + 1;
        for (int child : graph[curr]) {
            int next = dfs(start, child, graph);
            cnt[curr] += next;
            start += next;
        }
        return cnt[curr];
    }

    private int lowBit(int x) {
        return x & (-x);
    }

    private void add(int x, int y) {
        int idx = x;
        while (x < treeArr1.length) {
            treeArr1[x] += y;
            treeArr2[x] += (idx - 1) * y;
            x += lowBit(x);
        }
    }

    private long query(int x) {
        long ans = 0;
        int idx = x;
        while (x > 0) {
            ans += treeArr1[x] * idx - treeArr2[x];
            x -= lowBit(x);
        }
        return ans;
    }

}
