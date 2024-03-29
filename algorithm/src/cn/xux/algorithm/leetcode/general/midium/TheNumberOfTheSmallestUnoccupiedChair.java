package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1942. 最小未被占据椅子的编号
 * 有 n 个朋友在举办一个派对，这些朋友从 0 到 n - 1 编号。
 * 派对里有 无数 张椅子，编号为 0 到 infinity 。
 * 当一个朋友到达派对时，他会占据 编号最小 且未被占据的椅子。
 *
 * 比方说，当一个朋友到达时，如果椅子 0 ，1 和 5 被占据了，那么他会占据 2 号椅子。
 * 当一个朋友离开派对时，他的椅子会立刻变成未占据状态。
 * 如果同一时刻有另一个朋友到达，可以立即占据这张椅子。
 *
 * 给你一个下标从 0 开始的二维整数数组 times ，
 * 其中 times[i] = [arrivali, leavingi] 表示第 i 个朋友到达和离开的时刻，
 * 同时给你一个整数 targetFriend 。所有到达时间 互不相同 。
 *
 * 请你返回编号为 targetFriend 的朋友占据的 椅子编号 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：times = [[1,4],[2,3],[4,6]], targetFriend = 1
 * 输出：1
 * 解释：
 * - 朋友 0 时刻 1 到达，占据椅子 0 。
 * - 朋友 1 时刻 2 到达，占据椅子 1 。
 * - 朋友 1 时刻 3 离开，椅子 1 变成未占据。
 * - 朋友 0 时刻 4 离开，椅子 0 变成未占据。
 * - 朋友 2 时刻 4 到达，占据椅子 0 。
 * 朋友 1 占据椅子 1 ，所以返回 1 。
 * 示例 2：
 *
 * 输入：times = [[3,10],[1,5],[2,6]], targetFriend = 0
 * 输出：2
 * 解释：
 * - 朋友 1 时刻 1 到达，占据椅子 0 。
 * - 朋友 2 时刻 2 到达，占据椅子 1 。
 * - 朋友 0 时刻 3 到达，占据椅子 2 。
 * - 朋友 1 时刻 5 离开，椅子 0 变成未占据。
 * - 朋友 2 时刻 6 离开，椅子 1 变成未占据。
 * - 朋友 0 时刻 10 离开，椅子 2 变成未占据。
 * 朋友 0 占据椅子 2 ，所以返回 2 。
 *
 *
 * 提示：
 *
 * n == times.length
 * 2 <= n <= 104
 * times[i].length == 2
 * 1 <= arrivali < leavingi <= 105
 * 0 <= targetFriend <= n - 1
 * 每个 arrivali 时刻 互不相同 。
 */
public class TheNumberOfTheSmallestUnoccupiedChair {

    public static void main(String[] args) {
        System.out.println(new TheNumberOfTheSmallestUnoccupiedChair()
                .smallestChair(new int[][]{{1,4},{2,3},{4,6}}, 1));
    }

    public int smallestChair(int[][] times, int targetFriend) {
        int n = times.length;
        int[][] matrix = new int[n][3];
        for(int i=0;i<n;i++) {
            matrix[i][0] = i;
            matrix[i][1] = times[i][0];
            matrix[i][2] = times[i][1];
        }
        Arrays.sort(matrix, Comparator.comparingInt(o->o[1]));
        PriorityQueue<int[]> using = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        PriorityQueue<Integer> empty = new PriorityQueue<>();
        int next = 0;
        for(int[] arr : matrix) {
            while (!using.isEmpty()&&using.peek()[1]<=arr[1]) {
                empty.add(using.poll()[0]);
            }
            int curr = empty.isEmpty()?next++:empty.poll();
            if(targetFriend==arr[0]) {
                return curr;
            }
            using.offer(new int[]{curr, arr[2]});
        }
        return -1;
    }

}
