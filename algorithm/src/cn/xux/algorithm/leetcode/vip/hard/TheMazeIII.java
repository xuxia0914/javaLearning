package cn.xux.algorithm.leetcode.vip.hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 499. 迷宫 III
 * 由空地和墙组成的迷宫中有一个球。球可以向上（u）下（d）左（l）右（r）四个方向滚动，
 * 但在遇到墙壁前不会停止滚动。当球停下时，可以选择下一个方向。
 * 迷宫中还有一个洞，当球运动经过洞时，就会掉进洞里。
 * 给定球的起始位置，目的地和迷宫，找出让球以最短距离掉进洞里的路径。
 * 距离的定义是球从起始位置（不包括）到目的地（包括）经过的空地个数。
 * 通过'u', 'd', 'l' 和 'r'输出球的移动方向。 由于可能有多条最短路径，
 * 请输出字典序最小的路径。如果球无法进入洞，输出"impossible"。
 * 迷宫由一个0和1的二维数组表示。 1表示墙壁，0表示空地。
 * 你可以假定迷宫的边缘都是墙壁。起始位置和目的地的坐标通过行号和列号给出。
 *
 * 示例1:
 * 输入 1: 迷宫由以下二维数组表示
 * 0 0 0 0 0
 * 1 1 0 0 1
 * 0 0 0 0 0
 * 0 1 0 0 1
 * 0 1 0 0 0
 * 输入 2: 球的初始位置 (rowBall, colBall) = (4, 3)
 * 输入 3: 洞的位置 (rowHole, colHole) = (0, 1)
 * 输出: "lul"
 * 解析: 有两条让球进洞的最短路径。
 * 第一条路径是 左 -> 上 -> 左, 记为 "lul".
 * 第二条路径是 上 -> 左, 记为 'ul'.
 * 两条路径都具有最短距离6, 但'l' < 'u'，故第一条路径字典序更小。因此输出"lul"。
 *
 * 示例 2:
 * 输入 1: 迷宫由以下二维数组表示
 * 0 0 0 0 0
 * 1 1 0 0 1
 * 0 0 0 0 0
 * 0 1 0 0 1
 * 0 1 0 0 0
 * 输入 2: 球的初始位置 (rowBall, colBall) = (4, 3)
 * 输入 3: 洞的位置 (rowHole, colHole) = (3, 0)
 * 输出: "impossible"
 *
 * 注意:
 * 迷宫中只有一个球和一个目的地。
 * 球和洞都在空地上，且初始时它们不在同一位置。
 * 给定的迷宫不包括边界 (如图中的红色矩形), 但你可以假设迷宫的边缘都是墙壁。
 * 迷宫至少包括2块空地，行数和列数均不超过30。
 */
public class TheMazeIII {

    public static void main(String[] args) {
        System.out.println(new TheMazeIII().findShortestWay(
                new int[][]{{0,0,0,0,0},
                            {1,1,0,0,1},
                            {0,0,0,0,0},
                            {0,1,0,0,1},
                            {0,1,0,0,0}},
                new int[]{4,3},
                new int[]{0,1} ));
    }

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        // 一些边界情况的判断
        if(maze==null||maze.length==0||maze[0].length==0) {
            return "impossible";
        }
        int m = maze.length;
        int n = maze[0].length;
        if(ball[0]<0||ball[0]>=m||maze[ball[0]][ball[1]]==1
                ||hole[0]<0||hole[0]>=m||maze[hole[0]][hole[1]]==1) {
            return "impossible";
        }
        if(ball[0]==hole[0]&&ball[1]==hole[1]) {
            return "";
        }

        // 向四个方向(上下左右)移动的坐标变化, 按字典排序(这样后面广搜是=时遇到hole就可以直接返回结果)
        char[] d = new char[]{'d','l','r','u'};
        int[] dx = new int[]{1,0,0,-1};
        int[] dy = new int[]{0,-1,1,0};

        // queue内的元素pair:
        //      key[0]当前位置的x坐标， key[1]当前位置的y坐标， key[2]当前方向,
        //      value表示当前路径(表示方向变化)
        // 初始化，小球可以从起始位置向四个方向移动
        Queue<Pair> queue = new LinkedList<>();
        // 记录已经经历过的状态(位置+方向)，避免重复计算
        Set<Integer> visited = new HashSet<>();
        for(int i=0;i<4;i++) {
            int[] next = new int[]{ball[0]+dx[i], ball[1]+dy[i], i};
            if(next[0]>=0&&next[0]<m&&next[1]>=0&&next[1]<n
                    &&maze[next[0]][next[1]]==0) {
                // 下一个状态是可达的，如果是终点则更新终点
                String nw = ""+d[i];
                if(next[0]==hole[0]&&next[1]==hole[1]) {
                    return nw;
                }
                int key = ((next[0]*31+next[1])*31)+next[2];
                if(visited.add(key)) {
                    queue.offer(new Pair(next, nw));
                }
            }
        }
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-->0) {
                Pair pair = queue.poll();
                int[] curr = pair.key;
                // 下一个状态(位置+方向)
                int[] next = new int[]{curr[0]+dx[curr[2]], curr[1]+dy[curr[2]], curr[2]};
                if(next[0]>=0&&next[0]<m&&next[1]>=0&&next[1]<n
                        &&maze[next[0]][next[1]]==0) {
                    // 下一个状态是可达的，如果是终点返回当前路径，否则入队
                    if(next[0]==hole[0]&&next[1]==hole[1]) {
                        return pair.value;
                    }
                    int key = ((next[0]*31+next[1])*31)+next[2];
                    if(visited.add(key)) {
                        queue.offer(new Pair(next, pair.value));
                    }
                }else {
                    // 下一个状态不可达，即因出界或者撞墙停住
                    // 此时如果停在终点则返回true;否则选择其他方向
                    for(int i=0;i<4;i++) if(i!=curr[2]) {
                        next = new int[]{curr[0]+dx[i], curr[1]+dy[i], i};
                        if(next[0]>=0&&next[0]<m&&next[1]>=0&&next[1]<n
                                &&maze[next[0]][next[1]]==0) {
                            // 下一个状态是可达的，如果是终点则返回当前路径，否则入队
                            String nw = pair.value+d[i];
                            if(next[0]==hole[0]&&next[1]==hole[1]) {
                                return nw;
                            }
                            int key = ((next[0]*31+next[1])*31)+next[2];
                            if(visited.add(key)) {
                                queue.offer(new Pair(next, nw));
                            }
                        }
                    }
                }
            }
        }
        return "impossible";
    }

    class Pair {
        int[] key;
        String value;

        Pair(int[] key, String value) {
            this.key = key;
            this.value = value;
        }
    }

}
