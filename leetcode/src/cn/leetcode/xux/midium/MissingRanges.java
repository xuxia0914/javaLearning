package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 163. 缺失的区间
 * 给定一个排序的整数数组 nums ，其中元素的范围在 闭区间 [lower, upper] 当中，返回不包含在数组中的缺失区间。
 *
 * 示例：
 * 输入: nums = [0, 1, 3, 50, 75], lower = 0 和 upper = 99,
 * 输出: ["2", "4->49", "51->74", "76->99"]
 *
 * 思路：
 * 双指针法，用start和end表示可能缺失的区间，
 * 如果真的缺失，就把缺失的区间插入到res里，否则重置start， end。
 */
public class MissingRanges {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if(nums==null||nums.length==0) {
            res.add(helper(lower, upper));
        }
        if(nums[0]>lower) {
            res.add(helper(lower, nums[0]-1));
        }
        int left = 0;
        int right = 1;
        while(right<nums.length) {
            if(nums[left]+1<nums[right]) {
                res.add(helper(nums[left]+1, nums[right]-1));
            }
            left++;
            right++;
        }
        if(nums[nums.length-1]<upper) {
            res.add(helper(nums[nums.length-1]+1, upper));
        }
        return res;
    }

    public String helper(int a, int b) {
        if(a==b) {
            return String.valueOf(a);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(a).append("->").append(b);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new MissingRanges().findMissingRanges(new int[]{1, 3, 50, 75, 99}, 0, 99));
    }

}
