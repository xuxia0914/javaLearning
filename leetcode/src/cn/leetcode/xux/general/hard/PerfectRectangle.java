package cn.leetcode.xux.general.hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 391. 完美矩形
 * 我们有 N 个与坐标轴对齐的矩形, 其中 N > 0,
 * 判断它们是否能精确地覆盖一个矩形区域。
 * 每个矩形用左下角的点和右上角的点的坐标来表示。
 * 例如， 一个单位正方形可以表示为 [1,1,2,2]。
 * ( 左下角的点的坐标为 (1, 1) 以及右上角的点的坐标为 (2, 2) )。
 *
 * 示例 1:
 * rectangles = [
 *   [1,1,3,3],
 *   [3,1,4,2],
 *   [3,2,4,4],
 *   [1,3,2,4],
 *   [2,3,3,4]
 * ]
 * 返回 true。5个矩形一起可以精确地覆盖一个矩形区域。
 *
 * 示例 2:
 * rectangles = [
 *   [1,1,2,3],
 *   [1,3,2,4],
 *   [3,1,4,2],
 *   [3,2,4,4]
 * ]
 * 返回 false。两个矩形之间有间隔，无法覆盖成一个矩形。
 *
 * 示例 3:
 * rectangles = [
 *   [1,1,3,3],
 *   [3,1,4,2],
 *   [1,3,2,4],
 *   [3,2,4,4]
 * ]
 * 返回 false。图形顶端留有间隔，无法覆盖成一个矩形。
 *
 * 示例 4:
 * rectangles = [
 *   [1,1,3,3],
 *   [3,1,4,2],
 *   [1,3,2,4],
 *   [2,2,4,4]
 * ]
 * 返回 false。因为中间有相交区域，虽然形成了矩形，但不是精确覆盖。
 */
public class PerfectRectangle {

    public static void main(String[] args) {
        /*System.out.println(new PerfectRectangle().isRectangleCover(
                new int[][]{
                        {1,1,3,3},
                        {3,1,4,2},
                        {3,2,4,4},
                        {1,3,2,4},
                        {2,3,3,4}
                }
        ));*/
        System.out.println(new PerfectRectangle().isRectangleCover(
                    new int[][]{{1,1,2,2},{1,1,2,2},{2,1,3,2}}
            ));
        }

    public boolean isRectangleCover(int[][] rectangles) {
        if(rectangles==null||rectangles.length==0) {
            return false;
        }
        int n = rectangles.length;
        if(n==1) {
            return true;
        }
        int area = 0;
        Set<String> set = new HashSet<>();
        for(int[] rectangle : rectangles) {
            String point1 = rectangle[0]+"#"+rectangle[1];
            String point2 = rectangle[0]+"#"+rectangle[3];
            String point3 = rectangle[2]+"#"+rectangle[1];
            String point4 = rectangle[2]+"#"+rectangle[3];
            if(!set.add(point1)) set.remove(point1);
            if(!set.add(point2)) set.remove(point2);
            if(!set.add(point3)) set.remove(point3);
            if(!set.add(point4)) set.remove(point4);
            area += (rectangle[2]-rectangle[0])*(rectangle[3]-rectangle[1]);
        }
        if(set.size()!=4) return false;
        int[][] points = new int[4][2];
        int idx = 0;
        for(String s : set) {
            String[] ss = s.split("#");
            points[idx][0] = Integer.parseInt(ss[0]);
            points[idx][1] = Integer.parseInt(ss[1]);
            idx++;
        }
        Arrays.sort(points, (o1,o2)->o1[0]==o2[0]?o1[1]-o2[1]:o1[0]-o2[0]);
        if(points[0][0]==points[1][0]&&points[0][1]==points[2][1]
                &&points[1][1]==points[3][1]&&points[2][0]==points[3][0]
                &&(points[3][0]-points[0][0])*(points[3][1]-points[0][1])==area) {
            return true;
        }
        return false;
    }

}
