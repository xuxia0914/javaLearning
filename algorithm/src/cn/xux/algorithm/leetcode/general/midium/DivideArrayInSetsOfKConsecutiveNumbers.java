package cn.xux.algorithm.leetcode.general.midium;

import java.util.*;

/**
 * 1296. 划分数组为连续数字的集合
 * 给你一个整数数组 nums 和一个正整数 k，请你判断是否可以把这个数组划分成一些由 k 个连续数字组成的集合。
 * 如果可以，请返回 True；否则，返回 False。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,3,4,4,5,6], k = 4
 * 输出：true
 * 解释：数组可以分成 [1,2,3,4] 和 [3,4,5,6]。
 *
 * 示例 2：
 * 输入：nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
 * 输出：true
 * 解释：数组可以分成 [1,2,3] , [2,3,4] , [3,4,5] 和 [9,10,11]。
 *
 * 示例 3：
 * 输入：nums = [3,3,2,2,1,1], k = 3
 * 输出：true
 *
 * 示例 4：
 * 输入：nums = [1,2,3,4], k = 3
 * 输出：false
 * 解释：数组不能分成几个大小为 3 的子数组。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= nums.length
 */
public class DivideArrayInSetsOfKConsecutiveNumbers {

    public static void main(String[] args) {
        DivideArrayInSetsOfKConsecutiveNumbers d = new DivideArrayInSetsOfKConsecutiveNumbers();
        System.out.println(d.isPossibleDivide(new int[]{1,2,3,3,4,4,5,6}, 4));
    }

    public boolean isPossibleDivide(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        while(map.size()>0) {
            int start = map.firstKey();
            for(int i=0;i<k;i++) {
                int target = start+i;
                int cnt = map.getOrDefault(target, 0);
                if(cnt==0) {
                    return false;
                }else if(cnt==1) {
                    map.remove(target);
                }else {
                    map.put(target, cnt-1);
                }
            }
        }
        return true;
    }

    public boolean isPossibleDivide1(int[] nums, int k) {
        int len = nums.length;
        if(len%k!=0) {
            return false;
        }
        if(k==1) {
            return true;
        }
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        Arrays.sort(nums);
        for(int num : nums) {
            if(map.containsKey(num)) {
                Queue<Integer> queue = map.get(num);
                int start = queue.poll();
                if(queue.size()==0) {
                    map.remove(num);
                }
                if(num-start+1<k) {
                    if(!map.containsKey(num+1)) {
                        map.put(num+1, new LinkedList<>());
                    }
                    map.get(num+1).offer(start);
                }
            }else {
                if(!map.containsKey(num+1)) {
                    map.put(num+1, new LinkedList<>());
                }
                map.get(num+1).offer(num);
            }
        }
        return map.size()==0;
    }

}
