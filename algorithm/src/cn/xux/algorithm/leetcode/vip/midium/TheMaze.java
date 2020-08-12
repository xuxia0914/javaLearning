package cn.xux.algorithm.leetcode.vip.midium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 490 迷宫
 * 在迷宫中有一个球，里面有空的空间和墙壁。球可以通过滚上，下，左或右移动，
 * 但它不会停止滚动直到撞到墙上。当球停止时，它可以选择下一个方向。
 * 给定球的起始位置，目的地和迷宫，确定球是否可以停在终点。
 * 迷宫由二维数组表示。1表示墙和0表示空的空间。
 * 你可以假设迷宫的边界都是墙。开始和目标坐标用行和列索引表示。
 *
 * 例1:
 * 输入:map =
 * [
 *  [0,0,1,0,0],
 *  [0,0,0,0,0],
 *  [0,0,0,1,0],
 *  [1,1,0,1,1],
 *  [0,0,0,0,0]
 * ]
 * start = [0,4]
 * end = [3,2]
 * 输出: false
 *
 * 例2:
 * 输入: map =
 * [[0,0,1,0,0],
 *  [0,0,0,0,0],
 *  [0,0,0,1,0],
 *  [1,1,0,1,1],
 *  [0,0,0,0,0]
 * ]
 * start = [0,4]
 * end = [4,4]
 * 输出: true
 *
 * 注意事项
 * 1.在迷宫中只有一个球和一个目的地。
 * 2.球和目的地都存在于一个空的空间中，它们最初不会处于相同的位置。
 * 3.给定的迷宫不包含边框(比如图片中的红色矩形)，但是你可以假设迷宫的边界都是墙。
 * 5.迷宫中至少有2个空的空间，迷宫的宽度和高度都不会超过100。
 */
public class TheMaze {

    public static void main(String[] args) {
        /*System.out.println(new TheMaze().hasPath(
                new int[][]{{0, 0, 1, 0, 0},{0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}},
                new int[]{0, 4},
                new int[]{4, 4}
        )); //true*/
        /*System.out.println(new TheMaze().hasPath(
                new int[][]{{0, 0, 1, 0, 0},{0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}},
                new int[]{0, 4},
                new int[]{3, 2}
        )); //false*/
    }

    public boolean hasPath(int[][] maze, int[] start, int[] des) {
        // 一些边界情况的判断
        if(maze==null||maze.length==0||maze[0].length==0) {
            return false;
        }
        int m = maze.length;
        int n = maze[0].length;
        if(start[0]<0||start[0]>=m||maze[start[0]][start[1]]==1
                ||des[0]<0||des[0]>=m||maze[des[0]][des[1]]==1) {
            return false;
        }
        if(start[0]==des[0]&&start[1]==des[1]) {
            return true;
        }

        // 向四个方向(上下左右)移动的坐标变化
        int[] dx = new int[]{-1,1,0,0};
        int[] dy = new int[]{0,0,-1,1};

        // queue内的元素arr：arr[0]当前位置的x坐标， arr[1]当前位置的y坐标， arr[2]当前方向
        // 初始化，小球可以从起始位置向四个方向移动
        Queue<int[]> queue = new LinkedList<>();
        // 记录已经经历过的状态(位置+方向)，避免重复计算
        Set<Integer> visited = new HashSet<>();
        queue.offer(new int[]{start[0], start[1], 0});
        visited.add((start[0]*101+start[1])*101);
        queue.offer(new int[]{start[0], start[1], 1});
        visited.add(((start[0]*101+start[1])*101)+1);
        queue.offer(new int[]{start[0], start[1], 2});
        visited.add(((start[0]*101+start[1])*101)+2);
        queue.offer(new int[]{start[0], start[1], 3});
        visited.add(((start[0]*101+start[1])*101)+3);

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            // 下一个状态(位置+方向)
            int[] next = new int[]{curr[0]+dx[curr[2]], curr[1]+dy[curr[2]], curr[2]};
            if(next[0]>=0&&next[0]<m&&next[1]>=0&&next[1]<n
                    &&maze[next[0]][next[1]]==0) {
                // 下一个状态是可达的，则加入队列
                int key = ((next[0]*101+next[1])*101)+next[2];
                if(visited.add(key)) {
                    queue.offer(next);
                }
            }else {
                // 下一个状态不可达，即因出界或者撞墙停住
                // 此时如果停在终点则返回true;否则选择其他方向
                if(curr[0]==des[0]&&curr[1]==des[1]) {
                    return true;
                }
                for(int i=0;i<4;i++) {
                    int key = ((curr[0]*101+curr[1])*101)+i;
                    if(visited.add(key)) {
                        queue.offer(new int[]{curr[0], curr[1], i});
                    }
                }
            }
        }
        return false;
    }

}
