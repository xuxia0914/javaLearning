package cn.leetcode.xux.general.midium;

/**
 * 1131. 绝对值表达式的最大值
 * 给你两个长度相等的整数数组，返回下面表达式的最大值：
 * |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
 * 其中下标 i，j 满足 0 <= i, j < arr1.length。
 *
 * 示例 1：
 * 输入：arr1 = [1,2,3,4], arr2 = [-1,4,5,6]
 * 输出：13
 *
 * 示例 2：
 * 输入：arr1 = [1,-2,-5,0,10], arr2 = [0,-2,-1,-7,-4]
 * 输出：20
 *
 * 提示：
 * 2 <= arr1.length == arr2.length <= 40000
 * -10^6 <= arr1[i], arr2[i] <= 10^6
 */
public class MaximumOfAbsoluteValueExpression {

    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int len = arr1.length;
        int min1 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max2 = Integer.MIN_VALUE;
        int min3 = Integer.MAX_VALUE;
        int max3 = Integer.MIN_VALUE;
        int min4 = Integer.MAX_VALUE;
        int max4 = Integer.MIN_VALUE;
        for(int i=0;i<len;i++) {
            int curr1 = arr1[i]+arr2[i]+i;
            min1 = Math.min(min1, curr1);
            max1 = Math.max(max1, curr1);
            int curr2 = arr1[i]+arr2[i]-i;
            min2 = Math.min(min2, curr2);
            max2 = Math.max(max2, curr2);
            int curr3 = arr1[i]-arr2[i]+i;
            min3 = Math.min(min3, curr3);
            max3 = Math.max(max3, curr3);
            int curr4 = arr1[i]-arr2[i]-i;
            min4 = Math.min(min4, curr4);
            max4 = Math.max(max4, curr4);
        }
        return Math.max(Math.max(max1-min1, max2-min2), Math.max(max3-min3, max4-min4));
    }

}
