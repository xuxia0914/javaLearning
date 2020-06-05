package cn.leetcode.xux.general.easy;

import java.util.Arrays;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai)
 * 画 n 条垂直线，使得垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 注意：你不能倾斜容器，n 至少是2。
 */

public class MostWater {

    public static int[] solusion(int[] ia) {
        int a=0, b=0, max=0;
        for(int i=0;i<ia.length-1;i++) {
            for(int j=i+1;j<ia.length;j++) {
                if((j-i)*Math.min(ia[i], ia[j])>max) {
                    max = (j-i)*Math.min(ia[i], ia[j]);
                    a = i;
                    b = j;
                }
            }

        }
        return new int[]{a, b, max};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solusion(new int[]{9, 5, 3, 1})));
    }

}
