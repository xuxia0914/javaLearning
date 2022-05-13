package cn.xux.algorithm.leetcode.general.midium;

/**
 * 2212. 射箭比赛中的最大得分
 * Alice 和 Bob 是一场射箭比赛中的对手。比赛规则如下：
 * <p>
 * Alice 先射 numArrows 支箭，然后 Bob 也射 numArrows 支箭。
 * 分数按下述规则计算：
 * 箭靶有若干整数计分区域，范围从 0 到 11 （含 0 和 11）。
 * 箭靶上每个区域都对应一个得分 k（范围是 0 到 11），
 * Alice 和 Bob 分别在得分 k 区域射中 ak 和 bk 支箭。
 * 如果 ak >= bk ，那么 Alice 得 k 分。如果 ak < bk ，则 Bob 得 k 分
 * 如果 ak == bk == 0 ，那么无人得到 k 分。
 * 例如，Alice 和 Bob 都向计分为 11 的区域射 2 支箭，
 * 那么 Alice 得 11 分。如果 Alice 向计分为 11 的区域射 0 支箭，
 * 但 Bob 向同一个区域射 2 支箭，那么 Bob 得 11 分。
 * <p>
 * 给你整数 numArrows 和一个长度为 12 的整数数组 aliceArrows ，
 * 该数组表示 Alice 射中 0 到 11 每个计分区域的箭数量。
 * 现在，Bob 想要尽可能 最大化 他所能获得的总分。
 * <p>
 * 返回数组 bobArrows ，
 * 该数组表示 Bob 射中 0 到 11 每个 计分区域的箭数量。
 * 且 bobArrows 的总和应当等于 numArrows 。
 * <p>
 * 如果存在多种方法都可以使 Bob 获得最大总分，返回其中 任意一种 即可。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：numArrows = 9, aliceArrows = [1,1,0,1,0,0,2,1,0,1,2,0]
 * 输出：[0,0,0,0,1,1,0,0,1,2,3,1]
 * 解释：上表显示了比赛得分情况。
 * Bob 获得总分 4 + 5 + 8 + 9 + 10 + 11 = 47 。
 * 可以证明 Bob 无法获得比 47 更高的分数。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：numArrows = 3, aliceArrows = [0,0,1,0,0,0,0,0,0,0,0,2]
 * 输出：[0,0,0,0,0,0,0,0,1,1,1,0]
 * 解释：上表显示了比赛得分情况。
 * Bob 获得总分 8 + 9 + 10 = 27 。
 * 可以证明 Bob 无法获得比 27 更高的分数。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= numArrows <= 105
 * aliceArrows.length == bobArrows.length == 12
 * 0 <= aliceArrows[i], bobArrows[i] <= numArrows
 * sum(aliceArrows[i]) == numArrows
 */
public class MaximumPointsInAnArcheryCompetition {

    public static void main(String[] args) {
        new MaximumPointsInAnArcheryCompetition().maximumBobPoints(
                9, new int[]{1, 1, 0, 1, 0, 0, 2, 1, 0, 1, 2, 0}
        );
    }

    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        int max = 0;
        int state = 0;
        for (int i = 1; i < 2048; i++) {
            int tmp1 = i;
            int tmp2 = numArrows;
            int idx = 1;
            int score = 0;
            while (tmp1 > 0 && tmp2 > 0) {
                if ((tmp1 & 1) == 1) {
                    if (tmp2 > aliceArrows[idx]) {
                        score += idx;
                        tmp2 -= aliceArrows[idx] + 1;
                    } else {
                        score = 0;
                        break;
                    }
                }
                tmp1 >>= 1;
                idx++;
            }
            if (score > max) {
                max = score;
                state = i;
            }
        }
        int[] ans = new int[12];
        int i = 1;
        int n0 = numArrows;
        while (state > 0) {
            if ((state & 1) == 1) {
                ans[i] = aliceArrows[i] + 1;
                n0 -= ans[i];
            }
            state >>= 1;
            i++;
        }
        ans[0] = n0;
        return ans;
    }

}
