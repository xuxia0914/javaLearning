package cn.leetcode.xux.general.midium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 473. 火柴拼正方形
 * 还记得童话《卖火柴的小女孩》吗？现在，你知道小女孩有多少根火柴，请找出一种能使用所有火柴拼成一个正方形的方法。
 * 不能折断火柴，可以把火柴连接起来，并且每根火柴都要用到。
 * 输入为小女孩拥有火柴的数目，每根火柴用其长度表示。输出即为是否能用所有的火柴拼成正方形。
 *
 * 示例 1:
 * 输入: [1,1,2,2,2]
 * 输出: true
 * 解释: 能拼成一个边长为2的正方形，每边两根火柴。
 *
 * 示例 2:
 * 输入: [3,3,3,3,4]
 * 输出: false
 * 解释: 不能用所有火柴拼成一个正方形。
 *
 * 注意:
 * 给定的火柴长度和在 0 到 10^9之间。
 * 火柴数组的长度不超过15。
 */
public class MatchsticksToSquare {

    public static void main(String[] args) {
        MatchsticksToSquare mts = new MatchsticksToSquare();
        System.out.println(mts.makesquare(new int[]{5,5,5,5,16,4,4,4,4,4,3,3,3,3,4}));
    }

    public boolean makesquare(int[] nums) {
        if(nums==null||nums.length<4) {
            return false;
        }
        Arrays.sort(nums);
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        if(sum==0||sum%4!=0) {
            return false;
        }
        int target = sum/4;
        return makesquare(nums, nums.length-1, new int[4], target);
    }

    public boolean makesquare(int[] nums, int idx, int[] curr, int target) {
        if(idx==-1) {
            return true;
        }
        boolean result = false;
        Set<Integer> mem = new HashSet<>();
        for(int i=0;i<4;i++) {
            if(mem.add(curr[i])&&curr[i]+nums[idx]<=target) {
                curr[i] += nums[idx];
                result |= makesquare(nums, idx-1, curr, target);
                curr[i] -= nums[idx];
            }
        }
        return result;
    }

}
