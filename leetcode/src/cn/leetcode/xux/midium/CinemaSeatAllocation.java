package cn.leetcode.xux.midium;

import java.util.Arrays;

/**
 * 1386. 安排电影院座位
 * 1 --- ---- ---
 * 2 --- ---- ---
 * 3 --- ---- ---
 * 4 --- ---- ---
 * 如上图所示，电影院的观影厅中有 n 行座位，行编号从 1 到 n ，
 * 且每一行内总共有 10 个座位，列编号从 1 到 10 。
 * 给你数组 reservedSeats ，包含所有已经被预约了的座位。
 * 比如说，researvedSeats[i]=[3,8] ，它表示第 3 行第 8 个座位被预约了。
 * 请你返回 最多能安排多少个 4 人家庭 。4 人家庭要占据 同一行内连续 的 4 个座位。
 * 隔着过道的座位（比方说 [3,3] 和 [3,4]）不是连续的座位，
 * 但是如果你可以将 4 人家庭拆成过道两边各坐 2 人，这样子是允许的。
 *
 * 示例 1：
 * 输入：n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
 * 输出：4
 * 解释：上图所示是最优的安排方案，总共可以安排 4 个家庭。
 * 蓝色的叉表示被预约的座位，橙色的连续座位表示一个 4 人家庭。
 *
 * 示例 2：
 * 输入：n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
 * 输出：2
 *
 * 示例 3：
 * 输入：n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
 * 输出：4
 *
 * 提示：
 * 1 <= n <= 10^9
 * 1 <= reservedSeats.length <= min(10*n, 10^4)
 * reservedSeats[i].length == 2
 * 1 <= reservedSeats[i][0] <= n
 * 1 <= reservedSeats[i][1] <= 10
 * 所有 reservedSeats[i] 都是互不相同的。
 */
public class CinemaSeatAllocation {

    //3
    //[[2,3]]
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Arrays.sort(reservedSeats, (o1,o2)->o1[0]==o2[0]?o1[1]-o2[1]:o1[0]-o2[0]);
        int len = reservedSeats.length;
        boolean[] booked = new boolean[10];
        int ans = 0;
        int preRow = 0;
        for(int i=0;i<len;i++) {
            booked[reservedSeats[i][1]-1] = true;
            if(i==len-1||reservedSeats[i+1][0]!=reservedSeats[i][0]) {
                ans += 2*(reservedSeats[i][0]-preRow-1);
                preRow = reservedSeats[i][0];
                boolean flag1 = !booked[1]&!booked[2];
                boolean flag2 = !booked[3]&!booked[4];
                boolean flag3 = !booked[5]&!booked[6];
                boolean flag4 = !booked[7]&!booked[8];
                if(flag1&flag2) {
                    ans++;
                    if(flag3&flag4) {
                        ans++;
                    }
                }else {
                    if(flag2&flag3) {
                        ans++;
                    }else if(flag3&flag4) {
                        ans++;
                    }
                }
                booked = new boolean[10];
            }
        }
        ans += 2*(n-preRow-1);
        return ans;
    }

}
