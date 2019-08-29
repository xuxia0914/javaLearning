package cn.leetcode.xux.midium;

import java.util.HashSet;
import java.util.Set;

/**
 * 365. 水壶问题
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 * 你允许：
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 *
 * 示例 1: (From the famous "Die Hard" example)
 * 输入: x = 3, y = 5, z = 4
 * 输出: True
 *
 * 示例 2:
 * 输入: x = 2, y = 6, z = 5
 * 输出: False
 */
public class WaterAndJugProblem {

    Set<String> mem;

    /**TLE TODO*/
    public boolean canMeasureWater(int x, int y, int z) {
        mem = new HashSet<String>();
        if(z<0||z>x+y) {
            return false;
        }
        Set<Integer> res = new HashSet<>();
        helper(res, x, y, 0, 0);
        return res.contains(z);
    }

    public void helper(Set<Integer> res, int x, int y, int currX, int currY) {
        if(mem.contains(""+currX+","+currY)) {
            return;
        }
        res.add(currX);
        res.add(currY);
        res.add(currX+currY);
        mem.add(""+currX+","+currY);
        helper(res, x, y, x, currY);
        helper(res, x, y, 0, currY);
        helper(res, x, y, Math.max(currX-currY, 0), Math.min(y, currY+currX));
        helper(res, x, y, currX, y);
        helper(res, x, y, currX, 0);
        helper(res, x, y, Math.min(x, currX+currX), Math.max(currY-currX, 0));
    }

    public static void main(String[] args) {
        WaterAndJugProblem wj = new WaterAndJugProblem();
        System.out.println(wj.canMeasureWater(3, 5, 4));
        System.out.println(wj.canMeasureWater(2, 6, 5));
        System.out.println(wj.canMeasureWater(22003, 31237, 123));
    }

}
