package cn.xux.algorithm.leetcode.general.hard;

import java.util.Arrays;

/**
 * 1439. 有序矩阵中的第 k 个最小数组和
 * 给你一个 m * n 的矩阵 mat，以及一个整数 k ，矩阵中的每一行都以非递减的顺序排列。
 * 你可以从每一行中选出 1 个元素形成一个数组。返回所有可能数组中的第 k 个 最小 数组和。
 *
 * 示例 1：
 * 输入：mat = [[1,3,11],[2,4,6]], k = 5
 * 输出：7
 * 解释：从每一行中选出一个元素，前 k 个和最小的数组分别是：
 * [1,2], [1,4], [3,2], [3,4], [1,6]。其中第 5 个的和是 7 。
 *
 * 示例 2：
 * 输入：mat = [[1,3,11],[2,4,6]], k = 9
 * 输出：17
 *
 * 示例 3：
 * 输入：mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
 * 输出：9
 * 解释：从每一行中选出一个元素，前 k 个和最小的数组分别是：
 * [1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]。其中第 7 个的和是 9 。
 *
 * 示例 4：
 * 输入：mat = [[1,1,10],[2,2,9]], k = 7
 * 输出：12
 *
 * 提示：
 * m == mat.length
 * n == mat.length[i]
 * 1 <= m, n <= 40
 * 1 <= k <= min(200, n ^ m)
 * 1 <= mat[i][j] <= 5000
 * mat[i] 是一个非递减数组
 */
public class FindTheKthSmallestSumOfAMatrixWithSortedRows {

    public static void main(String[] args) {
        FindTheKthSmallestSumOfAMatrixWithSortedRows ft = new FindTheKthSmallestSumOfAMatrixWithSortedRows();
        System.out.println(ft.kthSmallest(new int[][]{{1,3,11},{2,4,6}}, 5));
        System.out.println(ft.kthSmallest(new int[][]{{1,3,11},{2,4,6}}, 9));
    }

    public int kthSmallest(int[][] mat, int k) {
        int left = 0;
        int right = 0;
        for (int[] arr : mat) {
            left += arr[0];
            right += arr[arr.length - 1];
        }
        int min = left;
        while(left<right) {
            int mid = (left+right)/2;
            cnt = 1;
            dfs(mat, mid, 0, min, k);
            if(cnt>=k) {
                right = mid;
            }else {
                left = mid+1;
            }
        }
        return left;
    }

    int cnt;

    // 计算数组和小于tar的数组个数(大于k是计算终止)
    // js[i]表示目前数组的第i行选中的数是mat[i]的第j个数
    private void dfs(int[][] mat,int mid, int i, int sum, int k) {
        if(sum>mid||i==mat.length||cnt>=k) {
            return;
        }
        dfs(mat, mid, i+1, sum, k);
        for(int j=1;j<mat[0].length;j++) {
            if(sum+mat[i][j]-mat[i][0]<=mid) {
                cnt++;
                dfs(mat, mid, i+1, sum+mat[i][j]-mat[i][0], k);
            }else {
                break;
            }
        }
    }

}
