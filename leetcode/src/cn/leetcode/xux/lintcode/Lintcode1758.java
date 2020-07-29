package cn.leetcode.xux.lintcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

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

    /**
     * TODO
     * @param a: The coordinates of the lower left and upper right corners of the n rectangles.
     * @return: the total area of the last coverage of all rectangles
     */
    public double getArea(double[][] a) {
        // Write your code here
        TreeSet<Double> set = new TreeSet<>((o1,o2)->(o1-o2<1E-10?0:(o1-o2<0?-1:1)));
        for(double[] d : a) {
            set.add(d[1]);
            set.add(d[3]);
        }
        int n = set.size();
        int idx = 0;
        Map<Double, Integer> Mapping = new HashMap<>();
       
        return 0;

    }

    class Node {
        int start;
        int end;
        Double left;
        Double right;

        Node(int start, int end, Double left, Double right) {
            this.start = start;
            this.end = end;
            this.left = left;
            this.right = right;
        }

    }

}
