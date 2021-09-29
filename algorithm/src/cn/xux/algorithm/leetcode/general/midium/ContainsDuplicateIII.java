package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 220. 存在重复元素 III
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 *
 * 如果存在则返回 true，不存在返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1], k = 3, t = 0
 * 输出：true
 * 示例 2：
 *
 * 输入：nums = [1,0,1,1], k = 1, t = 2
 * 输出：true
 * 示例 3：
 *
 * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出：false
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 104
 * 0 <= t <= 231 - 1
 */
public class ContainsDuplicateIII {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(t<0) {
            return false;
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            //查询大于nums[i]的最小元素
            Integer s = set.ceiling(nums[i]);
            if (s != null && s <= (long)nums[i] + t) return true;
            //查询小于nums[i]的最大元素
            Integer g = set.floor(nums[i]);
            if (g != null && nums[i] <= (long)g + t) return true;

            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        if(t<0) {
            return false;
        }
        Map<Long, Long> map = new HashMap<>();
        for(int i=0; i<nums.length;++i) {
            int num = nums[i];
            long key = getKey((long)num, (long)(t+1));
            if(map.containsKey(key)) {
                return true;
            }
            if(map.containsKey(key-1)&&(long)num-map.get(key-1)<=t) {
                return true;
            }
            if(map.containsKey(key+1)&&map.get(key+1)-(long)num<=t) {
                return true;
            }
            map.put(key, (long)num);
            if(i>=k) {
                map.remove(getKey(nums[i-k], (long)(t+1)));
            }
        }
        return false;
    }

    public long getKey(long num, long cap) {
        return num<0?num+1/cap-1:num/cap;
    }

}
