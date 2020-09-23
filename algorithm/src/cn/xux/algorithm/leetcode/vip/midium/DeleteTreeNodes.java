package cn.xux.algorithm.leetcode.vip.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 1273：删除树节点
 * 给你一棵以节点 0 为根节点的树，定义如下：
 * 节点的总数为 nodes 个；
 * 第 i 个节点的值为 value[i] ；
 * 第 i 个节点的父节点是 parent[i] 。
 * 请你删除节点值之和为 0 的每一棵子树。
 * 在完成所有删除之后，返回树中剩余节点的数目。
 *
 * 示例：
 * 输入：nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
 * 输出：2
 *
 * 提示：
 * 1 <= nodes <= 10^4
 * -10^5 <= value[i] <= 10^5
 * parent.length == nodes
 * parent[0] == -1 表示节点 0 是树的根。
 */
public class DeleteTreeNodes {

    public static void main(String[] args) {
        DeleteTreeNodes dt = new DeleteTreeNodes();
        System.out.println(dt.deleteTreeNodes(7,
                new int[]{-1,0,0,1,2,2,2},
                new int[]{1,-2,4,0,-2,-1,-1}));
    }

    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        List<Integer>[] graph = new List[nodes];
        for(int i=0;i<nodes;i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i=1;i<nodes;i++) {
            graph[parent[i]].add(i);
        }
        dfs(graph, value, 0);
        return nodes-ans;
    }

    int ans = 0;

    // res[0] 节点总数 res[1] 所有节点值的和
    private int[] dfs(List<Integer>[] graph, int[] value, int curr) {
        int sum = value[curr];
        int cnt = 1;
        for(int child : graph[curr]) {
            int[] childRes = dfs(graph, value, child);
            sum += childRes[1];
            cnt += childRes[0];
        }
        if(sum==0) {
            ans += cnt;
            cnt = 0;
        }
        return new int[]{cnt, sum};
    }

}
