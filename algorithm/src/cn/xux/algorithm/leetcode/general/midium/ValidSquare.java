package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * Given the coordinates of four points in 2D space, return whether the four points could construct a square.
 * The coordinate (x,y) of a point is represented by an integer array with two integers.
 * Example:
 * Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * Output: True
 * Note:
 * All the input integers are in the range [-10000, 10000].
 * A valid square has four equal sides with positive length and four equal angles (90-degree angles).
 * Input points have no order.
 */
public class ValidSquare {

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        if(Arrays.equals(p1, p2)
                ||Arrays.equals(p1, p3)
                ||Arrays.equals(p1, p4)
                ||Arrays.equals(p2, p3)
                ||Arrays.equals(p2, p4)
                ||Arrays.equals(p3, p4)) {
            return false;
        }
        int d12 = (p1[0]-p2[0])*(p1[0]-p2[0]) + (p1[1]-p2[1])*(p1[1]-p2[1]);
        int d13 = (p3[0]-p1[0])*(p3[0]-p1[0]) + (p3[1]-p1[1])*(p3[1]-p1[1]);
        int d14 = (p1[0]-p4[0])*(p1[0]-p4[0]) + (p1[1]-p4[1])*(p1[1]-p4[1]);
        int d23 = (p3[0]-p2[0])*(p3[0]-p2[0]) + (p3[1]-p2[1])*(p3[1]-p2[1]);
        int d24 = (p2[0]-p4[0])*(p2[0]-p4[0]) + (p2[1]-p4[1])*(p2[1]-p4[1]);
        int d34 = (p3[0]-p4[0])*(p3[0]-p4[0]) + (p3[1]-p4[1])*(p3[1]-p4[1]);

        int[] ds = new int[]{d12,d13,d14,d23,d24,d34};
        Arrays.sort(ds);
        if(ds[0]==ds[1]
                &&ds[0]==ds[2]
                &&ds[0]==ds[3]
                &&2*ds[0]==ds[4]
                &&ds[4]==ds[5]) {
            return true;
        }
        return false;
    }

}
