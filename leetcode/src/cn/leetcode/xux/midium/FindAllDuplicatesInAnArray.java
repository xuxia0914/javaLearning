package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 442. 数组中重复的数据
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 * 找到所有出现两次的元素。
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 *
 * 示例：
 * 输入:[4,3,2,7,8,2,3,1]
 * 输出:[2,3]
 */
public class FindAllDuplicatesInAnArray {

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if(nums==null||nums.length<2) {
            return list;
        }
        int len = nums.length;
        for(int i=0;i<len;i++) {
            if(nums[Math.abs(nums[i])-1]<0) {
                list.add(Math.abs(nums[i]));
            }
            nums[Math.abs(nums[i])-1] = -nums[Math.abs(nums[i])-1];
        }
        return list;
    }

    public List<Integer> findDuplicates1(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if(nums==null||nums.length<2) {
            return list;
        }
        int len = nums.length;
        int idx = 0;
        while(idx<len) {
            if(nums[idx]!=0&&nums[idx]!=idx+1) {
                if(nums[nums[idx]-1]==nums[idx]) {
                    list.add(nums[idx]);
                    nums[idx] = 0;
                    idx++;
                }else {
                    int tmp = nums[nums[idx]-1];
                    nums[nums[idx]-1] = nums[idx];
                    nums[idx] = tmp;
                }
            }else {
                idx++;
            }
        }
        return list;
    }

}
