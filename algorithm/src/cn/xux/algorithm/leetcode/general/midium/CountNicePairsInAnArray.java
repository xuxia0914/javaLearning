package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 1814. 统计一个数组中好对子的数目
 * 给你一个数组 nums ，数组中只包含非负整数。
 * 定义 rev(x) 的值为将整数 x 各个数字位反转得到的结果。
 * 比方说 rev(123) = 321 ， rev(120) = 21 。
 * 我们称满足下面条件的下标对 (i, j) 是 好的 ：
 * 0 <= i < j < nums.length
 * nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
 * 请你返回好下标对的数目。由于结果可能会很大，请将结果对 109 + 7 取余 后返回。
 *
 * 示例 1：
 * 输入：nums = [42,11,1,97]
 * 输出：2
 * 解释：两个坐标对为：
 *  - (0,3)：42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121 。
 *  - (1,2)：11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12 。
 *
 * 示例 2：
 * 输入：nums = [13,10,35,24,76]
 * 输出：4
 *
 * 提示：
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 */
public class CountNicePairsInAnArray {

    // 好对子nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
    // 则有nums[i] - rev(nums[i]) == nums[j] - rev(nums[j])
    // 把nums[i] - rev(nums[i])作为key存map,map,value为个数，两两组合都是好对子
    public int countNicePairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            int key = getKey(num);
            map.put(key, map.getOrDefault(key, 0)+1);
        }
        long ans = 0L;
        for(int v : map.values()) {
            ans = (ans+(long)v*(v-1)/2)%1000000007;
        }
        return (int)ans;
    }

    private int getKey(int num) {
        int rev = 0;
        int tmp = num;
        while(tmp>0) {
            rev = rev*10+tmp%10;
            tmp /= 10;
        }
        return num-rev;
    }

}
