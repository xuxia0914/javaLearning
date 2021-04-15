package cn.xux.algorithm.leetcode.lcp.easy;

/**
 * LCP 29. 乐团站位
 * 某乐团的演出场地可视作 num * num 的二维矩阵 grid（左上角坐标为 [0,0])，
 * 每个位置站有一位成员。乐团共有 9 种乐器，乐器编号为 1~9，每位成员持有 1 个乐器。
 * 为保证声乐混合效果，成员站位规则为：
 * 自 grid 左上角开始顺时针螺旋形向内循环以 1，2，...，9 循环重复排列。
 * 例如当 num = 5 时，站位如图所示
 * image.png
 * 请返回位于场地坐标 [Xpos,Ypos] 的成员所持乐器编号。
 *
 * 示例 1：
 * 输入：num = 3, Xpos = 0, Ypos = 2
 * 输出：3
 * 解释：
 * image.png
 *
 * 示例 2：
 * 输入：num = 4, Xpos = 1, Ypos = 2
 * 输出：5
 * 解释：
 * image.png
 *
 * 提示：
 * 1 <= num <= 10^9
 * 0 <= Xpos, Ypos < num
 */
public class Lcp029 {

    public static void main(String[] args) {
        System.out.println(new Lcp029().orchestraLayout(971131546, 966980466, 531910024));
    }

    public int orchestraLayout(int num, int xPos, int yPos) {
        // 为了防止int溢出，变量都用long类型
        // 先看在第几圈,从第0圈开始
        long c = Math.min(Math.min(xPos, num-1-xPos), Math.min(yPos, num-1-yPos));
        // 再看第c层上的第几步
        long s = c==xPos?yPos-c:(c==num-1-yPos?num-2*c+xPos-c-1:(c==num-1-xPos?num-2*c+num-2*c-1+num-1-c-1-yPos:num-2*c+num-2*c-1+num-2*c-1+num-1-c-1-xPos));
        return (int)((4*c*(num-c)+s)%9+1);
    }

}
