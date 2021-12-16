package cn.xux.algorithm.leetcode.lcci.midium;

/**
 * 面试题 10.10. 数字流的秩
 * 假设你正在读取一串整数。每隔一段时间，你希望能找出数字 x 的秩(小于或等于 x 的值的个数)。请实现数据结构和算法来支持这些操作，也就是说：
 *
 * 实现 track(int x) 方法，每读入一个数字都会调用该方法；
 *
 * 实现 getRankOfNumber(int x) 方法，返回小于或等于 x 的值的个数。
 *
 * 注意：本题相对原题稍作改动
 *
 * 示例:
 *
 * 输入:
 * ["StreamRank", "getRankOfNumber", "track", "getRankOfNumber"]
 * [[], [1], [0], [0]]
 * 输出:
 * [null,0,null,1]
 * 提示：
 *
 * x <= 50000
 * track 和 getRankOfNumber 方法的调用次数均不超过 2000 次
 */
public class RankFromStream {

    class StreamRank {

        int[] tree;

        int n;

        public int lowBit(int x) {
            return x&(-x);
        }

        public void add(int x, int v) {
            while(x<n) {
                tree[x] += v;
                x += lowBit(x);
            }
        }

        public int query(int x) {
            int ans = 0;
            while(x>0) {
                ans += tree[x];
                x -= lowBit(x);
            }
            return ans;
        }

        public StreamRank() {
            n = 50002;
            tree = new int[n];
        }

        public void track(int x) {
            add(x+1, 1);
        }

        public int getRankOfNumber(int x) {
            return query(x+1);
        }

    }

    /**
     * Your StreamRank object will be instantiated and called as such:
     * StreamRank obj = new StreamRank();
     * obj.track(x);
     * int param_2 = obj.getRankOfNumber(x);
     */

}