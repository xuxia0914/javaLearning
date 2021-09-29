package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 2001. 可互换矩形的组数
 * 用一个下标从 0 开始的二维整数数组 rectangles 来表示 n 个矩形，
 * 其中 rectangles[i] = [widthi, heighti] 表示第 i 个矩形的宽度和高度。
 * 如果两个矩形 i 和 j（i < j）的宽高比相同，则认为这两个矩形 可互换 。
 * 更规范的说法是，两个矩形满足 widthi/heighti == widthj/heightj（使用实数除法而非整数除法），
 * 则认为这两个矩形 可互换 。
 * 计算并返回 rectangles 中有多少对 可互换 矩形。
 *
 * 示例 1：
 * 输入：rectangles = [[4,8],[3,6],[10,20],[15,30]]
 * 输出：6
 * 解释：下面按下标（从 0 开始）列出可互换矩形的配对情况：
 * - 矩形 0 和矩形 1 ：4/8 == 3/6
 * - 矩形 0 和矩形 2 ：4/8 == 10/20
 * - 矩形 0 和矩形 3 ：4/8 == 15/30
 * - 矩形 1 和矩形 2 ：3/6 == 10/20
 * - 矩形 1 和矩形 3 ：3/6 == 15/30
 * - 矩形 2 和矩形 3 ：10/20 == 15/30
 *
 * 示例 2：
 * 输入：rectangles = [[4,5],[7,8]]
 * 输出：0
 * 解释：不存在成对的可互换矩形。
 *
 * 提示：
 * n == rectangles.length
 * 1 <= n <= 105
 * rectangles[i].length == 2
 * 1 <= widthi, heighti <= 105
 */
public class NumberOfPairsOfInterchangeableRectangles {

    public long interchangeableRectangles(int[][] rectangles) {
        Map<Long, Integer> map = new HashMap<>();
        for(int[] rec : rectangles) {
            int gcd = gcd(rec[0], rec[1]);
            long curr = (long)rec[0]/gcd*100001+rec[1]/gcd;
            map.put(curr, map.getOrDefault(curr, 0)+1);
        }
        long ans = 0L;
        for(int val : map.values()) {
            ans += (long) val*(val-1)/2;
        }
        return ans;
    }

    private int gcd(int a, int b) {
        while(a%b!=0) {
            int tmp = a;
            a = b;
            b = tmp%b;
        }
        return b;
    }

}
