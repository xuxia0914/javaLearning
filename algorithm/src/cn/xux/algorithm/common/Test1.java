package cn.xux.algorithm.common;

import java.util.*;
import java.util.List;

public class Test1 {

    public static void main(String[] args) {
//        System.out.println(new Test1().getAns(new int[]{8,9,7,3,0,5,11}));
    }

    /**
     * 目的地的最短路径
     * 给定表示地图上坐标的2D数组，地图上只有值0,1,2.
     * 0表示可以通过，1表示不可通过，2表示目标位置。
     * 从坐标[0,0]开始，你只能上，下，左，右移动。找到可以到达目的地的最短路径，并返回路径的长度。
     *
     * 样例1
     * 输入:
     * [
     *  [0, 0, 0],
     *  [0, 0, 1],
     *  [0, 0, 2]
     * ]
     * 输出: 4
     * 说明: [0,0] -> [1,0] -> [2,0] -> [2,1] -> [2,2]
     *
     * 样例2
     * 输入:
     * [
     *     [0,1],
     *     [0,1],
     *     [0,0],
     *     [0,2]
     * ]
     * 输出: 4
     * 说明: [0,0] -> [1,0] -> [2,0] -> [3,0] -> [3,1]
     *
     * 注意事项
     * 地图一定存在且不为空，并且只存在一个目的地
     * 保证targetMap[0][0] = 0
     */
    public int shortestPath(int[][] targetMap) {
        // Write your code here
        ans = Integer.MAX_VALUE;
        dfs(targetMap, 0, 0, -1);
        return ans==Integer.MAX_VALUE?-1:ans;
    }

    int ans;

    private void dfs(int[][] map, int i, int j, int curr) {
        if(i<0||i>=map.length||j<0||j>=map[0].length
                ||map[i][j]==1||++curr >=ans) {
            return;
        }
        if(map[i][j]==2) {
            ans = curr;
            return;
        }
        map[i][j] = 1;
        dfs(map,i-1,j,curr);
        dfs(map,i+1,j,curr);
        dfs(map,i,j-1,curr);
        dfs(map,i,j+1,curr);
        map[i][j] = 0;
    }

}