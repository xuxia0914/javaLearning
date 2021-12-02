package cn.xux.algorithm.leetcode.general.easy;

import java.util.Arrays;

/**
 * 506. 相对名次
 * 给你一个长度为 n 的整数数组 score ，
 * 其中 score[i] 是第 i 位运动员在比赛中的得分。
 * 所有得分都 互不相同 。
 *
 * 运动员将根据得分 决定名次 ，
 * 其中名次第 1 的运动员得分最高，
 * 名次第 2 的运动员得分第 2 高，依此类推。
 * 运动员的名次决定了他们的获奖情况：
 *
 * 名次第 1 的运动员获金牌 "Gold Medal" 。
 * 名次第 2 的运动员获银牌 "Silver Medal" 。
 * 名次第 3 的运动员获铜牌 "Bronze Medal" 。
 * 从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
 * 使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。
 *
 *
 *
 * 示例 1：
 *
 * 输入：score = [5,4,3,2,1]
 * 输出：["Gold Medal","Silver Medal","Bronze Medal","4","5"]
 * 解释：名次为 [1st, 2nd, 3rd, 4th, 5th] 。
 * 示例 2：
 *
 * 输入：score = [10,3,8,9,4]
 * 输出：["Gold Medal","5","Bronze Medal","Silver Medal","4"]
 * 解释：名次为 [1st, 5th, 3rd, 2nd, 4th] 。
 *
 *
 * 提示：
 *
 * n == score.length
 * 1 <= n <= 104
 * 0 <= score[i] <= 106
 * score 中的所有值 互不相同
 */
public class RelativeRanks {

    public String[] findRelativeRanks(int[] nums) {
        int n = nums.length;
        int max = 0;
        for(int num : nums) {
            max = Math.max(max, num);
        }
        int[] reflect = new int[max+1];
        for(int i=0;i<n;i++) {
            reflect[nums[i]] = i+1;
        }
        String[] res = new String[n];
        for(int i=max,rank=1;i>=0;i--) {
            if(reflect[i]>0) {
                switch (rank) {
                    case 1 : res[reflect[i]-1] = "Gold Medal"; break;
                    case 2 : res[reflect[i]-1] = "Silver Medal"; break;
                    case 3 : res[reflect[i]-1] = "Bronze Medal"; break;
                    default : res[reflect[i]-1] = rank+"";
                }
                rank++;
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
