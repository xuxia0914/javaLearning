package cn.leetcode.xux.midium;

import java.util.HashSet;
import java.util.Set;

/**
 * 迷宫
 * There is a ball in a maze with empty spaces and walls.
 * The ball can go through empty spaces by rolling up, down, left or right,
 * but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 * Given the ball's start position, the destination and the maze,
 * determine whether the ball could stop at the destination.
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space.
 * You may assume that the borders of the maze are all walls.
 * The start and destination coordinates are represented by row and column indexes.
 *
 * Example 1
 * Input 1: a maze represented by a 2D array
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
 * Output: true
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 *
 * Example 2
 * Input 1: a maze represented by a 2D array
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (3, 2)
 * Output: false
 * Explanation: There is no way for the ball to stop at the destination.
 *
 * Note:
 * There is only one ball and one destination in the maze.
 * Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 * The given maze does not contain border (like the red rectangle in the example pictures),
 * but you could assume the border of the maze are all walls.
 * The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */
public class TheMaze {

    Set<Integer> set = new HashSet<>();

    public boolean hasPath(int[][] maze, int[] start, int[] des) {
            if(maze==null||maze.length==0||maze[0].length==0) {
                return false;
            }
            if(start[0]==des[0]&&start[1]==des[1]) {
                return true;
            }
            int m = maze.length;
            int n= maze[0].length;

            //起点四个方向都不通
            if((start[0]==0||maze[start[0]-1][start[1]]==1)
                    &&(start[0]==m-1||maze[start[0]+1][start[1]]==1)
                    &&(start[1]==0||maze[start[0]][start[1]-1]==1)
                    &&(start[1]==n-1||maze[start[0]][start[1]+1]==1)) {
                return false;
            }
            //终点的上下都不通或相通 左右都不通或相通
            if((((des[0]==0||maze[des[0]-1][des[1]]==1)&&(des[0]==n-1||maze[des[0]+1][des[1]]==1))
                    ||(des[0]>0&&maze[des[0]-1][des[1]]==0&&des[0]<n-1&&maze[des[0]+1][des[1]]==0))
                &&(((des[1]==0||maze[des[0]][des[1]-1]==1)&&(des[1]==n-1||maze[des[0]][des[1]+1]==1))
                    ||(des[1]>0&&maze[des[0]][des[1]-1]==0&&des[1]<n-1&&maze[des[0]][des[1]+1]==0))) {
                return false;
            }


            return helper(maze, maze.length, maze[0].length, start, des);
    }

    boolean helper(int[][] maze, int m, int n, int[] start, int[] des) {

        if(start[0]>0&&maze[start[0]-1][start[1]]!=1&&!set.contains((start[0]*m+start[1])*10)) {    //roll up
            set.add((start[0]*m+start[1])*10);  //记录当前出发位置和方向
            int i = start[0];
            while(i>0&&maze[i-1][start[1]]!=1) {
                i--;
            }
            if(start[1]==des[1]&&i==des[0]) {
                return true;
            }
            set.add((i*m+start[1])*10+1);   //不用往回滚
            if(helper(maze, m, n, new int[]{i, start[1]}, des)) {
                return true;
            }
        }

        if(start[0]<m-1&&maze[start[0]+1][start[1]]!=1&&!set.contains((start[0]*m+start[1])*10+1)) {    //roll down
            set.add((start[0]*m+start[1])*10+1);
            int i = start[0];
            while(i<m-1&&maze[i+1][start[1]]!=1) {
                i++;
            }
            if(start[1]==des[1]&&i==des[0]) {
                return true;
            }
            set.add((i*m+start[1])*10);
            if(helper(maze, m, n, new int[]{i, start[1]}, des)) {
                return true;
            }
        }

        if(start[1]>0&&maze[start[0]][start[1]-1]!=1&&!set.contains((start[0]*m+start[1])*10+2)) {    //roll left
            set.add((start[0]*m+start[1])*10+2);
            int i = start[1];
            while(i>0&&maze[start[0]][i-1]!=1) {
                i--;
            }
            if(start[0]==des[0]&&i==des[1]) {
                return true;
            }
            set.add((start[0]*m+i)*10+3);
            if(helper(maze, m, n, new int[]{start[0], i}, des)) {
                return true;
            }
        }

        if(start[1]<n-1&&maze[start[0]][start[1]+1]!=1&&!set.contains((start[0]*m+start[1])*10+3)) {    //roll right
            set.add((start[0]*m+start[1])*10+3);
            int i = start[1];
            while(i<n-1&&maze[start[0]][i+1]!=1) {
                i++;
            }
            if(start[0]==des[0]&&i==des[1]) {
                return true;
            }
            set.add((start[0]*m+i)*10+2);
            if(helper(maze, m, n, new int[]{start[0], i}, des)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        /*System.out.println(new TheMaze().hasPath(
                new int[][]{{0, 0, 1, 0, 0},{0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}},
                new int[]{0, 4},
                new int[]{4, 4}
        )); //true*/
        System.out.println(new TheMaze().hasPath(
                new int[][]{{0, 0, 1, 0, 0},{0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}},
                new int[]{0, 4},
                new int[]{3, 2}
        )); //false
    }

}
