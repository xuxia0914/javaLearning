package cn.xux.algorithm.lintcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 794. 滑动拼图 II
 * 中文English
 * 在一个3x3的网格中，放着编号1到8的8块板，以及一块编号为0的空格。
 * 一次移动可以把空格0与上下左右四邻接之一的板子交换。
 * 给定初始和目标的板子排布，返回到目标排布最少的移动次数。
 * 如果不能从初始排布移动到目标排布，返回-1.
 *
 * 样例
 * 样例1 ：
 *
 * 输入:
 * [
 *  [2,8,3],
 *  [1,0,4],
 *  [7,6,5]
 * ]
 * [
 *  [1,2,3],
 *  [8,0,4],
 *  [7,6,5]
 * ]
 * 输出:
 * 4
 *
 * 解释:
 * [                 [
 *  [2,8,3],          [2,0,3],
 *  [1,0,4],   -->    [1,8,4],
 *  [7,6,5]           [7,6,5]
 * ]                 ]
 *
 * [                 [
 *  [2,0,3],          [0,2,3],
 *  [1,8,4],   -->    [1,8,4],
 *  [7,6,5]           [7,6,5]
 * ]                 ]
 *
 * [                 [
 *  [0,2,3],          [1,2,3],
 *  [1,8,4],   -->    [0,8,4],
 *  [7,6,5]           [7,6,5]
 * ]                 ]
 *
 * [                 [
 *  [1,2,3],          [1,2,3],
 *  [0,8,4],   -->    [8,0,4],
 *  [7,6,5]           [7,6,5]
 * ]                 ]
 * 样例 2：
 *
 * 输入:
 * [[2,3,8],[7,0,5],[1,6,4]]
 * [[1,2,3],[8,0,4],[7,6,5]]
 * 输出:
 * -1
 * 挑战
 * 如何优化内存？
 * 能用A*算法求解吗？
 */
public class Lintcode794 {

    /**
     * @param init_state: the initial state of chessboard
     * @param final_state: the final state of chessboard
     * @return: return an integer, denote the number of minimum moving
     */
    public int minMoveStep(int[][] init_state, int[][] final_state) {
        // # write your code here
        int i=0;
        for(;i<9;i++) {
            if(init_state[i/3][i%3]==0) {
                break;
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        int init = stateToInt(init_state);
        queue.offer(new int[]{init, i/3, i%3});
        int target = stateToInt(final_state);
        int level = 1;
        Set<Integer> set = new HashSet<>();
        set.add(init);
        int[][] steps = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-->0) {
                int[] curr = queue.poll();
                for(int[] step : steps) {
                    int currBit = curr[1]*3+curr[2];
                    int[] next = new int[]{curr[1]+step[0], curr[2]+step[1]};
                    if(next[0]>=0&next[0]<3&&next[1]>=0&&next[1]<3) {
                        int nextBit = next[0]*3+next[1];
                        int tmp = (int)Math.pow(10, 8-nextBit);
                        int digit = curr[0]/tmp%10;
                        int next0 = curr[0]-digit*tmp+digit*(int)Math.pow(10, 8-currBit);
                        if(next0==target) {
                            return level;
                        }
                        if(set.add(next0)) {
                            queue.offer(new int[]{next0, next[0], next[1]});
                        }
                    }
                }
            }
            level++;
        }
        return -1;
    }

    private int stateToInt(int[][] state) {
        int ans = 0;
        for(int[] arr : state) {
            for(int i : arr) {
                ans = ans*10+i;
            }
        }
        return ans;
    }

}
