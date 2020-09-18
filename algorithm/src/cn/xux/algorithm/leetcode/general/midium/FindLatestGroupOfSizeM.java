package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1562. 查找大小为 M 的最新分组
 * 给你一个数组 arr ，该数组表示一个从 1 到 n 的数字排列。
 * 有一个长度为 n 的二进制字符串，该字符串上的所有位最初都设置为 0 。
 * 在从 1 到 n 的每个步骤 i 中（假设二进制字符串和 arr 都是从 1 开始索引的情况下），
 * 二进制字符串上位于位置 arr[i] 的位将会设为 1 。
 * 给你一个整数 m ，请你找出二进制字符串上存在长度为 m 的一组 1 的最后步骤。
 * 一组 1 是一个连续的、由 1 组成的子串，且左右两边不再有可以延伸的 1 。
 * 返回存在长度 恰好 为 m 的 一组 1  的最后步骤。如果不存在这样的步骤，请返回 -1 。
 *
 * 示例 1：
 * 输入：arr = [3,5,1,2,4], m = 1
 * 输出：4
 * 解释：
 * 步骤 1："00100"，由 1 构成的组：["1"]
 * 步骤 2："00101"，由 1 构成的组：["1", "1"]
 * 步骤 3："10101"，由 1 构成的组：["1", "1", "1"]
 * 步骤 4："11101"，由 1 构成的组：["111", "1"]
 * 步骤 5："11111"，由 1 构成的组：["11111"]
 * 存在长度为 1 的一组 1 的最后步骤是步骤 4 。
 *
 * 示例 2：
 * 输入：arr = [3,1,5,4,2], m = 2
 * 输出：-1
 * 解释：
 * 步骤 1："00100"，由 1 构成的组：["1"]
 * 步骤 2："10100"，由 1 构成的组：["1", "1"]
 * 步骤 3："10101"，由 1 构成的组：["1", "1", "1"]
 * 步骤 4："10111"，由 1 构成的组：["1", "111"]
 * 步骤 5："11111"，由 1 构成的组：["11111"]
 * 不管是哪一步骤都无法形成长度为 2 的一组 1 。
 *
 * 示例 3：
 * 输入：arr = [1], m = 1
 * 输出：1
 *
 * 示例 4：
 * 输入：arr = [2,1], m = 2
 * 输出：2
 *
 * 提示：
 * n == arr.length
 * 1 <= n <= 10^5
 * 1 <= arr[i] <= n
 * arr 中的所有整数 互不相同
 * 1 <= m <= arr.length
 */
public class FindLatestGroupOfSizeM {

    public static void main(String[] args) {
        FindLatestGroupOfSizeM fl = new FindLatestGroupOfSizeM();
        System.out.println(fl.findLatestStep(new int[]{3,2,5,6,10,8,9,4,1,7}, 3));
    }

    public int findLatestStep(int[] arr, int m) {
        int ans = -1;
        // 连续1的个数为m的子数组的个数
        int cnt = 0;
        // left[i]表示从第i个数开始往左数连续1的个数
        int[] left = new int[arr.length+2];
        // left[i]表示从第i个数开始往右数连续1的个数
        int[] right = new int[arr.length+2];
        for(int i=0;i<arr.length;i++) {
            left[arr[i]] = 1+left[arr[i]-1];
            right[arr[i]] = 1+right[arr[i]+1];
            // 需要更新当前连续1的子数组的左边界的向右连续1的个数和右边界的向左连续1的个数
            // 其他的不用更新因为不会再使用到了
            right[arr[i]-left[arr[i]]+1] = right[arr[i]]+left[arr[i]]-1;
            left[arr[i]+right[arr[i]]-1] = right[arr[i]]+left[arr[i]]-1;
            if(right[arr[i]]+left[arr[i]]-1==m) {
                cnt++;
            }
            if(left[arr[i]-1]==m) {
                cnt--;
            }
            if(right[arr[i]+1]==m) {
                cnt--;
            }
            if(cnt>0) {
                ans = i+1;
            }
        }
        return ans;
    }

}
