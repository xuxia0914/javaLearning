package cn.leetcode.xux.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 169. 求众数
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 *
 * 示例 1:
 * 输入: [3,2,3]
 * 输出: 3
 *
 * 示例 2:
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 */
public class MajorityElement {

    //Boyer-Moore 投票算法
    public int majorityElement(int[] nums) {
        Integer curr = null;
        int cnt = 0;
        for(int num : nums) {
            if(cnt==0) {
                curr = num;
            }
            cnt += num==curr?1:-1;
        }
        return curr;
    }

    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        int cnt = 1;
        int pre = nums[0];
        int target = nums.length/2;
        for(int i=1;i<nums.length;i++) {
            if(nums[i]==pre) {
                cnt++;
            }else {
                if(cnt>target) {
                    return pre;
                }
                pre = nums[i];
            }
        }
        return pre;
    }

    public int majorityElement2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue()>nums.length/2) {
                return entry.getKey();
            }
        }
        return -1;
    }

}
