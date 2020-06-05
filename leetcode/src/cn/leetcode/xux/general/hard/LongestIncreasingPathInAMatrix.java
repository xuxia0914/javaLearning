package cn.leetcode.xux.general.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer matrix, find the length of the longest increasing path.
 * From each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 * Example 1:
 * nums = [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * Return 4
 * The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 * nums = [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * Return 4
 * The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class LongestIncreasingPathInAMatrix {

    public static List<Integer> solution(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        if(matrix==null||matrix.length==0||matrix[0].length==0) {
            return result;
        }
        int height = matrix.length, length = matrix[0].length;
        for(int i=0;i<height;i++) {
            for(int j=0;j<length;j++) {
                List<Integer> currResult = helper(matrix, i, j);
                if(currResult.size()+1>result.size()) {
                    result = currResult;
                }
            }
        }
        return result;
    }

    public static List<Integer> helper(int[][] matrix, int i, int j) {
        List<Integer> result = new ArrayList<Integer>();
        int height = matrix.length, length = matrix[0].length;
        int curr = matrix[i][j];
        result.add(curr);
        matrix[i][j] = 0;
        if(i>0&&matrix[i-1][j]>curr&&matrix[i-1][j]!=0) {
            List<Integer> preResult = helper(matrix, i-1, j);
            if(preResult.size()+1>result.size()) {
                preResult.add(0, curr);
                result = preResult;
            }
        }
        if(i<height-1&&matrix[i+1][j]>curr&&matrix[i+1][j]!=0) {
            List<Integer> preResult = helper(matrix, i+1, j);
            if(preResult.size()+1>result.size()) {
                preResult.add(0, curr);
                result = preResult;
            }
        }
        if(j>0&&matrix[i][j-1]>curr&&matrix[i][j-1]!=0) {
            List<Integer> preResult = helper(matrix, i, j-1);
            if(preResult.size()+1>result.size()) {
                preResult.add(0, curr);
                result = preResult;
            }
        }
        if(j<length-1&&matrix[i][j+1]>curr&&matrix[i][j+1]!=0) {
            List<Integer> preResult = helper(matrix, i, j+1);
            if(preResult.size()+1>result.size()) {
                preResult.add(0, curr);
                result = preResult;
            }
        }
        matrix[i][j] = curr;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{
                {9,9,4},
                {6,6,8},
                {2,1,1}
        }));
        System.out.println(solution(new int[][]{
                {3,4,5},
                {3,2,6},
                {2,2,1}
        }));
    }

}
