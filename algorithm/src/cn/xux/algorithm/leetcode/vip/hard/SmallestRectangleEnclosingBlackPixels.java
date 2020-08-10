package cn.xux.algorithm.leetcode.vip.hard;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 302. 包含全部黑色像素的最小矩形
 * 图片在计算机处理中往往是使用二维矩阵来表示的。
 * 假设，这里我们用的是一张黑白的图片，那么 0 代表白色像素，1 代表黑色像素。
 * 其中黑色的像素他们相互连接，也就是说，图片中只会有一片连在一块儿的黑色像素（像素点是水平或竖直方向连接的）。
 * 那么，给出某一个黑色像素点 (x, y) 的位置，你是否可以找出包含全部黑色像素的最小矩形（与坐标轴对齐）的面积呢？
 *
 * 示例:
 * 输入:
 * [
 *   "0010",
 *   "0110",
 *   "0100"
 * ]
 * 和 x = 0,
 * y = 2
 * 输出: 6
 */
public class SmallestRectangleEnclosingBlackPixels {

    public int minArea(char[][] image, int x, int y) {
        if(image==null||image.length==0||image[0].length==0) {
            return 0;
        }
        int m = image.length;
        int n = image[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        int up = x;
        int bottom = x;
        int left = y;
        int right = y;
        int[][] steps = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            for(int[] step : steps) {
                int nx = curr[0]+step[0];
                int ny = curr[1]+step[1];
                if(nx>=0&&nx<m&&ny>=0&&ny<n&&image[nx][ny]==1) {
                    queue.offer(new int[]{nx, ny});
                    image[nx][ny] = 0;
                    up = Math.min(up, nx);
                    bottom = Math.max(bottom, nx);
                    left = Math.min(left, ny);
                    right = Math.max(right, ny);
                }
            }
        }
        return (bottom-up+1)*(right-left+1);
    }

}
