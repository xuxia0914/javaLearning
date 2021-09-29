package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1109. 航班预订统计
 * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
 * 我们这儿有一份航班预订表，表中第 i 条预订记录 bookings[i] = [i, j, k]
 * 意味着我们在从 i 到 j 的每个航班上预订了 k 个座位。
 * 请你返回一个长度为 n 的数组 answer，按航班编号顺序返回每个航班上预订的座位数。
 *
 * 示例：
 * 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * 输出：[10,55,45,25,25]
 *
 * 提示：
 * 1 <= bookings.length <= 20000
 * 1 <= bookings[i][0] <= bookings[i][1] <= n <= 20000
 * 1 <= bookings[i][2] <= 10000
 */
public class CorporateFlightBookings {

    public static void main(String[] args) {
        new CorporateFlightBookings().corpFlightBookings(new int[][]{{1,2,10},{2,3,20},{2,5,25}}, 5);
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans = new int[n];
        for(int[] booking : bookings) {
            ans[booking[0]-1] += booking[2];
            if(booking[1]<n) {
                ans[booking[1]] -= booking[2];
            }
        }
        for(int i=1;i<n;i++) {
            ans[i] += ans[i-1];
        }
        return ans;
    }

    public int[] corpFlightBookings1(int[][] bookings, int n) {
        int len = bookings.length;
        Arrays.sort(bookings, Comparator.comparingInt(o->o[0]));
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        int[] result = new int[n];
        int currBook = 0;
        int idx = 0;
        for(int i=0;i<n;i++) {
            while(!queue.isEmpty()&&queue.peek()[1]-1<i) {
                int[] out = queue.poll();
                currBook -= out[2];
            }
            while(idx<len&&bookings[idx][0]-1<=i) {
                queue.offer(bookings[idx]);
                currBook += bookings[idx][2];
                idx++;
            }
            result[i] = currBook;
        }
        return result;
    }

    public int[] corpFlightBookings2(int[][] bookings, int n) {
        int[] result = new int[n];
        for(int[] booking : bookings) {
            for(int i=booking[0]-1;i<=booking[1]-1;i++) {
                result[i] += booking[2];
            }
        }
        return result;
    }

}
