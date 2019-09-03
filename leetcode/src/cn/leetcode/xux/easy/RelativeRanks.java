package cn.leetcode.xux.easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 506. 相对名次
 * 给出 N 名运动员的成绩，找出他们的相对名次并授予前三名对应的奖牌。前三名运动员将会被分别授予 “金牌”，“银牌” 和“ 铜牌”（"Gold Medal", "Silver Medal", "Bronze Medal"）。
 * (注：分数越高的选手，排名越靠前。)
 *
 * 示例 1:
 * 输入: [5, 4, 3, 2, 1]
 * 输出: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
 * 解释: 前三名运动员的成绩为前三高的，因此将会分别被授予 “金牌”，“银牌”和“铜牌” ("Gold Medal", "Silver Medal" and "Bronze Medal").
 * 余下的两名运动员，我们只需要通过他们的成绩计算将其相对名次即可。
 *
 * 提示:
 * N 是一个正整数并且不会超过 10000。
 * 所有运动员的成绩都不相同。
 */
public class RelativeRanks {

    public String[] findRelativeRanks(int[] nums) {
        int n = nums.length;
        int max = 0;
        for(int num : nums) {
            max = Math.max(max, num);
        }
        int[] dp = new int[max+1];
        for(int i=0;i<n;i++) {
            dp[nums[i]] = i+1;
        }
        String[] res = new String[n];
        for(int i=max,flag=1;i>=0;i--) {
            if(dp[i]>0) {
                switch (flag) {
                    case 1 : res[dp[i]-1] = "Gold Medal"; break;
                    case 2 : res[dp[i]-1] = "Silver Medal"; break;
                    case 3 : res[dp[i]-1] = "Bronze Medal"; break;
                    default : res[dp[i]-1] = flag+"";
                }
                flag++;
            }
        }
        return res;
    }

    public String[] findRelativeRanks1(int[] nums) {
        int n = nums.length;
        Integer[] idxs = new Integer[n];
        for(int i=0;i<n;i++) {
            idxs[i] = i;
        }
        String[] res = new String[n];
        Arrays.sort(idxs, (o1, o2)->nums[o2]-nums[o1]);
        for(int i=0;i<n;i++) {
            if(i==0) {
                res[idxs[i]] = "Gold Medal";
            }else if(i==1) {
                res[idxs[i]] = "Silver Medal";
            }else if(i==2) {
                res[idxs[i]] = "Bronze Medal";
            }else {
                res[idxs[i]] = i+1+"";
            }
        }
        return res;
    }

}
