package cn.leetcode.xux.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 205. 区间最小数
 * 中文English
 * 给定一个整数数组（下标由 0 到 n-1，其中 n 表示数组的规模），以及一个查询列表。
 * 每一个查询列表有两个整数 [start, end]。
 * 对于每个查询，计算出数组中从下标 start 到 end 之间的数的最小值，并返回在结果列表中。
 *
 * 样例
 * 样例1：
 * 输入：数组 ：[1,2,7,8,5] 查询 ：[(1,2),(0,4),(2,4)]。输出：[2,1,5]
 * 样例2：
 * 输入：数组 ：[4,5,7,1] 查询 ：[(1,2),(1,3)]。输出：[5,1]
 *
 * 挑战
 * 每次查询在O(logN)的时间内完成
 *
 * 注意事项
 * 在做此题前，建议先完成以下三题：线段树的构造， 线段树的查询，以及线段树的修改。
 */
public class Lintcode205 {

    /**
     * @param A: An integer array
     * @param queries: An query list
     * @return: The result list
     */
    public List<Integer> intervalMinNumber(int[] A, List<Interval> queries) {
        // write your code here
        List<Integer> ans = new ArrayList<>();
        if(queries==null||queries.size()==0) {
            return ans;
        }
        SegmentTree root = init(A, 0, A.length-1);
        for(Interval interval : queries) {
            /*int min = Integer.MAX_VALUE;
            for(int i=interval.start;i<=interval.end;i++) {
                min = Math.min(min, A[i]);
            }*/
            ans.add(query(root, interval.start, interval.end));
        }
        return ans;
    }

    private SegmentTree init(int[] nums, int start, int end) {
        if(start>end) {
            return null;
        }
        if(start==end) {
            return new SegmentTree(start, end, nums[start]);
        }
        int mid = (start+end)/2;
        SegmentTree left = init(nums, start, mid);
        SegmentTree right = init(nums, mid+1, end);
        SegmentTree root = new SegmentTree(start, end, Math.min(left.min, right.min));
        root.left = left;
        root.right = right;
        return root;
    }

    private int query(SegmentTree root, int start, int end) {
        if(root==null||start>end) {
            return Integer.MAX_VALUE;
        }
        if(start<=root.start&&end>=root.end) {
            return root.min;
        }
        return Math.min(query(root.left, Math.max(start, root.start), Math.min(end, root.end)),
                query(root.right, Math.max(start, root.start), Math.min(end, root.end)));
    }

    class SegmentTree {

        int start;
        int end;
        int min;
        SegmentTree left;
        SegmentTree right;

        SegmentTree(int start, int end, int min) {
            this.start = start;
            this.end = end;
            this.min = min;
            this.left = null;
            this.right = null;
        }

    }

}

//  Definition of Interval:
class Interval {
    int start, end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
