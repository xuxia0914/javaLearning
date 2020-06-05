package cn.leetcode.xux.general.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1200. 最小绝对差
 * 给你个整数数组 arr，其中每个元素都 不相同。
 * 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
 *
 * 示例 1：
 * 输入：arr = [4,2,1,3]
 * 输出：[[1,2],[2,3],[3,4]]
 *
 * 示例 2：
 * 输入：arr = [1,3,6,10,15]
 * 输出：[[1,3]]
 *
 * 示例 3：
 * 输入：arr = [3,8,-10,23,19,-4,-14,27]
 * 输出：[[-14,-10],[19,23],[23,27]]
 *
 * 提示：
 * 2 <= arr.length <= 10^5
 * -10^6 <= arr[i] <= 10^6
 */
public class MinimumAbsoluteDifference {

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
        List<Integer> list = new ArrayList<>();
        list.add(arr[0]);
        list.add(arr[1]);
        int min = arr[1]-arr[0];
        result.add(list);
        for(int i=2;i<arr.length;i++) {
            int curr = arr[i]-arr[i-1];
            if(curr<=min) {
                if(curr<min) {
                    result.clear();
                    min = curr;
                }
                List<Integer> tmp = new ArrayList<>();
                tmp.add(arr[i-1]);
                tmp.add(arr[i]);
                result.add(tmp);
            }
        }
        return result;
    }

}
