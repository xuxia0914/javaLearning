package cn.xux.algorithm.lintcode;

/**
 * 589. 连接图
 * 中文English
 * 给一个图中的n个节点, 记为 1 到 n . 在开始的时候图中没有边。
 * 你需要完成下面两个方法:
 *
 * connect(a, b), 添加连接节点 a, b 的边.
 * query(a, b), 检验两个节点是否联通
 * 样例
 * Example 1:
 *
 * Input:
 * ConnectingGraph(5)
 * query(1, 2)
 * connect(1, 2)
 * query(1, 3)
 * connect(2, 4)
 * query(1, 4)
 * Output:
 * [false,false,true]
 *
 * Example 2:
 *
 * Input:
 * ConnectingGraph(6)
 * query(1, 2)
 * query(2, 3)
 * query(1, 3)
 * query(5, 6)
 * query(1, 4)
 *
 * Output:
 * [false,false,false,false,false]
 */
public class Lintcode589 {
}

class ConnectingGraph {

    DSU dsu;
    int n;

    /*
     * @param n: An integer
     */public ConnectingGraph(int n) {
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
     * @param b: An integer
     * @return: A boolean
     */
    public boolean query(int a, int b) {
        // write your code here
        return dsu.find(a)==dsu.find(b);
    }

}
