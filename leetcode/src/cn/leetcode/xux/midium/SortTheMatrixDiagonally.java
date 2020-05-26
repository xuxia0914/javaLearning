package cn.leetcode.xux.midium;

/**
 * 1329. 将矩阵按对角线排序
 * 给你一个 m * n 的整数矩阵 mat ，
 * 请你将同一条对角线上的元素（从左上到右下）按升序排序后，返回排好序的矩阵。
 *
 * 示例 1：
 * 输入：mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
 * 输出：[[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 *
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * 1 <= mat[i][j] <= 100
 */
public class SortTheMatrixDiagonally {

    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        for(int i=0;i<m-1;i++) {
            int[] start = new int[]{i, 0};
            int[] end = new int[]{Math.min(i+n-1, m-1), Math.min(n-1, m-i-1)};
            sort(mat, start, end);
        }
        for(int j=1;j<n-1;j++) {
            int[] start = new int[]{0, j};
            int[] end = new int[]{Math.min(m-1, n-1-j), Math.min(j+m-1, n-1)};
            sort(mat, start, end);
        }
        return mat;
    }

    public void sort(int[][] mat, int[] start, int[] end) {
        if(start[0]>=end[0]) {
            return;
        }
        int[] left = start.clone();
        int[] right = end.clone();
        int key = mat[left[0]][left[1]];
        while(left[0]<right[0]) {
            while(left[0]<right[0]&&mat[right[0]][right[1]]>=key) {
                right[0]--;
                right[1]--;
            }
            while(left[0]<right[0]&&mat[left[0]][left[1]]<=key) {
                left[0]++;
                left[1]++;
            }
            if(left[0]<right[0]) {
                int tmp = mat[left[0]][left[1]];
                mat[left[0]][left[1]] = mat[right[0]][right[1]];
                mat[right[0]][right[1]] = tmp;
            }
        }
        mat[start[0]][start[1]] = mat[right[0]][right[1]];
        mat[right[0]][right[1]] = key;
        sort(mat, start, new int[]{right[0]-1, right[1]-1});
        sort(mat, new int[]{right[0]+1, right[1]+1}, end);
    }

}
