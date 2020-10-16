package cn.xux.algorithm.leetcode.general.easy;

/**
 * 977. 有序数组的平方
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 *
 * 示例 1：
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 *
 * 示例 2：
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *
 * 提示：
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 */
public class SquaresOfASortedArray {

    public int[] sortedSquares(int[] A) {
        int len = A.length;
        int[] ans = new int[len];
        int idx = len-1;
        int left = 0;
        int right = len-1;
        while(left<=right) {
            if(Math.abs(A[left])>=Math.abs(A[right])) {
                ans[idx--] = A[left]*A[left++];
            }else {
                ans[idx--] = A[right]*A[right--];
            }
        }
        return ans;
    }

}
