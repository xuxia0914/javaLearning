package cn.leetcode.xux.general.midium;

/**
 * 1155. 掷骰子的N种方法
 * 这里有 d 个一样的骰子，每个骰子上都有 f 个面，分别标号为 1, 2, ..., f。
 * 我们约定：掷骰子的得到总点数为各骰子面朝上的数字的总和。
 * 如果需要掷出的总点数为 target，请你计算出有多少种不同的组合情况（所有的组合情况总共有 f^d 种），模 10^9 + 7 后返回。
 *
 *  示例 1：
 * 输入：d = 1, f = 6, target = 3
 * 输出：1
 *
 *  示例 2：
 * 输入：d = 2, f = 6, target = 7
 * 输出：6
 *
 *  示例 3：
 * 输入：d = 2, f = 5, target = 10
 * 输出：1
 *
 *  示例 4：
 * 输入：d = 1, f = 2, target = 3
 * 输出：0
 *
 *  示例 5：
 * 输入：d = 30, f = 30, target = 500
 * 输出：222616187
 *
 * 提示：
 * 1 <= d, f <= 30
 * 1 <= target <= 1000
 */
public class NumberOfDiceRollsWithTargetSum {

    public int numRollsToTarget(int d, int f, int target) {
        if(d<1||f<1||target<d||target>d*f) {
            return 0;
        }
        if(d==1) {
            return target>=1&&target<=f?1:0;
        }
        int[][] dp = new int[d+1][target+1];
        dp[0][0] = 1;
        for(int i=1;i<=d;i++) {
            for(int j=Math.max(i, target-f*(d-i));j<=Math.min(f*i, target-d+i);j++) {
                for(int k=1;k<=Math.min(f, j);k++) {
                    dp[i][j] = (dp[i][j]+dp[i-1][j-k])%1000000007;
                }
            }
        }
        return dp[d][target];
    }

    public static void main(String[] args) {
        NumberOfDiceRollsWithTargetSum nod = new NumberOfDiceRollsWithTargetSum();
        System.out.println(nod.numRollsToTarget(2,6,7));
    }

}
