package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:
 * Si % Sj = 0 or Sj % Si = 0.
 * If there are multiple solutions, return any subset is fine.
 * Example 1:
 * Input: [1,2,3]
 * Output: [1,2] (of course, [1,3] will also be ok)
 * Example 2:
 * Input: [1,2,4,8]
 * Output: [1,2,4,8]
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
