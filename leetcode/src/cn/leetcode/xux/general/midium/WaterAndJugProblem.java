package cn.leetcode.xux.general.midium;

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

    /**
     * 本题先判断所需要的水量是否大于两个桶的容量之和，如果不大于，判断所需要的水量是否是两个桶容量的最大公约数的倍数，根据裴蜀定理可以证明：
     * 如果所需要的水量是两个水壶容量的最大公约数的倍数，且水量不大于两个水壶的容量之和，那么必然可以用这两个水壶操作得到所需要的水量。
     * */
    public boolean canMeasureWater(int x, int y, int z) {
        if(z<0||z>x+y) {
            return false;
        }
        if(x>y) {
            x = x^y;
            y = x^y;
            x = x^y;
        }
        if(x==0) {
            return z==0||z==y;
        }
        if(y%x==0) {
            return z%x==0;
        }else {
            for(int i=x/2;i>0;i--) {
                if(x%i==0&&y%i==0) {
                    return z%i==0;
                }
            }
        }
        return false;
    }

    Set<String> mem;

    /**TLE*/
    public boolean canMeasureWater1(int x, int y, int z) {
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
