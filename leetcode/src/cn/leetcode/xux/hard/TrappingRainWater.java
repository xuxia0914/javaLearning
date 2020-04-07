package cn.leetcode.xux.hard;

/**
 * 42.接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 *
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class TrappingRainWater {

    public int trap(int[] height) {
        if(height==null||height.length<3) {
            return 0;
        }
        int len = height.length;
        int maxIndex = 0;
        for(int i=1;i<len;i++) {
            if(height[i]>height[maxIndex]) {
                maxIndex = i;
            }
        }
        int result = 0;
        int leftMax = height[0];
        for(int i=1;i<maxIndex;i++) {
            if(height[i]<leftMax) {
                result += leftMax-height[i];
            }else {
                leftMax = height[i];
            }
        }
        int rightMax = height[len-1];
        for(int i=len-2;i>maxIndex;i--) {
            if(height[i]<rightMax) {
                result += rightMax - height[i];
            }else {
                rightMax = height[i];
            }
        }
        return result;
    }

    int sum = 0;

    public int trap2(int[] height) {
        sum = 0;
        trap2(height, 0, height.length-1);
        return sum;
    }

    private void trap2(int[] height, int start, int end) {
        if(end-start<2) {
            return;
        }
        int index1 = start;
        int index2 = start+1;
        if(height[index1]<height[index2]) {
            int tmp = index1;
            index1 = index2;
            index2 = tmp;
        }
        for(int i=start+2;i<=end;i++) {
            if(height[i]>height[index1]) {
                index2 = index1;
                index1 = i;
            }else if(height[i]>height[index2]) {
                index2 = i;
            }
        }
        for(int i=Math.min(index1, index2)+1;i<=Math.max(index1, index2)-1;i++) {
            sum += height[index2]-height[i];
        }
        trap2(height, start, Math.min(index1, index2));
        trap2(height, Math.max(index1, index2), end);
    }

    public static void main(String[] args) {
        System.out.println(new TrappingRainWater().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));   //6
    }

}
