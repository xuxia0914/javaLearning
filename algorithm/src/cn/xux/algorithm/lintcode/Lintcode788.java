package cn.xux.algorithm.lintcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 788. 迷宫II
 * 中文English
 * 在迷宫中有一个球，里面有空的空间和墙壁。
 * 球可以通过滚上，下，左或右移动，但它不会停止滚动直到撞到墙上。
 * 当球停止时，它可以选择下一个方向。
 * 给定球的起始位置，目标和迷宫，找到最短距离的球在终点停留。
 * 距离是由球从起始位置(被排除)到目的地(包括)所走过的空空间的数量来定义的。
 * 如果球不能停在目的地，返回-1。
 * 迷宫由二维数组表示。1表示墙和0表示空的空间。
 * 你可以假设迷宫的边界都是墙。
 * 开始和目标坐标用行和列索引表示。
 *
 * 样例
 * Example 1:
 * 	Input:
 * 	(rowStart, colStart) = (0,4)
 * 	(rowDest, colDest)= (4,4)
 * 	0 0 1 0 0
 * 	0 0 0 0 0
 * 	0 0 0 1 0
 * 	1 1 0 1 1
 * 	0 0 0 0 0
 *
 * 	Output:  12
 *
 * 	Explanation:
 * 	(0,4)->(0,3)->(1,3)->(1,2)->(1,1)->(1,0)->(2,0)->(2,1)->(2,2)->(3,2)->(4,2)->(4,3)->(4,4)
 *
 * Example 2:
 * 	Input:
 * 	(rowStart, colStart) = (0,4)
 * 	(rowDest, colDest)= (0,0)
 * 	0 0 1 0 0
 * 	0 0 0 0 0
 * 	0 0 0 1 0
 * 	1 1 0 1 1
 * 	0 0 0 0 0
 *
 * 	Output:  6
 *
 * 	Explanation:
 * 	(0,4)->(0,3)->(1,3)->(1,2)->(1,1)->(1,0)->(0,0)
 *
 * 注意事项
 * 1.在迷宫中只有一个球和一个目的地。
 * 2.球和目的地都存在于一个空的空间中，它们最初不会处于相同的位置。
 * 3.给定的迷宫不包含边框(比如图片中的红色矩形)，但是你可以假设迷宫的边界都是墙。
 * 4.迷宫中至少有2个空的空间，迷宫的宽度和高度都不会超过100。
 */
public class Lintcode788 {

    /**
     * @param maze: the maze
     * @param start: the start
     * @param dest: the destination
     * @return: the shortest distance for the ball to stop at the destination
     */
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        // write your code here
        if(maze==null||maze.length==0||maze[0].length==0) {
            return -1;
        }
        if(start[0]==dest[0]&&start[1]==dest[1]) {
            return 0;
        }
        int m = maze.length;
        int n = maze[0].length;
        int[][] direct = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        Set<Integer> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<=3;i++) {
            queue.offer(new int[]{start[0], start[1], i});
            visited.add((start[0]*m+start[1])*10+i);
        }
        int step = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-->0) {
                int[] curr = queue.poll();
                int x = curr[0]+direct[curr[2]][0];
                int y = curr[1]+direct[curr[2]][1];
                if(x>=0&&x<m&&y>=0&&y<n&&maze[x][y]==0) {
                    int key = (x * n + y) * 10 + curr[2];
                    if (visited.add(key)) {
                        if (x == dest[0] && y == dest[1]) {
                            return step;
                        }
                        queue.offer(new int[]{x, y, curr[2]});
                    }
                }else {
                    for(int i=0;i<=3;i++) {
                        if(i!=curr[2]) {
                            x = curr[0]+direct[i][0];
                            y = curr[1]+direct[i][1];
                            if(x>=0&&x<m&&y>=0&&y<n&&maze[x][y]==0) {
                                int key = (x*n+y)*10+i;
                                if(visited.add(key)) {
                                    if(x==dest[0]&&y==dest[1]) {
                                        return step;
                                    }
                                    queue.offer(new int[]{x, y, i});
                                }
                            }
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

}
