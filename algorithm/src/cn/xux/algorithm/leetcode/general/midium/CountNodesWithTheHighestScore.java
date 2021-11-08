package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2049. 统计最高分的节点数目
 * 给你一棵根节点为 0 的 二叉树 ，它总共有 n 个节点，节点编号为 0 到 n - 1 。
 * 同时给你一个下标从 0 开始的整数数组 parents 表示这棵树，
 * 其中 parents[i] 是节点 i 的父节点。由于节点 0 是根，所以 parents[0] == -1 。
 * <p>
 * 一个子树的 大小 为这个子树内节点的数目。每个节点都有一个与之关联的 分数 。
 * 求出某个节点分数的方法是，将这个节点和与它相连的边全部 删除 ，
 * 剩余部分是若干个 非空 子树，这个节点的 分数 为所有这些子树 大小的乘积 。
 * <p>
 * 请你返回有 最高得分 节点的 数目 。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * example-1
 * <p>
 * 输入：parents = [-1,2,0,2,0]
 * 输出：3
 * 解释：
 * - 节点 0 的分数为：3 * 1 = 3
 * - 节点 1 的分数为：4 = 4
 * - 节点 2 的分数为：1 * 1 * 2 = 2
 * - 节点 3 的分数为：4 = 4
 * - 节点 4 的分数为：4 = 4
 * 最高得分为 4 ，有三个节点得分为 4 （分别是节点 1，3 和 4 ）。
 * 示例 2：
 * <p>
 * example-2
 * <p>
 * 输入：parents = [-1,2,0]
 * 输出：2
 * 解释：
 * - 节点 0 的分数为：2 = 2
 * - 节点 1 的分数为：2 = 2
 * - 节点 2 的分数为：1 * 1 = 1
 * 最高分数为 2 ，有两个节点分数为 2 （分别为节点 0 和 1 ）。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == parents.length
 * 2 <= n <= 105
 * parents[0] == -1
 * 对于 i != 0 ，有 0 <= parents[i] <= n - 1
 * parents 表示一棵二叉树。
 */
public class CountNodesWithTheHighestScore {

    public static void main(String[] args) {
        System.out.println(new CountNodesWithTheHighestScore()
                .countHighestScoreNodes(new int[]{-1,2,0}));
    }

    // children[i]表示节点i的所有子节点的列表
    List[] children;
    // cnt[i]表示 以i为根节点的子树包含的节点的个数
    int[] cnt;

    public int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        children = new List[n];
        cnt = new int[n];
        for (int i = 1; i < n; i++) {
            if (children[parents[i]] == null) {
                children[parents[i]] = new ArrayList<>();
            }
            children[parents[i]].add(i);
        }
        // 计算 所有子树包含的节点数 cnt
        dfs(0);
        // 最大分数
        long max = 0;
        // 分数为max的节点数
        int ans = 0;
        for (int i = 0; i < n; i++) {
            long curr = 1;
            if(children[i]==null) {
                curr = n-1;
            }else {
                int up = n-1;
                for(int child : (List<Integer>)children[i]) {
                    up -= cnt[child];
                    curr *= cnt[child];
                }
                if(up>1) {
                    curr *= up;
                }
            }
            if(curr>max) {
                max = curr;
                ans = 1;
            }else if(curr==max) {
                ans++;
            }
        }
        return ans;
    }

    private void dfs(int curr) {
        cnt[curr] = 1;
        if(children[curr]!=null) {
            for(int child : (List<Integer>)children[curr]) {
                dfs(child);
                cnt[curr] += cnt[child];
            }
        }
    }

}
