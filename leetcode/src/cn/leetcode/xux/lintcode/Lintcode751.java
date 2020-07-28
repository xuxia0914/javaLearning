package cn.leetcode.xux.lintcode;

/**
 * 751. 约翰的生意
 * 中文English
 * 在一条数轴上，有n个城市，编号从0 ~ n – 1 , 约翰打算在这n个城市做点生意，
 * 他对Armani的一批货物感兴趣，每个城市对于这批货物都有一个价格prices[i]。
 * 对于城市x,约翰可从城市编号为[x - k, x + k]购买货物，然后卖到城市x,问约翰在每个城市最多能赚到多少钱？
 *
 * 样例
 * 样例1
 *
 * 输入: prices = [1, 3, 2, 1, 5] 和 k = 2
 * 输出: [0, 2, 1, 0, 4]
 * 解释:
 * i = 0，约翰可去的城市有0~2因为1、2号城市的价格比0号城市的价格高，所以赚不了钱，即 ans[0] = 0。
 * i = 1，可去的城市有0~3，可以从0号或者3号城市购买货物赚取的差价最大，即ans[1] = 2。
 * i = 2，可去的城市有0~4，显然从3号城市购买货物赚取的差价最大，即ans[2] = 1。
 * i = 3，可去的城市有1~4，没有其他城市的价格比3号城市价格低，所以赚不了钱，ans[3] = 0。
 * i = 4，可去的城市有2~4，从3号城市购买货物赚取的差价最大，即ans[4] = 4。
 * 样例2
 *
 * 输入: prices = [1, 1, 1, 1, 1] 和 k = 1
 * 输出: [0, 0, 0, 0, 0]
 * 解释:
 * 所有城市价格都一样，所以不能赚到钱，即所有的ans都为0。
 * 注意事项
 * prices.length 范围为[2, 100000], k <= 100000。
 */
public class Lintcode751 {

    /**
     * @param A: The prices [i]
     * @param k:
     * @return: The ans array
     */
    public int[] business(int[] A, int k) {
        // Write your code here
        int[] ans = new int[A.length];
        if(k<=0) {
            return ans;
        }
        SegmentTreeNode root = build(A, 0, A.length-1);
        for(int i=0;i<A.length;i++) {
            ans[i] = A[i]-query(root, i-k, i+k);
        }
        return ans;
    }

    private SegmentTreeNode build(int[] A, int start, int end) {
        if(start>end) {
            return null;
        }
        if(start==end) {
            return new SegmentTreeNode(start, end, A[start]);
        }
        int mid = (start+end)/2;
        SegmentTreeNode left = build(A, start, mid);
        SegmentTreeNode right = build(A, mid+1, end);
        SegmentTreeNode ans = new SegmentTreeNode(start, end, Math.min(left.min, right.min));
        ans.left = left;
        ans.right = right;
        return ans;
    }

    private int query(SegmentTreeNode node, int start, int end) {
        if(start>node.end||end<node.start) {
            return Integer.MAX_VALUE;
        }
        if(start<=node.start&&end>=node.end) {
            return node.min;
        }
        return Math.min(query(node.left, Math.max(node.start, start), Math.min(node.end, end)),
                query(node.right, Math.max(node.start, start), Math.min(node.end, end)));
    }

    //      Definition of SegmentTreeNode:
    class SegmentTreeNode {
        public int start, end, min;
        public SegmentTreeNode left, right;
        public SegmentTreeNode(int start, int end, int min) {
            this.start = start;
            this.end = end;
            this.min = min;
            this.left = this.right = null;
        }
    }

}
