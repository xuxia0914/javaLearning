package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 例如，给定三角形：
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 说明：
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class Triangle {

    /**
     * 原地操作
     * 执行用时 :7 ms, 在所有 Java 提交中击败了35.50%的用户
     * 内存消耗 :37 MB, 在所有 Java 提交中击败了80.48%的用户
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle==null||triangle.size()==0) {
            return 0;
        }
        int n = triangle.size();
        for(int i=1;i<n;i++) {
            for(int j=0;j<=i;j++) {
                if(j==0) {
                    triangle.get(i).set(j, triangle.get(i).get(j)+triangle.get(i-1).get(j));
                }else if(j==i) {
                    triangle.get(i).set(j, triangle.get(i).get(j)+triangle.get(i-1).get(j-1));
                }else {
                    triangle.get(i).set(j, triangle.get(i).get(j)+Math.min(triangle.get(i-1).get(j-1), triangle.get(i-1).get(j)));
                }
            }
        }
        int res = triangle.get(n-1).get(0);
        for(int i=1;i<n;i++) {
            res = Math.min(triangle.get(n-1).get(i), res);
        }
        return res;
    }

    /**
     * 如果原数据只读
     * 执行用时 :2 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗 :37.6 MB, 在所有 Java 提交中击败了62.00%的用户
     * @param triangle
     * @return
     */
    public int minimumTotal1(List<List<Integer>> triangle) {
        if(triangle==null||triangle.size()==0) {
            return 0;
        }
        int n = triangle.size();
        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);
        int tmp = 0;
        for(int i=1;i<n;i++) {
            for(int j=0;j<=i;j++) {
                if(j==0) {
                    tmp = dp[j];
                    dp[j] = dp[j]+triangle.get(i).get(j);
                }else if(j==i) {
                    dp[j] = tmp + triangle.get(i).get(j);
                }else {
                    int tmp1 = dp[j];
                    dp[j] = Math.min(tmp1, dp[j])+triangle.get(i).get(j);
                    tmp = tmp1;
                }
            }
        }
        int res = dp[0];
        for(int i : dp) {
            res = Math.min(i, res);
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> list0 = new ArrayList<>();
        list0.add(2);
        triangle.add(list0);
        List<Integer> list1 = new ArrayList<>();
        list1.add(3);
        list1.add(4);
        triangle.add(list1);
        List<Integer> list2 = new ArrayList<>();
        list2.add(6);
        list2.add(5);
        list2.add(7);
        triangle.add(list2);
        List<Integer> list3 = new ArrayList<>();
        list3.add(4);
        list3.add(1);
        list3.add(8);
        list3.add(3);
        triangle.add(list3);
        System.out.println(new Triangle().minimumTotal1(triangle));
    }

}
