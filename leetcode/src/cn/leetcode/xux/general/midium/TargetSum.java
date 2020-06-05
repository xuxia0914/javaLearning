package cn.leetcode.xux.general.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 494. 目标和
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 *
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 * 示例 1:
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 *
 * 一共有5种方法让最终目标和为3。
 * 注意:
 * 数组的长度不会超过20，并且数组中的值全为正数。
 * 初始的数组的和不会超过1000。
 * 保证返回的最终结果为32位整数。
 */
public class TargetSum {

    public static void main(String[] args) {
        TargetSum ts = new TargetSum();
        System.out.println(ts.findTargetSumWays(new int[]{1,1,1,1,1}, 3));
    }

    public int findTargetSumWays(int[] nums, int S) {
        if(nums==null||nums.length==0||S<-1000||S>1000) {
            return 0;
        }
        int[] dp = new int[2001];
        dp[-nums[0]+1000]++;
        dp[nums[0]+1000]++;
        int[] tmp;
        for(int i=1;i<nums.length;i++) {
            tmp = new int[2001];
            for(int j=0;j<2001;j++) {
                if(dp[j]>0) {
                    if(j-nums[i]>=0) {
                        tmp[j-nums[i]] += dp[j];
                    }
                    if(j+nums[i]<2001) {
                        tmp[j+nums[i]] += dp[j];
                    }
                }
            }
            dp = tmp;
        }
        return dp[S+1000];
    }

    Map<String, Integer> mem = new HashMap<>();
    public int findTargetSumWays1(int[] nums, int S) {
        return helper(nums, 0, S);
    }

    public int helper(int[] nums, int start, int sum) {
        if(start>=nums.length) {
            return sum==0?1:0;
        }
        if(mem.containsKey(""+start+"#"+sum)) {
            return mem.get(""+start+"#"+sum);
        }
        int result = helper(nums, start+1, sum-nums[start])
                +helper(nums, start+1, sum+nums[start]);
        mem.put(""+start+"#"+sum, result);
        return result;
    }

}
