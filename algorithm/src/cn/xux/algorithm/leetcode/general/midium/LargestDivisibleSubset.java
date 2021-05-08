package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 368. 最大整除子集
 * 给你一个由 无重复 正整数组成的集合 nums ，
 * 请你找出并返回其中最大的整除子集 answer ，
 * 子集中每一元素对 (answer[i], answer[j]) 都应当满足：
 * answer[i] % answer[j] == 0 ，或
 * answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,2]
 * 解释：[1,3] 也会被视为正确答案。
 *
 * 示例 2：
 * 输入：nums = [1,2,4,8]
 * 输出：[1,2,4,8]
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2 * 109
 * nums 中的所有整数 互不相同
 */
public class LargestDivisibleSubset {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        if(nums==null||nums.length==0) {
            return new ArrayList<>();
        }
        int maxSize = 0;
        int index = -1;
        Arrays.sort(nums);
        List<List<Integer>> dp = new ArrayList<>();
        for(int i=0;i<nums.length;i++) {
            for(int j=0;j<i;j++) {
                if(nums[i]%dp.get(j).get(dp.get(j).size()-1)==0&&dp.get(j).size()>maxSize) {
                    maxSize = dp.get(j).size();
                    index = j;
                }
            }
            List<Integer> list;
            if(maxSize==0) {
                list = new ArrayList<>();
            }else {
                list = new ArrayList<>(dp.get(index));
            }
            list.add(nums[i]);
            dp.add(list);
            maxSize = 0;
            index = -1;
        }
        for(int i=0;i<dp.size();i++) {
            if(dp.get(i).size()>maxSize) {
                maxSize = dp.get(i).size();
                index = i;
            }
        }
        return dp.get(index);
    }

    public static void main(String[] args) {
        LargestDivisibleSubset lds = new LargestDivisibleSubset();
        System.out.println(lds.largestDivisibleSubset(new int[]{2,3,4,9,8}));
    }

}
