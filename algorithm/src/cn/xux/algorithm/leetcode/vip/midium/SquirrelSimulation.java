package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 573. 松鼠模拟
 * 现在有一棵树，一只松鼠和一些坚果。位置由二维网格的单元格表示。
 * 你的目标是找到松鼠收集所有坚果的最小路程，且坚果是一颗接一颗地被放在树下。
 * 松鼠一次最多只能携带一颗坚果，松鼠可以向上，向下，向左和向右四个方向移动到相邻的单元格。移动次数表示路程。
 *
 * 示例 1:
 * 输入:
 * 高度 : 5
 * 宽度 : 7
 * 树的位置 : [2,2]
 * 松鼠 : [4,4]
 * 坚果 : [[3,0], [2,5]]
 * 输出: 12
 *
 * 注意:
 * 所有给定的位置不会重叠。
 * 松鼠一次最多只能携带一颗坚果。
 * 给定的坚果位置没有顺序。
 * 高度和宽度是正整数。 3 <= 高度 * 宽度 <= 10,000。
 * 给定的网格至少包含一颗坚果，唯一的一棵树和一只松鼠。
 */
public class SquirrelSimulation {

    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        // Write your code here
        int sum = 0;
        for(int[] nut : nuts) {
            sum += 2*dis(nut, tree);
        }
        int ans = Integer.MAX_VALUE;
        for(int[] nut : nuts) {
            ans = Math.min(ans, sum+dis(nut, squirrel)-dis(nut, tree));
        }
        return ans;
    }

    private int dis(int[] p1, int[] p2) {
        return Math.abs(p1[0]-p2[0])+Math.abs(p1[1]-p2[1]);
    }

}
