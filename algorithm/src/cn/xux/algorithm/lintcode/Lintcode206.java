package cn.xux.algorithm.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 206. 区间求和 I
 * 中文English
 * 给定一个整数数组（下标由 0 到 n-1，其中 n 表示数组的规模），以及一个查询列表。
 * 每一个查询列表有两个整数 [start, end] 。
 * 对于每个查询，计算出数组中从下标 start 到 end 之间的数的总和，并返回在结果列表中。
 *
 * 样例
 * 样例 1:
 *
 * 输入: 数组 = [1,2,7,8,5], 查询 = [(0,4),(1,2),(2,4)]
 * 输出: [23,9,20]
 * 样例 2:
 *
 * 输入: 数组 = [4,3,1,2],  查询 = [(1,2),(0,2)]
 * 输出: [4,8]
 * 挑战
 * 每一次查询的时间复杂度为O(logN)
 *
 * 注意事项
 * 在做此题前，建议先完成以下三题：线段树的构造， 线段树的查询，以及线段树的修改。
 */
public class Lintcode206 {

    /**
     * @param A: An integer list
     * @param queries: An query list
     * @return: The result list
     */
    public List<Long> intervalSum(int[] A, List<Interval> queries) {
        // write your code here
        List<Long> ans = new ArrayList<>();
        if(A==null||A.length==0||queries==null||queries.size()==0) {
            return ans;
        }
        SegmentTreeNode root = init(A, 0, A.length-1);
        for(Interval interval : queries) {
            ans.add(query(root, interval.start, interval.end));
        }
        return ans;
    }

    private SegmentTreeNode init(int[] A, int start, int end) {
        if(start>end) {
            return null;
        }
        if(start==end) {
            return new SegmentTreeNode(start, end, A[start]);
        }
        int mid = (start+end)/2;
        SegmentTreeNode left = init(A, start, mid);
        SegmentTreeNode right = init(A, mid+1, end);
        SegmentTreeNode ans = new SegmentTreeNode(start, end, left.sum+right.sum);
        ans.left = left;
        ans.right = right;
        return ans;
    }

    private long query(SegmentTreeNode root, int start, int end) {
        if(root==null||start>end) {
            return 0;
        }
        if(start<=root.start&&end>=root.end) {
            return root.sum;
        }
        return query(root.left, Math.max(root.start, start), Math.min(root.end, end))
                + query(root.right, Math.max(root.start, start), Math.min(root.end, end));
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

//      Definition of Interval:
      class Interval {
          int start, end;
          Interval(int start, int end) {
              this.start = start;
              this.end = end;
          }
      }

}

