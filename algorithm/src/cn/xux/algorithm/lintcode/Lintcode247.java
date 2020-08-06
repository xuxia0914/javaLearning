package cn.xux.algorithm.lintcode;

/**
 * 247. 线段树查询 II
 * 中文English
 * 对于一个数组，我们可以对其建立一棵 线段树,
 * 每个结点存储一个额外的值 count 来代表这个结点所指代的数组区间内的元素个数. (数组中并不一定每个位置上都有元素)
 * 实现一个 query 的方法，该方法接受三个参数 root, start 和 end,
 * 分别代表线段树的根节点和需要查询的区间，找到数组中在区间[start, end]内的元素个数。
 *
 * 样例
 * 样例 1:
 *
 * 输入："[0,3,count=3][0,1,count=1][2,3,count=2][0,0,count=1][1,1,count=0][2,2,count=1][3,3,count=1]"
 * ,[[1, 1], [1, 2], [2, 3], [0, 2]]
 * 输出：[0,1,2,2]
 * 解释：
 * 对应的线段树为：
 *
 * 	                     [0, 3, count=3]
 * 	                     /             \
 * 	          [0,1,count=1]             [2,3,count=2]
 * 	          /         \               /            \
 * 	   [0,0,count=1] [1,1,count=0] [2,2,count=1], [3,3,count=1]
 *
 * Input : query(1,1), Output: 0
 *
 * Input : query(1,2), Output: 1
 *
 * Input : query(2,3), Output: 2
 *
 * Input : query(0,2), Output: 2
 * 样例 2:
 *
 * 输入："[0,3,count=3][0,1,count=1][2,3,count=2][0,0,count=1][1,1,count=0][2,2,count=0][3,3,count=1]",[[1, 1], [1, 2], [2, 3], [0, 2]]
 * 输出：[0,0,1,1]
 * 解释：
 * 对应的线段树为：
 *
 * 	                     [0, 3, count=2]
 * 	                     /             \
 * 	          [0,1,count=1]             [2,3,count=1]
 * 	          /         \               /            \
 * 	   [0,0,count=1] [1,1,count=0] [2,2,count=0], [3,3,count=1]
 *
 * Input : query(1,1), Output: 0
 *
 * Input : query(1,2), Output: 0
 *
 * Input : query(2,3), Output: 1
 *
 * Input : query(0,2), Output: 1
 * 注意事项
 * 为了能更好地理解这道题，你最好先完成线段树的构造和线段树的查询
 */
public class Lintcode247 {

    /*
     * @param root: The root of segment tree.
     * @param start: start value.
     * @param end: end value.
     * @return: The count number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
        // write your code here
        if(root==null||start>root.end||end<root.start) {
            return 0;
        }
        if(root.start>=start&&root.end<=end) {
            return root.count;
        }
        int ns = Math.max(root.start, start);
        int ne = Math.max(root.end, end);
        return query(root.left, ns, ne)+query(root.right, ns, ne);
    }

    //Definition of SegmentTreeNode:
    class SegmentTreeNode {

        public int start, end, count;
        public SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end, int count) {
            this.start = start;
            this.end = end;
            this.count = count;
            this.left = null;
            this.right = null;
        }
        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
        }

    }

}