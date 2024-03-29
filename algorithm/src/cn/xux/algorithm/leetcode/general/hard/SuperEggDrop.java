package cn.xux.algorithm.leetcode.general.hard;

/**
 * 887. 鸡蛋掉落
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，
 * 从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 * 你的目标是确切地知道 F 的值是多少。
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 *
 * 示例 1：
 * 输入：K = 1, N = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
 * 如果它没碎，那么我们肯定知道 F = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
 *
 * 示例 2：
 * 输入：K = 2, N = 6
 * 输出：3
 *
 * 示例 3：
 * 输入：K = 3, N = 14
 * 输出：4
 *
 * 提示：
 * 1 <= K <= 100
 * 1 <= N <= 10000
 */
public class SuperEggDrop {

    public int superEggDrop(int K, int N) {
        if (N == 1) {
            return 1;
        }
        // f[i][j]：i次操作 有j个鸡蛋时 可以确定多少层楼的F值
        int[][] f = new int[N + 1][K + 1];
        for (int i = 1; i <= K; ++i) {
            f[1][i] = 1;
        }
        int ans = -1;
        for (int i = 2; i <= N; ++i) {
            for (int j = 1; j <= K; ++j) {
                f[i][j] = 1 + f[i - 1][j - 1] + f[i - 1][j];
            }
            if (f[i][K] >= N) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    public int superEggDrop1(int K, int N) {
        int[][] dp = new int[N+1][K+1];
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=K;j++) {
                dp[i][j] = i;
            }
        }
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=K;j++) {
                for(int k=1;k<i;k++) {
                    dp[i][j] = Math.min(dp[i][j], 1+Math.max(dp[k][j-1], dp[i-k][j]));
                }
                /*int left = 1;
                int right = i;
                while(left<right) {
                    int mid = (left+right)/2;
                    int leftVal = dp[mid][j-1];
                    int rightVal = dp[i-mid][j];
                    if(rightVal>leftVal) {
                        left = mid+1;
                    }else if(rightVal<leftVal) {
                        right = mid-1;
                    }else {
                        left = mid;
                        break;
                    }
                }
                dp[i][j] = dp[left][j-1];*/
            }
        }
        return dp[N][K];
    }


}
