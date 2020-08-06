package cn.xux.algorithm.lintcode;

/**
 * 202. 线段树的查询
 * 中文English
 * 对于一个有n个数的整数数组，在对应的线段树中, 根节点所代表的区间为0-n-1,
 * 每个节点有一个额外的属性max，值为该节点所代表的数组区间start到end内的最大值。
 * 为SegmentTree设计一个 query 的方法，接受3个参数root, start和end，
 * 线段树root所代表的数组中子区间[start, end]内的最大值。
 *
 * 样例
 * 样例 1:
 * 输入："[0,3,max=4][0,1,max=4][2,3,max=3][0,0,max=1][1,1,max=4][2,2,max=2][3,3,max=3]",1,2
 * 输出：4
 * 解释：
 * 对于数组 [1, 4, 2, 3], 对应的线段树为 :
 *
 * 	                  [0, 3, max=4]
 * 	                 /             \
 * 	          [0,1,max=4]        [2,3,max=3]
 * 	          /         \        /         \
 * 	   [0,0,max=1] [1,1,max=4] [2,2,max=2], [3,3,max=3]
 * [1,2]区间最大值为4
 *
 * 样例 2:
 * 输入："[0,3,max=4][0,1,max=4][2,3,max=3][0,0,max=1][1,1,max=4][2,2,max=2][3,3,max=3]",2,3
 * 输出：3
 * 解释：
 * 对于数组 [1, 4, 2, 3], 对应的线段树为 :
 *
 * 	                  [0, 3, max=4]
 * 	                 /             \
 * 	          [0,1,max=4]        [2,3,max=3]
 * 	          /         \        /         \
 * 	   [0,0,max=1] [1,1,max=4] [2,2,max=2], [3,3,max=3]
 * [2,3]区间最大值为3
 * 注意事项
 * 在做此题之前，请先完成 线段树构造 这道题目。
 */
public class Lintcode202 {

    /**
     * @param root: The root of segment tree.
     * @param start: start value.
     * @param end: end value.
     * @return: The maximum number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
        // write your code here
        int ans = Integer.MIN_VALUE;
        if(root==null||start>end) {
            return ans;
        }
        if(root.start>=start&&root.end<=end) {
            return root.max;
        }
        return Math.max(query(root.left, Math.max(start, root.start), Math.min(end, root.end)),
                query(root.right, Math.max(start, root.start), Math.min(end, root.end)));
    }

    // Definition of SegmentTreeNode:
    class SegmentTreeNode {
        public int start, end, max;
        public SegmentTreeNode left, right;
        public SegmentTreeNode(int start, int end, int max) {
            this.start = start;
            this.end = end;
            this.max = max;
            this.left = this.right = null;
        }
    }

}

