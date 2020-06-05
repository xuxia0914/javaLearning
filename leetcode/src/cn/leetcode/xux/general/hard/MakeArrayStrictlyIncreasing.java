package cn.leetcode.xux.general.hard;

import java.util.*;

/**
 * 1187. 使数组严格递增
 * 给你两个整数数组 arr1 和 arr2，返回使 arr1 严格递增所需要的最小「操作」数（可能为 0）。
 * 每一步「操作」中，你可以分别从 arr1 和 arr2 中各选出一个索引，
 * 分别为 i 和 j，0 <= i < arr1.length 和 0 <= j < arr2.length，然后进行赋值运算 arr1[i] = arr2[j]。
 * 如果无法让 arr1 严格递增，请返回 -1。
 *
 * 示例 1：
 * 输入：arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
 * 输出：1
 * 解释：用 2 来替换 5，之后 arr1 = [1, 2, 3, 6, 7]。
 *
 * 示例 2：
 * 输入：arr1 = [1,5,3,6,7], arr2 = [4,3,1]
 * 输出：2
 * 解释：用 3 来替换 5，然后用 4 来替换 3，得到 arr1 = [1, 3, 4, 6, 7]。
 *
 *  示例 3：
 * 输入：arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
 * 输出：-1
 * 解释：无法使 arr1 严格递增。
 *
 * 提示：
 * 1 <= arr1.length, arr2.length <= 2000
 * 0 <= arr1[i], arr2[i] <= 10^9
 */
public class MakeArrayStrictlyIncreasing {

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        int len1 = arr1.length;
        Arrays.sort(arr2);
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(arr1[0], 0);
        int min = arr1[0];
        for(int num : arr2) {
            min = Math.min(min, num);
        }
        if(min<arr1[0]) {
            dp.put(min, 1);
        }
        for(int i=1;i<len1;i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for(Map.Entry<Integer, Integer> entry : dp.entrySet()) {
                if(arr1[i]>entry.getKey()) {
                    if(!map.containsKey(arr1[i])||map.get(arr1[i])>entry.getValue()) {
                        map.put(arr1[i], entry.getValue());
                    }
                }
                int changeIdx = findMin(arr2, entry.getKey());
                if(changeIdx!=-1&&(!map.containsKey(arr2[changeIdx])||map.get(arr2[changeIdx])>entry.getValue()+1)) {
                    map.put(arr2[changeIdx], entry.getValue()+1);
                }
            }
            dp = map;
        }
        int result = -1;
        for(Map.Entry<Integer, Integer> entry : dp.entrySet()) {
            if(result==-1||result>entry.getValue()) {
                result = entry.getValue();
            }
        }
        return result;
    }

    public int findMin(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while(left<right) {
            int mid = (left+right)/2;
            if(nums[mid]>target) {
                right = mid;
            }else {
                left = mid+1;
            }
        }
        return nums[left]>target?left:-1;
    }

}
