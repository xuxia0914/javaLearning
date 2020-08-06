package cn.xux.algorithm.leetcode.general.midium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 934. 最短的桥
 * 在给定的二维二进制数组 A 中，存在两座岛。（岛是由四面相连的 1 形成的一个最大组。）
 * 现在，我们可以将 0 变为 1，以使两座岛连接起来，变成一座岛。
 * 返回必须翻转的 0 的最小数目。（可以保证答案至少是 1。）
 *
 * 示例 1：
 * 输入：[[0,1],[1,0]]
 * 输出：1
 *
 * 示例 2：
 * 输入：[[0,1,0],[0,0,0],[0,0,1]]
 * 输出：2
 *
 * 示例 3：
 * 输入：[[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * 输出：1
 *
 * 提示：
 * 1 <= A.length = A[0].length <= 100
 * A[i][j] == 0 或 A[i][j] == 1
 */
public class ShortestBridge {

    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited;

    public int shortestBridge(int[][] A) {
        if(A==null||A.length==0||A[0].length==0) {
            return 0;
        }
        int m = A.length;
        int n = A[0].length;
        visited = new boolean[m][n];
        boolean flag = false;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(A[i][j]==1) {
                    helper(A, i, j);
                    flag = true;
                    break;
                }
            }
            if(flag) {
                break;
            }
        }
        int[][] dists = new int[m][n];
        int dist = 0;
        //广度优先搜索记录所有坐标距离第一个岛的最小距离
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                int[] curr = queue.poll();
                dists[curr[0]][curr[1]] = dist;
                if(curr[0]>0&&!visited[curr[0]-1][curr[1]]) {
                    queue.offer(new int[]{curr[0]-1, curr[1]});
                    visited[curr[0]-1][curr[1]] = true;
                }
                if(curr[0]<m-1&&!visited[curr[0]+1][curr[1]]) {
                    queue.offer(new int[]{curr[0]+1, curr[1]});
                    visited[curr[0]+1][curr[1]] = true;
                }
                if(curr[1]>0&&!visited[curr[0]][curr[1]-1]) {
                    queue.offer(new int[]{curr[0], curr[1]-1});
                    visited[curr[0]][curr[1]-1] = true;
                }
                if(curr[1]<n-1&&!visited[curr[0]][curr[1]+1]) {
                    queue.offer(new int[]{curr[0], curr[1]+1});
                    visited[curr[0]][curr[1]+1] = true;
                }
            }
            dist++;
        }
        int res = Integer.MAX_VALUE;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(A[i][j]==1) {
                    res = Math.min(res, dists[i][j]-1);
                }
            }
        }
        return res;
    }

    //把第一个岛的值都设置为-1并加入queue
    public void helper(int[][] A, int i, int j) {
        queue.offer(new int[]{i, j});
        visited[i][j] = true;
        A[i][j] = -1;
        if(i>0&&A[i-1][j]==1&&!visited[i-1][j]) {
            helper(A, i-1, j);
        }
        if(i<A.length-1&&A[i+1][j]==1&&!visited[i+1][j]) {
            helper(A, i+1, j);
        }
        if(j>0&&A[i][j-1]==1&&!visited[i][j-1]) {
            helper(A, i, j-1);
        }
        if(j<A[0].length-1&&A[i][j+1]==1&&!visited[i][j+1]) {
            helper(A, i, j+1);
        }
    }

    public static void main(String[] args) {
        ShortestBridge sb = new ShortestBridge();
        System.out.println(sb.shortestBridge(new int[][]{{0,1}, {1,0}}));
        System.out.println(sb.shortestBridge(new int[][]{{0,1,0}, {0,0,0}, {0,0,1}}));
    }

}
