package cn.leetcode.xux.easy;

/**
 * 矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。
 * 如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
 * 给出两个矩形，判断它们是否重叠并返回结果。
 * 示例 1：
 * 输入：rec1 = [0,0,2,2], rec2 = [1,1,3,3]
 * 输出：true
 * 示例 2：
 * 输入：rec1 = [0,0,1,1], rec2 = [1,0,2,1]
 * 输出：false
 */
public class RectangleOverlap {

    public static boolean solution(int[] rect1, int[] rect2) {
        if(rect1==null||rect1.length!=4||rect2==null||rect2.length!=4) {
            return false;
        }
        int maxLeft = Math.max(rect1[0], rect2[0]);
        int minRight = Math.min(rect1[2], rect2[2]);
        int maxBottom = Math.max(rect1[1], rect2[1]);
        int minTop = Math.min(rect1[3], rect2[3]);

        return (maxLeft<minRight)&&(maxBottom<minTop);
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{0,0,2,2}, new int[]{1,1,3,3}));
        System.out.println(solution(new int[]{0,0,1,1}, new int[]{1,0,2,1}));
    }

}
