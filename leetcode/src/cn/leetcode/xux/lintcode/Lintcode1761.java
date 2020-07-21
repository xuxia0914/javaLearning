package cn.leetcode.xux.lintcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 1761. 数字华容道
 * 中文English
 * 现在你有一个3×3的矩阵，其中填了1~8和一个空白位置，空白位置用0来表示。
 * 现在，你可以将与填充0数字的格子相邻的格子移动到这个位置，原来的位置即变成空，即原来的位置变成了0。
 * 问，最少移动几次，使得最后的矩阵变成[[1,2,3],[4,5,6],[7,8,0]]。
 * 如果你得到的最小移动次数小于等于k,那么返回true，否则返回false。
 *
 * 样例
 * 样例 1:
 *
 * 输入: arr = [[1, 0, 3], [4, 2, 6], [7, 5, 8]], k = 3
 * 输出:"true"
 * 103       123      123     123
 * 426   ->  406   -> 456  -> 456
 * 758       758      708     780
 * 所以，需要3步。
 * 3<=3，所以返回true。
 * 样例 2:
 *
 * 输入: arr = [[4, 2, 8], [5, 0, 3], [6, 7, 1]], k = 24
 * 输出: "true"
 * 解释:
 * 428       428       428       428       420       402       042       542       542
 * 503   ->  573   ->  573   ->  570   ->  578   ->  578   ->  578   ->  078   ->  708   ->
 * 671       601       610       613       613       613       613       613       613
 *
 * 542       542       542       542       502       052       152       152       152
 * 718   ->  718   ->  018   ->  108   ->  148   ->  148   ->  048   ->  408   ->  480   ->
 * 603       063       763       763       763       763       763       763       763
 *
 * 152       152       152       102       120       123       123
 * 483   ->  483   ->  403   ->  453   ->  453   ->  450   ->  456
 * 760       706       786       786       786       786       780
 * 24 <= 24，所以返回true。
 * 注意事项
 * 保证给定输入一定有解
 */
public class Lintcode1761 {

    /**
     * @param arr: the initial state
     * @param k: the limit
     * @return: the steps
     */
    public String digitalHuarongRoad(int[][] arr, int k) {
        // Write your code here.
        if(k<0) {
            return "false";
        }
        Queue<int[]> queue = new LinkedList<>();
        int curr = stateToInt(arr);
        int target = 123456780;
        if(curr==target) {
            return "true";
        }
        Set<Integer> visited = new HashSet<>();
        for(int i=0;i<9;i++) {
            if(arr[i/3][i%3]==0) {
                queue.offer(new int[]{curr, i});
                visited.add(curr);
                break;
            }
        }
        int[] steps = new int[]{-3,-1,1,3};
        while(!queue.isEmpty()&&k-->0) {
            int size = queue.size();
            while(size-->0) {
                int[] pre = queue.poll();
                int state = pre[0];
                int idx = pre[1];
                for(int step : steps) {
                    if((step==-1&&idx%3!=0)||(step==1&&idx%3!=2)
                            ||(step==-3&&idx>2)||(step==3&&idx<6)) {
                        int nextIdx = idx+step;
                        int bit = (int)Math.pow(10, 8-nextIdx);
                        int digit = state/bit%10;
                        int nextState = state-digit*bit+digit*(int)Math.pow(10, 8-idx);
                        if(nextState==target) {
                            return "true";
                        }
                        if(visited.add(nextState)) {
                            queue.offer(new int[]{nextState, nextIdx});
                        }
                    }
                }
            }
        }
        return "false";
    }

    private int stateToInt(int[][] arr) {
        int ans = 0;
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                ans = ans*10+arr[i][j];
            }
        }
        return ans;
    }

}
