package cn.leetcode.xux.general.hard;

import java.util.Arrays;

/**
 * 164. 最大间距
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 * 如果数组元素个数小于 2，则返回 0。
 *
 * 示例 1:
 * 输入: [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 *
 * 示例 2:
 * 输入: [10]
 * 输出: 0
 * 解释: 数组元素个数小于 2，因此返回 0。
 *
 * 说明:
 * 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 * 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 */
public class MaximumGap {

    public static void main(String[] args) {
        System.out.println(new MaximumGap().maximumGap(new int[]{1,1,1,1,1,5,5,5,5,5}));
    }

    //桶排序
    public int maximumGap(int[] nums) {
        if(nums.length<2) {
            return 0;
        }
        int max = nums[0];
        int min = nums[0];
        for(int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        if(max-min<2) {
            return max-min;
        }
        int dis = Math.max((max-min)/(nums.length-1),1);
        int size = (max-min+1)/dis+((max-min+1)%dis!=0?1:0);
        Integer[][] bucket = new Integer[size][2];
        for(int num : nums) {
            int idx = (num-min)/dis;
            bucket[idx][0] = bucket[idx][0]==null?num:Math.min(bucket[idx][0], num);
            bucket[idx][1] = bucket[idx][1]==null?num:Math.max(bucket[idx][1], num);
        }
        int ans = 0;
        Integer preMax = null;
        for(int i=0;i<size;i++) {
            if(bucket[i][0]!=null) {
                if(preMax!=null) {
                    ans = Math.max(ans, bucket[i][0]-preMax);
                }
                preMax = bucket[i][1];
            }
        }
        return ans;
    }

    public int maximumGap1(int[] nums) {
        if(nums.length<2) {
            return 0;
        }
        Arrays.sort(nums);
        int ans = 0;
        for(int i=1;i<nums.length;i++) {
            ans = Math.max(ans, nums[i]-nums[i-1]);
        }
        return ans;
    }

}
