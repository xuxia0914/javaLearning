package cn.xux.algorithm.leetcode.vip.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 465. 最优账单平衡
 * 一群朋友在度假期间会相互借钱。比如说，小爱同学支付了小新同学的午餐共计 10 美元。
 * 如果小明同学支付了小爱同学的出租车钱共计 5 美元。
 * 我们可以用一个三元组 (x, y, z) 表示一次交易，
 * 表示 x 借给 y 共计 z 美元。用 0, 1, 2 表示小爱同学、小新同学和小明同学（0, 1, 2 为人的标号），
 * 上述交易可以表示为 [[0, 1, 10], [2, 0, 5]]。
 *
 * 给定一群人之间的交易信息列表，计算能够还清所有债务的最小次数。
 *
 * 注意：
 * 一次交易会以三元组 (x, y, z) 表示，并有 x ≠ y 且 z > 0。
 * 人的标号可能不是按顺序的，例如标号可能为 0, 1, 2 也可能为 0, 2, 6。
 *
 * 示例 1：
 * 输入：[[0,1,10], [2,0,5]]
 * 输出：2
 * 解释：
 * 人 #0 给人 #1 共计 10 美元。
 * 人 #2 给人 #0 共计 5 美元。
 * 需要两次交易。一种方式是人 #1 分别给人 #0 和人 #2 各 5 美元。
 *
 * 示例 2：
 * 输入：[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
 * 输出：1
 * ​
 * 解释：
 * 人 #0 给人 #1 共计 10 美元。Person #0 gave person #1 $10.
 * 人 #1 给人 #0 共计 1 美元。Person #1 gave person #0 $1.
 * 人 #1 给人 #2 共计 5 美元。Person #1 gave person #2 $5.
 * 人 #2 给人 #0 共计 5 美元。Person #2 gave person #0 $5.
 * 因此，人 #1 需要给人 #0 共计 4 美元，所有的债务即可还清。
 */
public class OptimalAccountBalancing {

    public int minTransfers(int[][] transactions) {
        if(transactions==null||transactions.length==0) {
            return 0;
        }
        //计算每个节点的权重(出则减，进则加)
        Map<Integer, Integer> heavy = new HashMap<>();
        for(int[] e : transactions) {
            heavy.put(e[0], heavy.getOrDefault(e[0], 0)-e[2]);
            heavy.put(e[1], heavy.getOrDefault(e[1], 0)+e[2]);
        }
        //记录所有权重不为0的节点
        int[] nums = new int[heavy.size()];
        int len = 0;
        for(int v : heavy.values()) {
            if(v!=0) {
                nums[len++] = v;
            }
        }
        if(len==0) {
            return 0;
        }
        //dp[i]表示第i个子集(i的二进制表示的第j位为1,则nums[j]在该子集中)达成平衡需要的增加的边数
        int[] dp = new int[1<<len];
        Arrays.fill(dp, -1);
        for(int i=1;i<dp.length;i++) {
            int sum = 0;
            int cnt = 0;
            //计算该子集的和，子集的节点数
            for(int j=0;j<len;j++) {
                if(((1<<j)&i)>0) {
                    sum += nums[j];
                    cnt++;
                }
            }
            if(sum==0) {
                dp[i] = cnt-1;
                for(int j=1;j<i;j++) {
                    // i子集包含j子集
                    if((i&j)==j&&dp[j]!=-1&&dp[i-j]!=-1) {
                        dp[i] = Math.min(dp[i], dp[j]+dp[i-j]);
                    }
                }
            }
        }
        return dp[dp.length-1];
    }

}
