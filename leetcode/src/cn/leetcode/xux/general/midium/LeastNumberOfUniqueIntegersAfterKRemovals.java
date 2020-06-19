package cn.leetcode.xux.general.midium;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 1481. 不同整数的最少数目
 * 给你一个整数数组 arr 和一个整数 k 。
 * 现需要从数组中恰好移除 k 个元素，请找出移除后数组中不同整数的最少数目。
 *
 * 示例 1：
 * 输入：arr = [5,5,4], k = 1
 * 输出：1
 * 解释：移除 1 个 4 ，数组中只剩下 5 一种整数。
 *
 * 示例 2：
 * 输入：arr = [4,3,1,1,3,3,2], k = 3
 * 输出：2
 * 解释：先移除 4、2 ，然后再移除两个 1 中的任意 1 个或者三个 3 中的任意 1 个，最后剩下 1 和 3 两种整数。
 *
 * 提示：
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^9
 * 0 <= k <= arr.length
 */
public class LeastNumberOfUniqueIntegersAfterKRemovals {

    public static void main(String[] args) {
        System.out.println(new LeastNumberOfUniqueIntegersAfterKRemovals()
                .findLeastNumOfUniqueInts(new int[]{4,3,1,1,3,3,2}, 3));
    }

    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> cnts = new HashMap<>();
        for(int num : arr) {
            cnts.put(num, cnts.getOrDefault(num, 0)+1);
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(Map.Entry<Integer, Integer> entry : cnts.entrySet()) {
            map.put(entry.getValue(), map.getOrDefault(entry.getValue(), 0)+1);
        }
        int ans = 0;
        while(map.size()>0) {
            Map.Entry<Integer, Integer> entry = map.pollFirstEntry();
            int key = entry.getKey();
            int val = entry.getValue();
            if(k==0) {
                ans += val;
            }else if(k>=key*val) {
                k -= key*val;
            }else {
                val -= k/key;
                ans += val;
                k = 0;
            }
        }
        return ans;
    }

}
