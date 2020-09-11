package cn.xux.algorithm.leetcode.vip.easy;

/**
 * 1228：等差数列中缺失的数字
 * 有一个数组，其中的值符合等差数列的数值规律，也就是说：
 * 在 0 <= i < arr.length - 1 的前提下，arr[i+1] - arr[i] 的值都相等。
 * 我们会从该数组中删除一个 既不是第一个 也 不是最后一个的值，得到一个新的数组 arr。
 * 给你这个缺值的数组 arr，请你帮忙找出被删除的那个数。
 *
 * 示例 1：
 * 输入：arr = [5,7,11,13]
 * 输出：9
 * 解释：原来的数组是 [5,7,9,11,13]。
 *
 * 示例 2：
 * 输入：arr = [15,13,12]
 * 输出：14
 * 解释：原来的数组是 [15,14,13,12]。
 *
 * 提示：
 * 3 <= arr.length <= 1000
 * 0 <= arr[i] <= 10^5
 */
public class MissingNumberInArithmeticProgression {

    public int missingNumber(int[] arr) {
        int n = arr.length;
        int d = (arr[n-1]-arr[0])/n;
        int left = 0;
        int right = n-1;
        while(left<right) {
            int mid = (left+right+1)/2;
            if(arr[left]+(mid-left)*d==arr[mid]) {
                left = mid;
            }else {
                right = mid-1;
            }
        }
        return arr[left]+d;
    }

}
