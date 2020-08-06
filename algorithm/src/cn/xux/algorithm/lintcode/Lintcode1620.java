package cn.xux.algorithm.lintcode;

import java.util.*;

/**
 * 1620. 收集硬币
 * 中文English
 * 给定一个n * m个的矩阵，矩阵的每个位置有一定数量的硬币，
 * 你从（0,0）位置出发，每次只能往右或者往下走，当他经过某个格子的时候，
 * 可以得到这个格子上的所有硬币，当它走到第（n-1，M-1）位置时游戏结束，
 * 在游戏开始前，你有ķ次机会，可以交换某两个格子中的硬币数量中，k次机会不一定要使用完，
 * 求从（0,0）走到第（n-1，M-1）所能得到的最大的硬币数量。
 *
 * 样例
 * 输入:
 * matrix = [[9,9,9,0,0],[0,0,9,0,0],[0,0,0,0,0],[0,0,9,0,0],[9,0,9,9,9]]
 * k = 1
 * 输出:81
 * 注意事项
 * 2≤n,m≤50
 * 0<=k<=20
 * 0<=matrix[I][j]<=1000000
 */
public class Lintcode1620 {

    /**
     * @param matrix: a matrix
     * @param k: an integer
     * @return: the maximum coins L can get
     */
    public int collectingCoins(int[][] matrix, int k) {
        // Write your code here
        if(matrix==null||matrix.length==0
                ||matrix[0].length==0||k<0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        Node[] dp = new Node[n];
        for(int j=0;j<n;j++) {
            dp[j] = new Node(0, new ArrayList<>());
        }
        for(int i=0;i<m;i++) {
            Node[] newDp = new Node[n];
            for(int j=0;j<n;j++) {
                newDp[j] = dp[j];
                if(j>0&&newDp[j-1].value>newDp[j].value) {
                    newDp[j] = new Node(newDp[j-1].value, new ArrayList<>(newDp[j-1].list));
                }
                newDp[j].value += matrix[i][j];
                newDp[j].list.add(matrix[i][j]);
            }
            dp = newDp;
        }
        int ans = dp[n-1].value;
        if(k>0) {
            PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o->-o));
            for(int[] arr : matrix) {
                for(int num : arr) {
                    queue.offer(num);
                }
            }
            List<Integer> nums = dp[n-1].list;
            for(int num : nums) {
                queue.remove(num);
            }
            Collections.sort(nums);
            int idx = 0;
            while(k-->0&&!queue.isEmpty()&&idx<nums.size()&&queue.peek()>nums.get(idx)) {
                ans += queue.poll()-nums.get(idx++);
            }
        }
        return ans;
    }

    class Node {
        List<Integer> list;
        int value;

        Node(int value, List<Integer> list) {
            this.list = list;
            this.value = value;
        }
    }

}
