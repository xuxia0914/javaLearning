package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 542. 01 矩阵
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 * 两个相邻元素间的距离为 1 。
 *
 * 示例 1:
 * 输入:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 输出:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 *
 * 示例 2:
 * 输入:
 * 0 0 0
 * 0 1 0
 * 1 1 1
 * 输出:
 * 0 0 0
 * 0 1 0
 * 1 2 1
 *
 * 注意:
 * 给定矩阵的元素个数不超过 10000。
 * 给定矩阵中至少有一个元素是 0。
 * 矩阵中的元素只在四个方向上相邻: 上、下、左、右。
 */
public class ZeroOneMatrix {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ZeroOneMatrix().updateMatrix(
                new int[][]{{0,0,0},{0,1,0},{0,0,0}}
        )));
    }

    public int[][] updateMatrix(int[][] matrix) {
        if(matrix==null||matrix.length==0||matrix[0].length==0) {
            return matrix;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] result = new int[m][n];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(matrix[i][j]==1) {
                    int curr = Integer.MAX_VALUE;
                    if(i>0&&result[i-1][j]!=Integer.MAX_VALUE) {
                        curr = Math.min(curr, result[i-1][j]+1);
                    }
                    if(j>0&&result[i][j-1]!=Integer.MAX_VALUE) {
                        curr = Math.min(curr, result[i][j-1]+1);
                    }
                    result[i][j] = curr;
                }
            }
        }
        for(int i=m-1;i>=0;i--) {
            for(int j=n-1;j>=0;j--) {
                if(matrix[i][j]==1) {
                    if(i<m-1&&result[i+1][j]!=Integer.MAX_VALUE) {
                        result[i][j] = Math.min(result[i][j], result[i+1][j]+1);
                    }
                    if(j<n-1&&result[i][j+1]!=Integer.MAX_VALUE) {
                        result[i][j] = Math.min(result[i][j], result[i][j+1]+1);
                    }
                }
            }
        }
        return result;
    }

    public int[][] updateMatrix1(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(mat[i][j]==0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int[][] ans = new int[m][n];
        int level = 1;
        int[][] neis = new int[][]{{-1, 0},{1, 0},{0, -1},{0, 1}};
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-->0) {
                int[] curr = queue.poll();
                for(int[] nei : neis) {
                    int x = curr[0]+nei[0];
                    int y = curr[1]+nei[1];
                    if(x>=0&&x<m&&y>=0&&y<n&&mat[x][y]==1&&ans[x][y]==0) {
                        ans[x][y] = level;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
            level++;
        }
        return ans;
    }

}
