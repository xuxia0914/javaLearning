package cn.xux.algorithm.leetcode.general.hard;

/**
 * 1563. 石子游戏 V
 * 几块石子 排成一行 ，每块石子都有一个关联值，关联值为整数，由数组 stoneValue 给出。
 * 游戏中的每一轮：Alice 会将这行石子分成两个 非空行（即，左侧行和右侧行）；
 * Bob 负责计算每一行的值，即此行中所有石子的值的总和。Bob 会丢弃值最大的行，
 * Alice 的得分为剩下那行的值（每轮累加）。如果两行的值相等，Bob 让 Alice 决定丢弃哪一行。
 * 下一轮从剩下的那一行开始。
 * 只 剩下一块石子 时，游戏结束。Alice 的分数最初为 0 。
 * 返回 Alice 能够获得的最大分数 。
 *
 * 示例 1：
 * 输入：stoneValue = [6,2,3,4,5,5]
 * 输出：18
 * 解释：在第一轮中，Alice 将行划分为 [6，2，3]，[4，5，5] 。
 * 左行的值是 11 ，右行的值是 14 。Bob 丢弃了右行，Alice 的分数现在是 11 。
 * 在第二轮中，Alice 将行分成 [6]，[2，3] 。
 * 这一次 Bob 扔掉了左行，Alice 的分数变成了 16（11 + 5）。
 * 最后一轮 Alice 只能将行分成 [2]，[3] 。
 * Bob 扔掉右行，Alice 的分数现在是 18（16 + 2）。游戏结束，因为这行只剩下一块石头了。
 *
 * 示例 2：
 * 输入：stoneValue = [7,7,7,7,7,7,7]
 * 输出：28
 *
 * 示例 3：
 * 输入：stoneValue = [4]
 * 输出：0
 *
 * 提示：
 * 1 <= stoneValue.length <= 500
 * 1 <= stoneValue[i] <= 10^6
 */
public class StoneGameV {

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[][] dp  = new int[n][n];
        int[] preSum = new int[n+1];
        for(int i=0;i<n;i++) {
            preSum[i+1] = preSum[i]+stoneValue[i];
        }
        // mids[i][j]=k表示从i到j的子数组中，sum(i,k)<sum(k+1,j)&&sum(i,k+1)>=sum(k+2,j)
        int[][] mids =new int[n][n];
        for(int i=0;i<n;i++) {
            mids[i][i] = i;
        }

        for(int start=0;start<n-1;start++) {
            for(int end = start+1;end<n;end++) {
                // 如此初始化mid的值使得下面while循环执行次数的总和的时间复杂度为O(n)
                int mid = mids[start][end-1];
                while(mid+1<end&&preSum[mid+2]-preSum[start]<preSum[end+1]-preSum[mid+2]) {
                    mid++;
                }
                mids[start][end] = mid;
            }
        }
        for(int len=2;len<=n;len++) {
            for(int start=0;start+len-1<n;start++) {
                int end = start+len-1;
                int mid = mids[start][end];
                int leftSum = preSum[mid+1]-preSum[start];
                int rightSum = preSum[end+1]-preSum[mid+1];
                if(leftSum<rightSum) {
                    dp[start][end] = Math.max(dp[start][end], leftSum+dp[start][mid]);
                }else if(leftSum>rightSum) {
                    dp[start][end] = Math.max(dp[start][end], rightSum+dp[mid+1][end]);
                }else {
                    dp[start][end] = Math.max(dp[start][end],
                            leftSum+Math.max(dp[start][mid], dp[mid+1][end]));
                }
                if(leftSum<rightSum&&mid<end-1) {
                    mid++;
                    leftSum += stoneValue[mid];
                    rightSum -= stoneValue[mid];
                    if(leftSum>rightSum) {
                        dp[start][end] = Math.max(dp[start][end], rightSum+dp[mid+1][end]);
                    }else {
                        dp[start][end] = Math.max(dp[start][end],
                                leftSum+Math.max(dp[start][mid], dp[mid+1][end]));
                    }
                }
            }
        }
        return dp[0][n-1];
    }

    public int stoneGameV1(int[] stoneValue) {
        int n = stoneValue.length;
        int[][] dp  = new int[n][n];
        int[] preSum = new int[n+1];
        for(int i=0;i<n;i++) {
            preSum[i+1] = preSum[i]+stoneValue[i];
        }
        for(int len=2;len<=n;len++) {
            for(int start=0;start+len-1<n;start++) {
                int end = start+len-1;
                for(int mid=start;mid<end;mid++) {
                    int leftSum = preSum[mid+1]-preSum[start];
                    int rightSum = preSum[end+1]-preSum[mid+1];
                    if(leftSum<rightSum) {
                        dp[start][end] = Math.max(dp[start][end], leftSum+dp[start][mid]);
                    }else if(leftSum>rightSum) {
                        dp[start][end] = Math.max(dp[start][end], rightSum+dp[mid+1][end]);
                    }else {
                        dp[start][end] = Math.max(dp[start][end],
                                leftSum+Math.max(dp[start][mid], dp[mid+1][end]));
                    }
                }
            }
        }
        return dp[0][n-1];
    }

}
