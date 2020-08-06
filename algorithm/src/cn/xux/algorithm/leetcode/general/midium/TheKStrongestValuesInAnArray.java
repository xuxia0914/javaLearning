package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1471. 数组中的 k 个最强值
 * 给你一个整数数组 arr 和一个整数 k 。
 * 设 m 为数组的中位数，只要满足下述两个前提之一，就可以判定 arr[i] 的值比 arr[j] 的值更强：
 *  |arr[i] - m| > |arr[j] - m|
 *  |arr[i] - m| == |arr[j] - m|，且 arr[i] > arr[j]
 * 请返回由数组中最强的 k 个值组成的列表。答案可以以 任意顺序 返回。
 * 中位数 是一个有序整数列表中处于中间位置的值。形式上，如果列表的长度为 n ，
 * 那么中位数就是该有序列表（下标从 0 开始）中位于 ((n - 1) / 2) 的元素。
 * 例如 arr = [6, -3, 7, 2, 11]，n = 5：数组排序后得到 arr = [-3, 2, 6, 7, 11] ，
 * 数组的中间位置为 m = ((5 - 1) / 2) = 2 ，中位数 arr[m] 的值为 6 。
 * 例如 arr = [-7, 22, 17, 3]，n = 4：数组排序后得到 arr = [-7, 3, 17, 22] ，
 * 数组的中间位置为 m = ((4 - 1) / 2) = 1 ，中位数 arr[m] 的值为 3 。
 *
 * 示例 1：
 * 输入：arr = [1,2,3,4,5], k = 2
 * 输出：[5,1]
 * 解释：中位数为 3，按从强到弱顺序排序后，数组变为 [5,1,4,2,3]。最强的两个元素是 [5, 1]。[1, 5] 也是正确答案。
 * 注意，尽管 |5 - 3| == |1 - 3| ，但是 5 比 1 更强，因为 5 > 1 。
 *
 * 示例 2：
 * 输入：arr = [1,1,3,5,5], k = 2
 * 输出：[5,5]
 * 解释：中位数为 3, 按从强到弱顺序排序后，数组变为 [5,5,1,1,3]。最强的两个元素是 [5, 5]。
 *
 * 示例 3：
 * 输入：arr = [6,7,11,7,6,8], k = 5
 * 输出：[11,8,6,6,7]
 * 解释：中位数为 7, 按从强到弱顺序排序后，数组变为 [11,8,6,6,7,7]。
 * [11,8,6,6,7] 的任何排列都是正确答案。
 *
 * 示例 4：
 * 输入：arr = [6,-3,7,2,11], k = 3
 * 输出：[-3,11,2]
 *
 * 示例 5：
 * 输入：arr = [-7,22,17,3], k = 2
 * 输出：[22,17]
 *
 * 提示：
 * 1 <= arr.length <= 10^5
 * -10^5 <= arr[i] <= 10^5
 * 1 <= k <= arr.length
 */
public class TheKStrongestValuesInAnArray {

    public int[] getStrongest(int[] arr, int k) {
        if(k==arr.length) {
            return arr;
        }
        int n = arr.length;
        int midIdx = (n-1)/2;
        int midVal = quickSelect(arr, 0, n-1, midIdx);
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>((o1, o2)->
                Math.abs(o1-midVal)==Math.abs(o2-midVal)?o1-o2:Math.abs(o1-midVal)-Math.abs(o2-midVal));
        for(int num : arr) {
            queue.offer(num);
            if(queue.size()>k) {
                queue.poll();
            }
        }
        int[] ans = new int[k];
        for(int i=0;i<k;i++) {
            ans[i] = queue.poll();
        }
        return ans;
    }

    private int quickSelect(int[] arr, int start, int end, int target) {
        if(start>=end) {
            return arr[start];
        }
        int left = start;
        int right = end;
        int key = arr[start];
        while(left<right) {
            while(left<right&&arr[right]>=arr[key]) {
                right--;
            }
            while(left<right&&arr[left]<=arr[key]) {
                left++;
            }
            if(left<right) {
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
            }
        }
        if(right==target) {
            return key;
        }
        arr[start] = arr[right];
        arr[right] = key;
        if(right>target) {
            return quickSelect(arr, start, right-1, target);
        }else {
            return quickSelect(arr, right+1, end, target);
        }
    }

    public int[] getStrongest1(int[] arr, int k) {
        if(k==arr.length) {
            return arr;
        }
        Arrays.sort(arr);
        int n = arr.length;
        int mid = arr[(n-1)/2];
        int left = 0;
        int right = n-1;
        int[] ans = new int[k];
        for(int i=0;i<k;i++) {
            if(arr[right]-mid>=mid-arr[left]) {
                ans[i] = arr[right--];
            }else {
                ans[i] = arr[left++];
            }
        }
        return ans;
    }

}
