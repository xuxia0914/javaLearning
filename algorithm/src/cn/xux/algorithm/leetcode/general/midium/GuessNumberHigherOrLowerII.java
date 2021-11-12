package cn.xux.algorithm.leetcode.general.midium;

/**
 * 375. 猜数字大小 II
 * 我们正在玩一个猜数游戏，游戏规则如下：
 *
 * 我从 1 到 n 之间选择一个数字。
 * 你来猜我选了哪个数字。
 * 如果你猜到正确的数字，就会 赢得游戏 。
 * 如果你猜错了，那么我会告诉你，我选的数字比你的 更大或者更小 ，并且你需要继续猜数。
 * 每当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。如果你花光了钱，就会 输掉游戏 。
 * 给你一个特定的数字 n ，返回能够 确保你获胜 的最小现金数，不管我选择那个数字 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 10
 * 输出：16
 * 解释：制胜策略如下：
 * - 数字范围是 [1,10] 。你先猜测数字为 7 。
 *     - 如果这是我选中的数字，你的总费用为 $0 。否则，你需要支付 $7 。
 *     - 如果我的数字更大，则下一步需要猜测的数字范围是 [8,10] 。你可以猜测数字为 9 。
 *         - 如果这是我选中的数字，你的总费用为 $7 。否则，你需要支付 $9 。
 *         - 如果我的数字更大，那么这个数字一定是 10 。你猜测数字为 10 并赢得游戏，总费用为 $7 + $9 = $16 。
 *         - 如果我的数字更小，那么这个数字一定是 8 。你猜测数字为 8 并赢得游戏，总费用为 $7 + $9 = $16 。
 *     - 如果我的数字更小，则下一步需要猜测的数字范围是 [1,6] 。你可以猜测数字为 3 。
 *         - 如果这是我选中的数字，你的总费用为 $7 。否则，你需要支付 $3 。
 *         - 如果我的数字更大，则下一步需要猜测的数字范围是 [4,6] 。你可以猜测数字为 5 。
 *             - 如果这是我选中的数字，你的总费用为 $7 + $3 = $10 。否则，你需要支付 $5 。
 *             - 如果我的数字更大，那么这个数字一定是 6 。你猜测数字为 6 并赢得游戏，总费用为 $7 + $3 + $5 = $15 。
 *             - 如果我的数字更小，那么这个数字一定是 4 。你猜测数字为 4 并赢得游戏，总费用为 $7 + $3 + $5 = $15 。
 *         - 如果我的数字更小，则下一步需要猜测的数字范围是 [1,2] 。你可以猜测数字为 1 。
 *             - 如果这是我选中的数字，你的总费用为 $7 + $3 = $10 。否则，你需要支付 $1 。
 *             - 如果我的数字更大，那么这个数字一定是 2 。你猜测数字为 2 并赢得游戏，总费用为 $7 + $3 + $1 = $11 。
 * 在最糟糕的情况下，你需要支付 $16 。因此，你只需要 $16 就可以确保自己赢得游戏。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：0
 * 解释：只有一个可能的数字，所以你可以直接猜 1 并赢得游戏，无需支付任何费用。
 * 示例 3：
 *
 * 输入：n = 2
 * 输出：1
 * 解释：有两个可能的数字 1 和 2 。
 * - 你可以先猜 1 。
 *     - 如果这是我选中的数字，你的总费用为 $0 。否则，你需要支付 $1 。
 *     - 如果我的数字更大，那么这个数字一定是 2 。你猜测数字为 2 并赢得游戏，总费用为 $1 。
 * 最糟糕的情况下，你需要支付 $1 。
 *
 *
 * 提示：
 *
 * 1 <= n <= 200
 */
public class GuessNumberHigherOrLowerII {

    int[] ans = new int[]{0,0,1,2,4,6,8,10,12,14,16,18,21,24,
            27,30,34,38,42,46,49,52,55,58,61,64,67,70,73,76,
            79,82,86,90,94,98,102,106,110,114,119,124,129,134,
            139,144,149,154,160,166,172,178,182,186,190,194,
            198,202,206,210,214,218,222,226,230,234,238,242,
            246,250,254,258,262,266,270,274,278,282,286,290,
            295,300,305,310,315,320,325,330,335,340,345,350,
            355,360,365,370,376,382,388,394,400,406,412,418,
            424,430,436,442,448,454,460,466,473,480,487,494,
            501,508,515,522,529,536,543,550,555,560,565,570,
            575,580,585,590,595,600,605,610,615,620,625,630,
            635,640,645,650,655,660,666,674,680,686,692,698,
            703,708,713,718,723,728,733,738,743,748,753,758,
            763,768,773,778,783,788,793,798,803,808,813,818,
            823,828,833,838,843,848,853,858,863,868,873,878,
            883,888,893,898,904,910,916,922,928,934,940,946,952};

    public int getMoneyAmount(int n) {
        return ans[n];
    }

    public int getMoneyAmount1(int n) {
        int[][] dp = new int[n + 2][n + 2];
        for (int len = 2; len <= n; len++) {
            for (int left = 1; left + len - 1 < n + 1; left++) {
                int right = left + len - 1;
                dp[left][right] = Integer.MAX_VALUE;
                for (int guess = left; guess <= right; guess++) {
                    dp[left][right] = Math.min(dp[left][right],
                            guess + Math.max(dp[left][guess - 1], dp[guess + 1][right]));
                }
            }
        }
        return dp[1][n];
    }

}
