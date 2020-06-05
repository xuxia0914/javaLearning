package cn.leetcode.xux.general.hard;

import java.util.Stack;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 *
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 *
 * 示例:
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 */
public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {
        int result = 0;
        if(heights==null||heights.length==0) {
            return result;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for(int i=0;i<heights.length;i++) {
            if(stack.peek()==-1||heights[i]>=heights[stack.peek()]) {
                stack.push(i);
                continue;
            }
            while(stack.peek()!=-1&&heights[i]<heights[stack.peek()]) {
                result = Math.max(result, heights[stack.pop()]*(i-stack.peek()-1));
            }
            stack.push(i);
        }
        while(stack.peek()!=-1) {
            result = Math.max(result, heights[stack.pop()]*(heights.length-stack.peek()-1));
        }
        return result;
    }

    int result;

    public int largestRectangleArea1(int[] heights) {
        result = 0;
        largestRectangleArea1(heights, 0, heights.length-1);
        return result;
    }

    public void largestRectangleArea1(int[] heights, int start, int end) {
        if(start>end) {
            return;
        }
        int minIndex = start;
        for(int i=start+1;i<=end;i++) {
            if(heights[i]<heights[minIndex]) {
                minIndex = i;
            }
        }
        int curr = heights[minIndex]*(end-start+1);
        result = Math.max(result, curr);
        largestRectangleArea1(heights, start, minIndex-1);
        largestRectangleArea1(heights, minIndex+1, end);
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram l = new LargestRectangleInHistogram();
        System.out.println(l.largestRectangleArea(new int[]{2,1,5,6,2,3}));
        System.out.println(l.largestRectangleArea(new int[]{1}));
        System.out.println(l.largestRectangleArea(new int[]{0,9}));
        System.out.println(l.largestRectangleArea(new int[]{2,1,2}));
    }

}
