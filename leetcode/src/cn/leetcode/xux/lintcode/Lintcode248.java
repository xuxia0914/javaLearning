package cn.leetcode.xux.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 248. 统计比给定整数小的数的个数
 * 中文English
 * 给定一个整数数组 （下标由 0 到 n-1，其中 n 表示数组的规模，数值范围由 0 到 10000），
 * 以及一个 查询列表。对于每一个查询，将会给你一个整数，请你返回该数组中小于给定整数的元素的数量。
 *
 * 样例
 * 样例 1:
 *
 * 输入: array =[1,2,7,8,5] queries =[1,8,5]
 * 输出:[0,4,2]
 * 样例 2:
 *
 * 输入: array =[3,4,5,8] queries =[2,4]
 * 输出:[0,1]
 * 挑战
 * 可否用一下三种方法完成以上题目。
 *
 * 仅用循环方法
 *
 * 分类搜索 和 二进制搜索
 *
 * 构建 线段树 和 搜索
 *
 * 注意事项
 * 在做此题前，最好先完成 线段树的构造 and 线段树查询 II 这两道题目。
 */
public class Lintcode248 {

    public static void main(String[] args) {
        System.out.println(new Lintcode248().countOfSmallerNumber(
                new int[]{1,2,3,4,5,6},
                new int[]{1,2,3,4}
        ));
    }

    /**
     * @param A: An integer array
     * @param queries: The query list
     * @return: The number of element in the array that are smaller that the given integer
     */
    public List<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        // write your code here
        List<Integer> ans = new ArrayList<>();
        if(queries==null||queries.length==0) {
            return ans;
        }
        root = build(0, 10000);
        for(int num : A) {
            insert(root, num);
        }
        for(int num : queries) {
            ans.add(query(root, num));
        }
        return ans;
    }

    private SegmentTreeNode build(int start, int end) {
        if(start>end) {
            return null;
        }
        if(start==end) {
            return new SegmentTreeNode(start, end, 0);
        }
        int mid = (start+end)/2;
        SegmentTreeNode left = build(start, mid);
        SegmentTreeNode right = build(mid+1, end);
        SegmentTreeNode ans = new SegmentTreeNode(start, end, 0);
        ans.left = left;
        ans.right = right;
        return ans;
    }

    SegmentTreeNode root = null;

    private void insert(SegmentTreeNode node, int num) {
        if(num<node.start||num>node.end) {
            return;
        }
        if(node.start==node.end) {
            node.count++;
            return;
        }
        insert(node.left, num);
        insert(node.right, num);
        node.count = node.left.count+node.right.count;
    }

    private int query(SegmentTreeNode node, int num) {
        if(node==null||node.start>=num) {
            return 0;
        }
        if(num>node.end) {
            return node.count;
        }
        return query(node.left, num)+query(node.right, num);
    }

    //      Definition of SegmentTreeNode:
    class SegmentTreeNode {

        public int start, end, count;
        public SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end, int count) {
            this.start = start;
            this.end = end;
            this.count = count;
            this.left = this.right = null;
        }
    }

}