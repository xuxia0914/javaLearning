package cn.leetcode.xux.hard;

/**
 *Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 * Thanks Marcos for contributing this image!
 */
public class TrappingRainWater {

    int res = 0;

    public int trap(int[] height) {
        if(height==null||height.length<3) {
            return 0;
        }
        helper(height, 0, height.length-1);
        return res;
    }

    public void helper(int[] height, int start, int end) {
        if(start+1>=end) {
            return;
        }
        int[] idxs = findHighestTwo(height, start, end);
        for(int i=Math.min(idxs[0],idxs[1])+1;i<Math.max(idxs[0],idxs[1]);i++) {
            res += height[idxs[0]] - height[i];
        }
        helper(height, start, Math.min(idxs[0],idxs[1]));
        helper(height, Math.max(idxs[0],idxs[1]), end);
    }

    //找出最高的两个柱子的idx
    public int[] findHighestTwo(int[] height, int start, int end) {
        if(start+1>=end) {
            return new int[]{start, end};
        }
        int idx0 = start;
        int idx1 = start+1;
        if(height[idx0]>height[idx1]) {
            int tmp = idx0;
            idx0 = idx1;
            idx1 = tmp;
        }
        for(int i=start+1;i<=end;i++) {
            if(height[i]>height[idx0]) {
                idx0 = i;
            }
            if(height[idx0]>height[idx1]) {
                int tmp = idx0;
                idx0 = idx1;
                idx1 = tmp;
            }
        }
        return new int[]{idx0, idx1};
    }

    public static void main(String[] args) {
        System.out.println(new TrappingRainWater().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));   //6
    }

}
