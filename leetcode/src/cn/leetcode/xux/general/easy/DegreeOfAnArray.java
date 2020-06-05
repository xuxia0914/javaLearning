package cn.leetcode.xux.general.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
 * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
 * Example 1:
 * Input: [1, 2, 2, 3, 1]
 * Output: 2
 * Explanation:
 * The input array has a degree of 2 because both elements 1 and 2 appear twice.
 * Of the subarrays that have the same degree:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * The shortest length is 2. So return 2.
 * Example 2:
 * Input: [1,2,2,3,1,4,2]
 * Output: 6
 * Note:
 * nums.length will be between 1 and 50,000.
 * nums[i] will be an integer between 0 and 49,999.
 */
public class DegreeOfAnArray {

    public int findShortestSubArray(int[] nums) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        Map<Integer, int[]> map = new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            if(!map.containsKey(nums[i])) {
                int[] tmp = new int[3];
                tmp[0] = 1;
                tmp[1] = i;
                tmp[2] = i;
                map.put(nums[i], tmp);
            }else {
                int[] tmp = map.get(nums[i]);
                tmp[0]++;
                tmp[2] = i;
            }
        }
        int maxDegree = 0;
        int res = Integer.MAX_VALUE;
        for(Map.Entry<Integer, int[]> entry : map.entrySet()) {
            if(entry.getValue()[0]==maxDegree) {
                res = Math.min(res, entry.getValue()[2]-entry.getValue()[1]+1);
            }
            if(entry.getValue()[0]>maxDegree) {
                maxDegree = entry.getValue()[0];
                res = entry.getValue()[2]-entry.getValue()[1]+1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new DegreeOfAnArray().findShortestSubArray(new int[]{1,2,2,3,1,4,2}));
    }

}
