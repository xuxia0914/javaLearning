package cn.leetcode.xux.lintcode;

/**
 * 591. 连接图 III
 * 中文English
 * 给一个图中的 n 个节点, 记为 1 到 n . 在开始的时候图中没有边.
 * 你需要完成下面两个方法:
 *
 * connect(a, b), 添加一条连接节点 a, b的边
 * query(), 返回图中联通区域个数
 * 样例
 * 例1:
 *
 * 输入:
 * ConnectingGraph3(5)
 * query()
 * connect(1, 2)
 * query()
 * connect(2, 4)
 * query()
 * connect(1, 4)
 * query()
 *
 * 输出:[5,4,3,3]
 *
 * 例2:
 *
 * 输入:
 * ConnectingGraph3(6)
 * query()
 * query()
 * query()
 * query()
 * query()
 *
 *
 * 输出:
 * [6,6,6,6,6]
 */
public class Lintcode591 {
}

class ConnectingGraph3 {

    DSU dsu;
    int n;

    public ConnectingGraph3(int n) {
        // initialize your data structure here.
        dsu = new DSU(n+1);
        this.n = n;
    }

    /**
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public void connect(int a, int b) {
        // write your code here
        dsu.union(a, b);
    }

    /**
     * @return: An integer
     */
    public int query() {
        // write your code here
        int ans = 0;
        for(int i=1;i<=n;i++) {
            if(i==dsu.find(i)) {
                ans++;
            }
        }
        return ans;
    }

}
