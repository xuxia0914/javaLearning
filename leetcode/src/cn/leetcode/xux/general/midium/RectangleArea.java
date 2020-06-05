package cn.leetcode.xux.general.midium;

/**
 *Find the total area covered by two rectilinear rectangles in a 2D plane.
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 * Rectangle Area
 * Example:
 * Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2
 * Output: 45
 * Note:
 * Assume that the total area is never beyond the maximum possible value of int.
 */
public class RectangleArea {

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C-A)*(D-B);
        int area2 = (G-E)*(H-F);
        if(C<=E||G<=A||B>=H||F>=D) {
            return area1 + area2;
        }else {
            return area1 + area2 - (Math.min(C, G)-Math.max(A, E))*(Math.min(D, H)-Math.max(B, F));
        }
    }

}
