package cn.leetcode.xux.common;

import javafx.util.Pair;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Test1 {

    public static void main(String[] args) {
//        System.out.println(new Test1().numDecodings("19261001"));
    }

    /**
     * 374. 螺旋矩阵
     * 中文English
     * 给定一个包含 m x n 个要素的矩阵，（m 行, n 列），按照螺旋顺序，返回该矩阵中的所有要素。
     *
     * 样例
     * 样例 1:
     *
     * 输入:[[ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ]]
     * 输出: [1,2,3,6,9,8,7,4,5]
     * 样例 2
     *
     * 输入:[[ 6,4,1 ], [ 7,8,9 ]]
     * 输出: [6,4,1,9,8,7]
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        // write your code here
        List<Integer> ans = new ArrayList<>();
        if(matrix==null||matrix.length==0||matrix[0].length==0) {
            return ans;
        }
        int up = 0;
        int bottom = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;
        while(up<=bottom&&left<=right) {
            for(int i=left;i<=right;i++) {
                ans.add(matrix[up][i]);
            }
            for(int i=up+1;i<=bottom;i++) {
                ans.add(matrix[i][right]);
            }
            if(left<right&&up<bottom) {
                for(int i=right-1;i>=left;i--) {
                    ans.add(matrix[bottom][i]);
                }
                for(int i=bottom-1;i>up;i--) {
                    ans.add(matrix[i][left]);
                }
            }
            up++;
            bottom--;
            left++;
            right--;
        }
        return ans;
    }

}
