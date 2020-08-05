package cn.leetcode.xux.lintcode;

import java.util.*;

/**
 * 1758. 矩形覆盖面积
 * cat-only-icon
 * CAT 专属题目
 * 中文English
 * 给定n个矩形的左下角和右上角的坐标，求所有矩形最后的覆盖的总面积。
 *
 * 样例
 * 样例 1:
 *
 * 输入: a=[[10,10,20,20],[15,15,25,25.5]]`
 * 输出: 180
 * 解释:
 * 坐标分别为(10,10),(20,20) 和(15,15),(25,25.5)的两个矩形的面积是180。
 * 样例 2:
 *
 * 输入: a=[[10,10,20,20]]`
 * 输出: 100
 * 解释:
 * 坐标分别为 (10,10),(20,20) 的面积是100。
 * 注意事项
 * n 不超过100
 * 坐标的值都大于0且小于100000
 */
public class Lintcode1758 {

    public static void main(String[] args) {
        System.out.println(new Lintcode1758().getArea(new double[][]{{10125.83,44558.92,40091.03,88168.23},{36573.90,90054.09,98174.92,94502.82},{29985.33,4375.42,75863.57,21183.09},{22667.52,2749.48,29152.45,28600.78},{15893.60,32198.77,62946.75,49236.85},{35492.76,8424.63,58701.99,45989.04},{25730.08,77741.74,54432.48,80344.28},{4229.75,36675.49,38255.92,74470.22},{1739.05,78098.15,76467.21,98026.59},{25585.41,26199.05,92511.72,56910.11}}));
    }

    // heights[i]表示第i条水平线和第i+1条水平线之间的高度
    double[] heights;
    // 离散化每条水平线，以便使用线段树(即把每条水平线从小到大映射成0 - n-1)
    Map<Double, Integer> mapping;

    /**
     * @param a: The coordinates of the lower left and upper right corners of the n rectangles.
     * @return: the total area of the last coverage of all rectangles
     */
    public double getArea(double[][] a) {
        // Write your code here
        // 保存所有水平方向上的线，从小到大排序
        TreeSet<Double> set = new TreeSet<>();
        for(double[] d : a) {
            set.add(d[1]);
            set.add(d[3]);
        }
        int n = set.size();
        int idx = 0;
        heights = new double[n-1];
        mapping = new HashMap<>();
        while(set.size()>0) {
            double curr = set.pollFirst();
            if(set.size()>0) {
                heights[idx] = set.first()-curr;
            }
            mapping.put(curr, idx++);
        }
        // 初始化线段树
        Node root = build(0, n-2);
        // 把每个矩形插入线段树
        for(double[] d : a) {
            int start = mapping.get(d[1]);
            add(root, start, mapping.get(d[3])-1, d[0], d[2]);
        }
        // 计算面积
        return calc(root);
    }

    private Node build(int start, int end) {
        if(start==end) {
            return new Node(start, end);
        }
        int mid = (start+end)/2;
        Node left = build(start, mid);
        Node right = build(mid+1, end);
        Node ans = new Node(start, end);
        ans.left = left;
        ans.right = right;
        return ans;
    }

    private void add(Node node, int start, int end, Double min, Double max) {
        if(node==null||node.start>end||node.end<start) {
            return;
        }
        if(node.start==node.end) {
            if(node.intervals==null) {
                node.intervals = new PriorityQueue<>((o1, o2)->(Math.abs(o1[0]-o2[0])<1E-10?0:(o1[0]-o2[0]<0?-1:1)));
                node.intervals.offer(new double[]{min, max});
            }else {
                node.intervals.offer(new double[]{min, max});
                PriorityQueue<double[]> newQueue = new PriorityQueue<>((o1, o2)->(Math.abs(o1[0]-o2[0])<1E-10?0:(o1[0]-o2[0]<0?-1:1)));
                double[] pre = node.intervals.poll();
                newQueue.offer(pre);
                while(!node.intervals.isEmpty()) {
                    double[] curr = node.intervals.poll();
                    if(curr[0]<=pre[1]) {
                        pre[1] = Math.max(pre[1], curr[1]);
                    }else {
                        newQueue.offer(curr);
                        pre = curr;
                    }
                }
                node.intervals = newQueue;
            }
        }else {
            add(node.left, start, end, min, max);
            add(node.right, start, end, min, max);
        }
    }

    private double calc(Node node) {
        if(node.start==node.end) {
            if(node.intervals==null||node.intervals.size()==0) {
                return 0d;
            }else {
                double ans = 0;
                for(double[] interval : node.intervals) {
                    ans += heights[node.start]*(interval[1]-interval[0]);
                }
                return ans;
            }
        }
        return calc(node.left)+calc(node.right);
    }

    class Node {
        int start, end;
        // start==end时，intervals可能不为空
        // 表示在第start -> start+1条水平线之间，所有的覆盖区域的不重叠不连续的竖直线区间(从小到大排序)
        PriorityQueue<double[]> intervals;
        Node left = null, right = null;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}
