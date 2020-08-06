package cn.xux.algorithm.lintcode;

/**
 * 590. 连接图 II
 * 中文English
 * 给一个图中的 n 个节点, 记为 1 到 n .在开始的时候图中没有边.
 * 你需要完成下面两个方法：
 * connect(a, b), 添加一条连接节点 a, b的边
 * query(a), 返回图中含 a 的联通区域内节点个数
 * 样例
 * 例1:
 * 输入:
 * ConnectingGraph2(5)
 * query(1)
 * connect(1, 2)
 * query(1)
 * connect(2, 4)
 * query(1)
 * connect(1, 4)
 * query(1)
 * 输出:
 * [1,2,3,3]
 *
 * 例2:
 * 输入:
 * ConnectingGraph2(6)
 * query(1)
 * query(2)
 * query(1)
 * query(5)
 * query(1)
 *
 *
 * 输出:
 * [1,1,1,1,1]
 */
public class Lintcode590 {
}

class ConnectingGraph2 {

    DSU dsu;
    int n;

    /*
     * @param n: An integer
     */public ConnectingGraph2(int n) {
        // do intialization if necessary
        dsu = new DSU(n+1);
        this.n = n;
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public void connect(int a, int b) {
        // write your code here
        dsu.union(a, b);
    }

    /*
     * @param a: An integer
     * @return: An integer
     */
    public int query(int a) {
        // write your code here
        int tar = dsu.find(a);
        int ans = 0;
        for(int i=1;i<=n;i++) {
            if(dsu.find(i)==tar) {
                ans++;
            }
        }
        return ans;
    }

}

class DSU {

    int[] parent;
    public DSU(int N) {
        parent = new int[N];
        for (int i = 0; i < N; ++i) {
            parent[i] = i;
        }
    }
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    public void union(int x, int y) {
        parent[find(y)] = find(x);
    }

}
