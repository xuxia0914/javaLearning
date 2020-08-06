package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 1140. 石子游戏 II
 * 亚历克斯和李继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。
 * 亚历克斯和李轮流进行，亚历克斯先开始。最初，M = 1。
 * 在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。
 * 游戏一直持续到所有石子都被拿走。
 * 假设亚历克斯和李都发挥出最佳水平，返回亚历克斯可以得到的最大数量的石头。
 *
 * 示例：
 * 输入：piles = [2,7,9,4,4]
 * 输出：10
 * 解释：
 * 如果亚历克斯在开始时拿走一堆石子，李拿走两堆，接着亚历克斯也拿走两堆。在这种情况下，亚历克斯可以拿到 2 + 4 + 4 = 10 颗石子。
 * 如果亚历克斯在开始时拿走两堆石子，那么李就可以拿走剩下全部三堆石子。在这种情况下，亚历克斯可以拿到 2 + 7 = 9 颗石子。
 * 所以我们返回更大的 10。
 * 提示：
 * 1 <= piles.length <= 100
 * 1 <= piles[i] <= 10 ^ 4
 */
public class StoneGameII {

    public static void main(String[] args) {
        System.out.println(new StoneGameII().stoneGameII0(new int[]{8,9,5,4,5,4,1,1,9,3,1,10,5,9,6,2,7,6,6,9}));
    }

    //dp
    public int stoneGameII0(int[] piles) {
        int len = piles.length;
        //m=j时从i开始拿能拿到的最大的石头数
        int[][] dp = new int[len][len+1];
        int[] sums = new int[len+1];
        for(int i=1;i<=len;i++) {
            sums[i] = sums[i-1]+piles[i-1];
        }
        for(int i=0;i<len;i++) {
            int total = sums[len]-sums[i];
            for(int j=len;2*j>=len-i;j--) {
                dp[i][j] = total;
            }
        }
        for(int i=len-2;i>=0;i--) {
            int total = sums[len]-sums[i];
            for(int j=1;2*j<len-i;j++) {
                for(int k=1;k<=2*j&&i+k<len&&k<=len;k++) {
                    dp[i][j] = Math.max(dp[i][j], total-dp[i+k][Math.max(j, k)]);
                }
            }
        }
        return dp[0][1];
    }

    //记录每次递归的计算结果避免重复计算
    Map<Integer, int[]> map = new HashMap<>();

    public int stoneGameII(int[] piles) {
        if(piles==null||piles.length==0) {
            return 0;
        }
        int n = piles.length;
        int[] sums = new int[n+1];
        sums[0] = 0;
        for(int i=1;i<=n;i++) {
            sums[i] = piles[i-1]+sums[i-1];
        }
        return helper(piles, 0, 1, sums, sums[n]);
    }

    public int helper(int[] piles, int start, int m, int[] sums, int sum) {
        int currRes = 0;
        if(start+2*m>=piles.length) {
            return sums[piles.length]-sums[start];
        }
        if(map.containsKey(start)&&map.get(start)[m]!=0) {
            return map.get(start)[m];
        }
        for(int i=1;i<=2*m;i++) {
            int tmp = sums[start+i]-sums[start];
            int post = helper(piles, start+i, Math.max(i, m), sums, sum-tmp);
            currRes = Math.max(sum-post, currRes);
        }
        if(map.containsKey(start)) {
            map.get(start)[m] = currRes;
        }else {
            int[] nums = new int[piles.length];
            nums[m] = currRes;
            map.put(start, nums);
        }
        return currRes;
    }

}
