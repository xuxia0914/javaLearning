package cn.xux.algorithm.leetcode.general.midium;

import java.util.Stack;

/**
 * 962. 最大宽度坡
 * 给定一个整数数组 A，坡是元组 (i, j)，其中  i < j 且 A[i] <= A[j]。这样的坡的宽度为 j - i。
 * 找出 A 中的坡的最大宽度，如果不存在，返回 0 。
 *
 * 示例 1：
 * 输入：[6,0,8,2,1,5]
 * 输出：4
 * 解释：
 * 最大宽度的坡为 (i, j) = (1, 5): A[1] = 0 且 A[5] = 5.
 *
 * 示例 2：
 * 输入：[9,8,1,0,1,9,4,0,4,1]
 * 输出：7
 * 解释：
 * 最大宽度的坡为 (i, j) = (2, 9): A[2] = 1 且 A[9] = 1.
 *
 * 提示：
 * 2 <= A.length <= 50000
 * 0 <= A[i] <= 50000
 */
public class MaximumWidthRamp {

    public static void main(String[] args) {
        System.out.println(new MaximumWidthRamp().maxWidthRamp(new int[]{6,0,8,2,1,5}));
    }

    public int maxWidthRamp(int[] A) {
        Stack<Integer> stack = new Stack<>();
        int len = A.length;
        for(int i=0;i<len;i++) {
            if(stack.isEmpty()||A[stack.peek()]>A[i]) {
                stack.push(i);
            }
        }
        int i=len-1;
        int result = 0;
        while(i>result&&!stack.isEmpty()&&i>stack.peek()) {
            while(!stack.isEmpty()&&A[i]>=A[stack.peek()]) {
                result = Math.max(i-stack.pop(), result);
            }
            i--;
        }
        return result;
    }

}
