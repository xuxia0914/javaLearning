package cn.xux.algorithm.lintcode;

/**
 * 43. 最大子数组 III
 * 中文English
 * 给定一个整数数组和一个整数 k，找出 k 个不重叠子数组使得它们的和最大。
 * 每个子数组的数字在数组中的位置应该是连续的。
 *
 * 返回最大的和。
 *
 * 样例
 * 样例1
 *
 * 输入:
 * List = [1,2,3]
 * k = 1
 * 输出: 6
 * 说明: 1 + 2 + 3 = 6
 * 样例2
 *
 * 输入:
 * List = [-1,4,-2,3,-2,3]
 * k = 2
 * 输出: 8
 * 说明: 4 + (3 + -2 + 3) = 8
 * 注意事项
 * 子数组最少包含一个数
 */
public class Lintcode43 {

    public static void main(String[] args) {
        System.out.println(new Lintcode43().maxSubArray(new int[]{4,5,-2,-4,-5,9,-10,-8,9,4,5,-4}, 5));
    }

    /**
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */
    public int maxSubArray(int[] nums, int k) {
        // write your code here
        if(k>nums.length){
            return 0;
        }
        int[][] local=new int[nums.length+1][k+1];
        int[][] global=new int[nums.length+1][k+1];
        for(int i=1;i<=nums.length;i++){
            local[i][0]=Integer.MIN_VALUE;
            for(int j=1;j<=k;j++){
                if(j>i){
                    local[i][j]=Integer.MIN_VALUE;
                    global[i][j]=Integer.MIN_VALUE;
                    continue;
                }
                local[i][j]=Math.max(local[i-1][j],global[i-1][j-1])+nums[i-1];
                if(i==j){
                    global[i][j]=local[i][j];
                }else{
                    global[i][j]=Math.max(global[i-1][j],local[i][j]);
                }
            }
        }
        return global[nums.length][k];
    }

}
