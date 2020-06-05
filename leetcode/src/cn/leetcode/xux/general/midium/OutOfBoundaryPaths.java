package cn.leetcode.xux.general.midium;

/**
 * 576. 出界的路径数
 * 给定一个 m × n 的网格和一个球。球的起始坐标为 (i,j) ，
 * 你可以将球移到相邻的单元格内，或者往上、下、左、右四个方向上移动使球穿过网格边界。
 * 但是，你最多可以移动 N 次。找出可以将球移出边界的路径数量。答案可能非常大，返回 结果 mod 109 + 7 的值。
 *
 * 示例 1：
 * 输入: m = 2, n = 2, N = 2, i = 0, j = 0
 * 输出: 6
 * 解释:
 *
 * 示例 2：
 * 输入: m = 1, n = 3, N = 3, i = 0, j = 1
 * 输出: 12
 * 解释:
 *
 * 说明:
 * 球一旦出界，就不能再被移动回网格内。
 * 网格的长度和高度在 [1,50] 的范围内。
 * N 在 [0,50] 的范围内。
 */
public class OutOfBoundaryPaths {

    public static void main(String[] args) {
        System.out.println(new OutOfBoundaryPaths().findPaths(2,2,2,0,0));
    }

    public int findPaths(int m, int n, int N, int i, int j) {
        int mod = 1000000007;
        int[][] matrix = new int[m][n];
        matrix[i][j] = 1;
        int result = 0;
        for(int k=0;k<N;k++) {
            int[][] tmp = new int[m][n];
            for(int x=0;x<m;x++) {
                tmp[x] = matrix[x].clone();
            }
            for(int x=0;x<m;x++) {
                for(int y=0;y<n;y++) {
                    matrix[x][y] = 0;
                    if(x==0) {
                        result = (result+tmp[x][y])%mod;
                    }else {
                        matrix[x][y] = (matrix[x][y]+tmp[x-1][y])%mod;
                    }
                    if(x==m-1) {
                        result = (result+tmp[x][y])%mod;
                    }else {
                        matrix[x][y] = (matrix[x][y]+tmp[x+1][y])%mod;
                    }
                    if(y==0) {
                        result = (result+tmp[x][y])%mod;
                    }else {
                        matrix[x][y] = (matrix[x][y]+tmp[x][y-1])%mod;
                    }
                    if(y==n-1) {
                        result = (result+tmp[x][y])%mod;
                    }else {
                        matrix[x][y] = (matrix[x][y]+tmp[x][y+1])%mod;
                    }
                }
            }
        }
        return result;
    }

}
