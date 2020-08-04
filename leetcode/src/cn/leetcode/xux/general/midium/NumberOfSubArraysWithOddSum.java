package cn.leetcode.xux.general.midium;

/**
 * 1524. 和为奇数的子数组数目
 * 给你一个整数数组 arr 。请你返回和为 奇数 的子数组数目。
 * 由于答案可能会很大，请你将结果对 10^9 + 7 取余后返回。
 *
 * 示例 1：
 * 输入：arr = [1,3,5]
 * 输出：4
 * 解释：所有的子数组为 [[1],[1,3],[1,3,5],[3],[3,5],[5]] 。
 * 所有子数组的和为 [1,4,9,3,8,5].
 * 奇数和包括 [1,9,3,5] ，所以答案为 4 。
 *
 * 示例 2 ：
 * 输入：arr = [2,4,6]
 * 输出：0
 * 解释：所有子数组为 [[2],[2,4],[2,4,6],[4],[4,6],[6]] 。
 * 所有子数组和为 [2,6,12,4,10,6] 。
 * 所有子数组和都是偶数，所以答案为 0 。
 *
 * 示例 3：
 * 输入：arr = [1,2,3,4,5,6,7]
 * 输出：16
 *
 * 示例 4：
 * 输入：arr = [100,100,99,99]
 * 输出：4
 *
 * 示例 5：
 * 输入：arr = [7]
 * 输出：1
 *
 * 提示：
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 100
 */
public class NumberOfSubArraysWithOddSum {

    public static void main(String[] args) {
        System.out.println(new NumberOfSubArraysWithOddSum().numOfSubarrays(new int[]{1,3,5}));
    }

    public int numOfSubarrays(int[] arr) {
        int cntOdd = 0; //当前奇数个数
        int preOdd = 0; //前缀子数组奇数个数为奇数的子数组个数
        int preEven = 1;    //前缀子数组奇数个数为偶数的子数组个数
        int ans = 0;
        for(int num : arr) {
            if(num%2==1) {
                cntOdd++;
            }
            if(cntOdd%2==1) {
                ans = (ans+preEven)%1000000007;
                preOdd++;
            }else {
                ans = (ans+preOdd)%1000000007;
                preEven++;
            }
        }
        return ans;
    }

}
