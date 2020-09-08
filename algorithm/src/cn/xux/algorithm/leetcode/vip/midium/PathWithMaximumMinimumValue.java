package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 1102：得分最高的路径
 * 给你一个 R 行 C 列的整数矩阵 A。矩阵上的路径从 [0,0] 开始，在 [R-1,C-1] 结束。
 * 路径沿四个基本方向（上、下、左、右）展开，从一个已访问单元格移动到任一相邻的未访问单元格。
 * 路径的得分是该路径上的 最小 值。例如，路径 8 → 4 → 5 → 9 的值为 4 。
 * 找出所有路径中得分 最高 的那条路径，返回其 得分。
 *
 * 示例 1：
 * 输入：[[5,4,5],[1,2,6],[7,4,6]]
 * 输出：4
 * 解释：得分最高的路径用黄色突出显示。
 *
 * 示例 2：
 * 输入：[[2,2,1,2,2,2],[1,2,2,2,1,2]]
 * 输出：2
 *
 * 示例 3：
 * 输入：[[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
 * 输出：3
 *
 * 提示：
 * 1 <= R, C <= 100
 * 0 <= A[i][j] <= 10^9
 */
public class PathWithMaximumMinimumValue {

    public static void main(String[] args) {
        PathWithMaximumMinimumValue pw = new PathWithMaximumMinimumValue();
        System.out.println(pw.maximumMinimumPath(new int[][]{{5,4,5},{1,2,6},{7,4,6}}));
        System.out.println(pw.maximumMinimumPath(new int[][]{{2,2,1,2,2,2},{1,2,2,2,1,2}}));
        System.out.println(pw.maximumMinimumPath(new int[][]{{3,4,6,3,4},{0,2,1,1,7},{8,8,3,2,7},{3,2,4,9,8},{4,1,2,0,0},{4,6,5,4,3}}));
    }

    int ans;

    public int maximumMinimumPath(int[][] A) {
        ans  = -1;
        if(A==null||A.length==0||A[0].length==0) {
            return ans;
        }
        dfs(A, 0, 0, Integer.MAX_VALUE);
        return ans;
    }

    private void dfs(int[][] A, int i, int j, int currMin) {
        if(i<0||i>=A.length||j<0||j>=A[0].length||A[i][j]==-1) {
            return;
        }
        currMin = Math.min(currMin, A[i][j]);
        if(currMin<=ans) {
            return;
        }
        if(i==A.length-1&&j==A[0].length-1) {
            ans = Math.max(ans, currMin);
            return;
        }
        int tmp = A[i][j];
        A[i][j] = -1;
        dfs(A, i-1, j, currMin);
        dfs(A, i+1, j, currMin);
        dfs(A, i, j-1, currMin);
        dfs(A, i, j+1, currMin);
        A[i][j] = tmp;
    }

}
