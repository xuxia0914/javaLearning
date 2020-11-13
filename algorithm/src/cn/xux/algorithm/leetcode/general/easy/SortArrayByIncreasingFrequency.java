package cn.xux.algorithm.leetcode.general.easy;

import java.util.*;

/**
 * 1636. 按照频率将数组升序排序
 * 给你一个整数数组 nums ，请你将数组按照每个值的频率 升序 排序。
 * 如果有多个值的频率相同，请你按照数值本身将它们 降序 排序。
 * 请你返回排序后的数组。
 *
 * 示例 1：
 * 输入：nums = [1,1,2,2,2,3]
 * 输出：[3,1,1,2,2,2]
 * 解释：'3' 频率为 1，'1' 频率为 2，'2' 频率为 3 。
 *
 * 示例 2：
 * 输入：nums = [2,3,1,3,2]
 * 输出：[1,3,3,2,2]
 * 解释：'2' 和 '3' 频率都为 2 ，所以它们之间按照数值本身降序排序。
 *
 * 示例 3：
 * 输入：nums = [-1,1,-6,4,5,-6,1,4,1]
 * 输出：[5,-1,4,4,-6,-6,1,1,1]
 *
 * 提示：
 * 1 <= nums.length <= 100
 * -100 <= nums[i] <= 100
 */
public class SortArrayByIncreasingFrequency {

    public int[] frequencySort(int[] nums) {
        int[] cnts = new int[201];
        // 统计每个数(+100)的个数
        for (int n : nums){
            cnts[n + 100] ++;
        }
        // 转换每个数
        for (int i = 0; i < nums.length; i ++){
            nums[i] =
                    // 使得个数多的元素转换后的值必然大于个数少的元素
                    10000 * cnts[nums[i] + 100]
                    // 原数取反，使得个数相同的元素转换后小的元素大于大的元素
                    - nums[i] + 100;
        }
        // 转换后的数组从小到大排序
        Arrays.sort(nums);

        // 还原每个数
        for (int i = 0; i < nums.length; i ++){
            nums[i] = 100 - nums[i] % 10000 ;
        }

        return nums;
    }

    //常规解法
    public int[] frequencySort1(int[] nums) {
        Map<Integer, Integer> map1 = new HashMap<>();
        for(int num : nums) {
            map1.put(num, map1.getOrDefault(num, 0)+1);
        }
        TreeMap<Integer, List<Integer>> map2 = new TreeMap<>();
        for(Map.Entry<Integer, Integer> entry : map1.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if(!map2.containsKey(value)) {
                map2.put(value, new ArrayList<>());
            }
            map2.get(value).add(key);
        }
        int[] ans = new int[nums.length];
        int idx = 0;
        while(map2.size()>0) {
            Map.Entry<Integer, List<Integer>> entry = map2.pollFirstEntry();
            int key = entry.getKey();
            List<Integer> value = entry.getValue();
            value.sort((o1, o2) -> o2 - o1);
            for(int v : value) {
                int tmp = key;
                while(tmp-->0) {
                    ans[idx++] = v;
                }
            }
        }
        return ans;
    }

}
