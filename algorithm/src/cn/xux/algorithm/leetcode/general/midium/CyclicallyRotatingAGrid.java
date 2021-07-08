package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1914. 循环轮转矩阵
 * 给你一个大小为 m x n 的整数矩阵 grid​​​ ，
 * 其中 m 和 n 都是 偶数 ；另给你一个整数 k 。
 * 矩阵由若干层组成，如下图所示，每种颜色代表一层：
 * 矩阵的循环轮转是通过分别循环轮转矩阵中的每一层完成的。
 * 在对某一层进行一次循环旋转操作时，
 * 层中的每一个元素将会取代其 逆时针 方向的相邻元素。轮转示例如下：
 * 返回执行 k 次循环轮转操作后的矩阵。
 *
 * 示例 1：
 * 输入：grid = [[40,10],[30,20]], k = 1
 * 输出：[[10,20],[40,30]]
 * 解释：上图展示了矩阵在执行循环轮转操作时每一步的状态。
 *
 * 示例 2：
 * 输入：grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], k = 2
 * 输出：[[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]]
 * 解释：上图展示了矩阵在执行循环轮转操作时每一步的状态。
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 50
 * m 和 n 都是 偶数
 * 1 <= grid[i][j] <= 5000
 * 1 <= k <= 109
 */
public class CyclicallyRotatingAGrid {

    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        // 层数遍历，从外到内
        for(int i=0;i<Math.min(m,n)/2;i++) {
            // 当前层的元素个数
            int len = 2*(m-i*2)+2*(n-i*2)-4;
            // 按从左到右从上到下遍历所有元素写入一维数组
            int[] data = new int[len];
            int idx = 0;
            for(int j=i;j<n-1-i;j++) {
                data[idx++] = grid[i][j];
            }
            for(int j=i;j<m-1-i;j++) {
                data[idx++] = grid[j][n-i-1];
            }
            for(int j=n-1-i;j>i;j--) {
                data[idx++] = grid[m-1-i][j];
            }
            for(int j=m-1-i;j>i;j--) {
                data[idx++] = grid[j][i];
            }
            // 旋转k次后的一维数组
            int[] tmp = new int[len];
            int x = k%len;
            for(int j=0;j<len;j++) {
                tmp[j] = data[(x+j)%len];
            }
            // 将旋转后的数组回写到grid中
            idx = 0;
            for(int j=i;j<n-1-i;j++) {
                grid[i][j] = tmp[idx++];
            }
            for(int j=i;j<m-1-i;j++) {
                grid[j][n-i-1] = tmp[idx++];
            }
            for(int j=n-1-i;j>i;j--) {
                grid[m-1-i][j] = tmp[idx++];
            }
            for(int j=m-1-i;j>i;j--) {
                grid[j][i] = tmp[idx++];
            }
        }
        return grid;
    }

}
