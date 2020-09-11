package cn.xux.algorithm.leetcode.vip.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1213：三个有序数组的交集
 * 给出三个均为 严格递增排列 的整数数组 arr1，arr2 和 arr3。
 * 返回一个由 仅 在这三个数组中 同时出现 的整数所构成的有序数组。
 *
 * 示例：
 * 输入: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
 * 输出: [1,5]
 * 解释: 只有 1 和 5 同时在这三个数组中出现.
 *
 * 提示：
 * 1 <= arr1.length, arr2.length, arr3.length <= 1000
 * 1 <= arr1[i], arr2[i], arr3[i] <= 2000
 */
public class IntersectionOfThreeSortedArrays {

    public int[] arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<arr1.length;i++) {
            int target = arr1[i];
            int idx2 = Arrays.binarySearch(arr2, target);
            int idx3 = Arrays.binarySearch(arr3, target);
            if(idx2>=0&&idx3>=0) {
                list.add(target);
            }
        }
        int[] ans = new int[list.size()];
        for(int i=0;i<list.size();i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

}
