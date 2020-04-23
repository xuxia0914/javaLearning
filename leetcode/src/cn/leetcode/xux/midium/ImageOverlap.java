package cn.leetcode.xux.midium;

import java.util.HashSet;
import java.util.Set;

/**
 * 835. 图像重叠
 * 给出两个图像 A 和 B ，A 和 B 为大小相同的二维正方形矩阵。（并且为二进制矩阵，只包含0和1）。
 * 我们转换其中一个图像，向左，右，上，或下滑动任何数量的单位，并把它放在另一个图像的上面。
 * 之后，该转换的重叠是指两个图像都具有 1 的位置的数目。
 * （请注意，转换不包括向任何方向旋转。）
 * 最大可能的重叠是什么？
 *
 * 示例 1:
 * 输入：A = [[1,1,0],
 *           [0,1,0],
 *           [0,1,0]]
 *      B = [[0,0,0],
 *           [0,1,1],
 *           [0,0,1]]
 * 输出：3
 * 解释: 将 A 向右移动一个单位，然后向下移动一个单位。
 *
 * 注意:
 * 1 <= A.length = A[0].length = B.length = B[0].length <= 30
 * 0 <= A[i][j], B[i][j] <= 1
 */
public class ImageOverlap {

    public static void main(String[] args) {
        //[[0,0,0,0,0],[0,1,0,0,0],[0,0,1,0,0],[0,0,0,0,1],[0,1,0,0,1]]
        //[[1,0,1,1,1],[1,1,1,1,1],[1,1,1,1,1],[0,1,1,1,1],[1,0,1,1,1]]
        System.out.println(new ImageOverlap().largestOverlap(
                new int[][]{{0,0,0,0,0},{0,1,0,0,0},{0,0,1,0,0},{0,0,0,0,1},{0,1,0,0,1}},
                new int[][]{{1,0,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{0,1,1,1,1},{1,0,1,1,1}}
        ));
    }

    int result = 0;

    public int largestOverlap(int[][] A, int[][] B) {
        if(A==null||A.length==0||B==null||B.length==0||A.length!=B.length) {
            return 0;
        }
        result = 0;
        dfs(A, B, A.length, 0, 0);
        return result;
    }

    Set<String> set = new HashSet<>();

    public void dfs(int[][] A, int[][] B, int n, int x, int y) {
        String key = x+"+"+y;
        if(x<=-n||x>=n||y<=-n||y>=n||!set.add(key)) {
            return;
        }
        int currResult = 0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(i+x>=0&&i+x<n&&j+y>=0&&j+y<n) {
                    currResult += A[i][j]*B[i+x][j+y];
                }
            }
        }
        result = Math.max(result, currResult);
        dfs(A, B, n, x-1, y);
        dfs(A, B, n, x+1, y);
        dfs(A, B, n, x, y-1);
        dfs(A, B, n, x, y+1);
    }

}
