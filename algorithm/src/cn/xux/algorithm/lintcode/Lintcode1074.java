package cn.xux.algorithm.lintcode;

/**
 * 1074. 范围模块
 * 中文English
 * 范围模块是跟踪数字范围的模块。 您的任务是以有效的方式设计和实现以下接口。
 * addRange(int left，int right): 添加左闭右开[left，right)的区间，跟踪区间中的每个实数。
 * 如果添加的区间里与已经跟踪的实数部分重合，那么就把区间内没有跟踪的实数也加进去。
 * queryRange(int left，int right): 当且仅当当前[left，right)中的每个实数都被跟踪时，返回true。
 * removeRange(int left，int right): 停止跟踪[left，right)区间内当前已经跟踪的每个实数。
 *
 * 样例1
 * 输入:
 * addRange(10,20)
 * removeRange(14,16)
 * queryRange(10,14)
 * queryRange(13,15)
 * queryRange(16,17)
 * 输出: [true,false,true]
 * 说明:
 * [10, 14)里的所有数字有已被跟踪
 * 一些数字，例如：14, 14.03, 14.17 [13, 15)并没有被跟踪
 * 尽管有remove的操作，区间[16, 17)中的16仍被跟踪
 *
 * 样例2
 * 输入:
 * addRange(1,2)
 * queryRange(2,3)
 * addRange(11,20)
 * queryRange(15,20)
 * 输出: [false,true]
 *
 * 注意事项
 * 一个左闭右开的区间 [left, right) 包含了 left <= x < right范围内所有的实数.
 * 函数 addRange, queryRange, removeRange中参数的取值范围为0 < left < right < 10^9.
 * 测试样例中调用addRange的次数最多为 1000.
 * 测试样例中调用queryRange 的次数最多为5000.
 * 测试样例中调用removeRange的次数最多为 1000.
 */
public class Lintcode1074 {

    SegmentTreeNode root;

    public Lintcode1074() {
        //do some initialization if necessary
        root = build(1, 999999998);
    }

    public void addRange(int left, int right) {
        //write your code here
        add(root, left, right-1);
    }

    public boolean queryRange(int left, int right) {
        //write your code here
        return query(root, left, right-1);
    }

    public void removeRange(int left, int right) {
        //write your code here
        remove(root, left, right-1);
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

    }

    private SegmentTreeNode build(int start, int end) {
        if(start>end) {
            return null;
        }
        if(start==end) {
            return new SegmentTreeNode(start, end, 0);
        }
        int mid = start+(end-start)/2;
        SegmentTreeNode left = build(start, mid);
        SegmentTreeNode right = build(mid+1, end);
        SegmentTreeNode ans = new SegmentTreeNode(start, end, 0);
        ans.left = left;
        ans.right = right;
        return ans;
    }

    private void add(SegmentTreeNode node, int start, int end) {
        if(start>end||node.end<start||node.start>end
                ||node.end-node.start+1==node.count) {
            return;
        }
        if(node.start==node.end) {
            node.count = 1;
            return;
        }
        add(node.left, start, end);
        add(node.right, start, end);
        node.count = node.left.count+node.right.count;
    }

    private boolean query(SegmentTreeNode node, int start, int end) {
        if(node.start>start||node.end<end||node.count==0) {
            return false;
        }
        if(node.end-node.start+1==node.count) {
            return true;
        }
        int mid = start+(end-start)/2;
        if(end<=mid) {
            return query(node.left, start, end);
        }else if(start>=mid+1) {
            return query(node.right, start, end);
        }else {
            return query(node.left, start, mid)&&query(node.right, mid+1, end);
        }
    }

    private void remove(SegmentTreeNode node, int start, int end) {
        if(start>end||node.end<start||node.start>end
                ||node.end-node.start+1==0) {
            return;
        }
        if(node.start==node.end) {
            node.count = 0;
            return;
        }
        remove(node.left, start, end);
        remove(node.right, start, end);
        node.count = node.left.count+node.right.count;
    }

}
