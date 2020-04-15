package cn.leetcode.xux.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 674. 最长连续递增序列
 * 给定一个未经排序的整数数组，找到最长且连续的的递增序列。
 *
 * 示例 1:
 * 输入: [1,3,5,4,7]
 * 输出: 3
 * 解释: 最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
 *
 * 示例 2:
 * 输入: [2,2,2,2,2]
 * 输出: 1
 * 解释: 最长连续递增序列是 [2], 长度为1。
 *
 * 注意：数组长度不会超过10000。
 */
public class LongestContinuousIncreasingSubsequence {

    public int findLengthOfLCIS(int[] nums) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        int len = 1;
        int res = 1;
        for(int i=1;i<nums.length;i++) {
            if(nums[i]>nums[i-1]) {
                len++;
            }else {
                res = Math.max(res, len);
                len = 1;
            }
        }
        return Math.max(len, res);
    }

    public static List<Integer> solution(int[] array) {
        List<Integer> result = new ArrayList<Integer>();
        if(array==null||array.length==0) {
            return result;
        }
        List<Integer> list = new ArrayList<Integer>();
        list.add(array[0]);
        for(int i=1;i<array.length;i++) {
            if(array[i]>array[i-1]) {
                list.add(array[i]);
            }else if(list.size()>result.size()) {
                result = new ArrayList<Integer>(list);
                list.clear();
                list.add(array[i]);
            }
        }
        if(list.size()>result.size()) {
            result = new ArrayList<Integer>(list);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,3,5,4,7}));
        System.out.println(solution(new int[]{2,2,2,2,2}));
    }

}
