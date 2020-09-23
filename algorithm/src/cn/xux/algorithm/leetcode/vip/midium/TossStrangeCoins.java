package cn.xux.algorithm.leetcode.vip.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 1230：抛掷硬币
 * 有一些不规则的硬币。在这些硬币中，prob[i] 表示第 i 枚硬币正面朝上的概率。
 * 请对每一枚硬币抛掷 一次，然后返回正面朝上的硬币数等于 target 的概率。
 *
 * 示例 1：
 * 输入：prob = [0.4], target = 1
 * 输出：0.40000
 *
 * 示例 2：
 * 输入：prob = [0.5,0.5,0.5,0.5,0.5], target = 0
 * 输出：0.03125
 *
 * 提示：
 * 1 <= prob.length <= 1000
 * 0 <= prob[i] <= 1
 * 0 <= target <= prob.length
 * 如果答案与标准答案的误差在 10^-5 内，则被视为正确答案。
 */
public class TossStrangeCoins {

    public static void main(String[] args) {
        TossStrangeCoins ts = new TossStrangeCoins();
        System.out.println(ts.probabilityOfHeads(new double[]{0.4},1));
        System.out.println(ts.probabilityOfHeads(new double[]{0.5,0.5,0.5,0.5,0.5},0));
    }

    // dp
    public double probabilityOfHeads(double[] prod, int target) {
        int n = prod.length;
        double[] dp = new double[target+1];
        dp[0] = 1.0;
        for(int i=0;i<n;i++) {
            for(int j=Math.min(i+1, target);j>0;j--) {
                dp[j] = dp[j-1]*prod[i]+dp[j]*(1.0-prod[i]);
            }
            dp[0] = dp[0]*(1.0-prod[i]);
        }
        return dp[target];
    }

    // 递归
    public double probabilityOfHeads1(double[] prod, int target) {
        return dfs(prod, 0, target);
    }

    Map<Integer, Double> mem = new HashMap<>();

    private double dfs(double[] prod, int idx, int target) {
        if(idx<prod.length&&prod.length-idx+1<target) {
            return 0;
        }
        int key = idx*1001+target;
        if(mem.containsKey(key)) {
            return mem.get(key);
        }
        double ans = (1.0-prod[idx])*dfs(prod, idx+1, target);
        if(target>0) {
            ans += prod[idx]*dfs(prod, idx+1, target-1);
        }
        mem.put(key, ans);
        return ans;
    }

}
