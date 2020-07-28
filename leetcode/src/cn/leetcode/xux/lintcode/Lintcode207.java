package cn.leetcode.xux.lintcode;

/**
 * 207. 区间求和 II
 * 中文English
 * 在类的构造函数中给一个整数数组, 实现两个方法 query(start, end) 和 modify(index, value):
 *
 * 对于 query(start, end), 返回数组中下标 start 到 end 的 和。
 * 对于 modify(index, value), 修改数组中下标为 index 上的数为 value.
 * 样例
 * 样例1
 *
 * 输入:
 * [1,2,7,8,5]
 * [query(0,2),modify(0,4),query(0,1),modify(2,1),query(2,4)]
 * 输出: [10,6,14]
 * 说明:
 * 给定数组 A = [1,2,7,8,5].
 * 在query(0, 2)后, 1 + 2 + 7 = 10,
 * 在modify(0, 4)后, 将 A[0] 修改为 4， A = [4,2,7,8,5].
 * 在query(0, 1)后, 4 + 2 = 6.
 * 在modify(2, 1)后, 将 A[2] 修改为 1，A = [4,2,1,8,5].
 * After query(2, 4), 1 + 8 + 5 = 14.
 * 样例2
 *
 * 输入:
 * [1,2,3,4,5]
 * [query(0,0),query(1,2),quert(3,4)]
 * 输出: [1,5,9]
 * 说明:
 * 1 = 1
 * 2 + 3 = 5
 * 4 + 5 = 9
 * 挑战
 * query 和 modify的时间复杂度需要为O(logN).
 *
 * 注意事项
 * 在做此题前，建议先完成以下三题：线段树的构造， 线段树的查询，以及线段树的修改。
 */
public class Lintcode207 {

    /* you may need to use some attributes here */
    SegmentTreeNode segmentree;
    int n;

    /*
     * @param A: An integer array
     */
    public Lintcode207(int[] A) {
        // do intialization if necessary
        if(A==null||A.length==0) {
            return;
        }
        n = A.length;
        segmentree = build(A, 0, n-1);
    }

    /*
     * @param start: An integer
     * @param end: An integer
     * @return: The sum from start to end
     */
    public long query(int start, int end) {
        // write your code here
        if(start>end) {
            return 0;
        }
        return query(segmentree, start, end);
    }

    private long query(SegmentTreeNode node, int start, int end) {
        if(start<=node.start&&end>=node.end) {
            return node.sum;
        }
        if(end<node.start||start>node.end) {
            return 0;
        }
        return query(node.left, Math.max(start, node.start), Math.min(end, node.end))
                + query(node.right, Math.max(start, node.start), Math.min(end, node.end));
    }

    /*
     * @param index: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void modify(int index, int value) {
        // write your code here
        if(index<0||index>=n) {
            return ;
        }
        modify(segmentree, index, value);
    }

    private void modify(SegmentTreeNode node, int index, int value) {
        if(index<node.start||index>node.end) {
            return;
        }
        if(node.start==node.end&&node.start==index&&node.sum!=value) {
            node.sum = value;
            return;
        }
        modify(node.left, index, value);
        modify(node.right, index, value);
        node.sum = node.left.sum+node.right.sum;
    }

    // Definition of SegmentTreeNode:
    class SegmentTreeNode {
        public int start, end;
        public long sum;
        public SegmentTreeNode left, right;
        public SegmentTreeNode(int start, int end, long sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
            this.left = this.right = null;
        }
    }

    private SegmentTreeNode build(int[] A, int start, int end) {
        if(start==end) {
            return new SegmentTreeNode(start, end, A[start]);
        }
        int mid = (start+end)/2;
        SegmentTreeNode left = build(A, start, mid);
        SegmentTreeNode right = build(A, mid+1, end);
        SegmentTreeNode ans = new SegmentTreeNode(start, end, left.sum+right.sum);
        ans.left = left;
        ans.right = right;
        return ans;
    }

}

