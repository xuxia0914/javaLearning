package cn.leetcode.xux.lintcode;

import java.util.*;

/**
 * 739. 24点
 * 中文English
 * 你有 4 张卡片, 每一张上面都有一个 1 到 9 的数字. 你需要判断是否能用运算符 *, /, +, -, (, ) 来计算得到 24
 *
 * 样例
 * 样例 1:
 * 输入：[1, 4, 8, 7]
 * 输出：true
 * 解释：8 * （7 - 4） * 1 = 24
 *
 * 样例 2:
 * 输入：[1, 1, 1, 2]
 * 输出：false
 * 解释：无法得到24
 *
 * 样例 3:
 * 输入：[3, 3, 8, 8]
 * 输出：true
 * 解释：8 / ( 3 - 8 / 3) = 24
 * 注意事项
 * 除法运算符 / 是真正的除法, 而不是整数除法. 所以 4 / (1 - 2 / 3) = 12.
 * 所有的运算符位于两个数字之间. 尤其是, 我们不能将 - 当做一个一元运算符. 比如说, 输入为[1, 1, 1, 1], 表达式 - 1 - 1 - 1 - 1 是不允许的
 * 你不能将数字 串联 起来. 比如, 如果输入为 [1, 2, 1, 2], 我们不能写成 12 + 12
 */
public class Lintcode739 {

    public static void main(String[] args) {
        System.out.println(new Lintcode739().compute24(new double[]{1,4,7,8}));
    }

    /**
     * @param nums: 4 cards
     * @return: whether they could get the value of 24
     */
    public boolean compute24(double[] nums) {
        // write your code here
        List<Double> list = new ArrayList<>();
        for(int i=0;i<4;i++) {
            list.add(nums[i]);
        }
        return dfs(list);
    }

    private boolean dfs(List<Double> nums) {
        if(nums.size()==2) {
            double n0 = nums.get(0);
            double n1 = nums.get(1);
            return equals24(n0+n1)||equals24(Math.abs(n0-n1))
                    ||equals24(n0*n1)||equals24(n0/n1)||equals24(n1/n0);
        }
        for(int i=0;i<nums.size()-1;i++) {
            for(int j=i+1;j<nums.size();j++) {
                List<Double> pl = new ArrayList<>();
                for(int k=0;k<nums.size();k++) {
                    if(k!=i&&k!=j) {
                        pl.add(nums.get(k));
                    }
                }
                double n0 = nums.get(i);
                double n1 = nums.get(j);
                List<Double> pl1 = new ArrayList<>(pl);
                pl1.add(n0+n1);
                if(dfs(pl1)) {
                    return true;
                }
                List<Double> pl2 = new ArrayList<>(pl);
                pl2.add(n0-n1);
                if(dfs(pl2)) {
                    return true;
                }
                List<Double> pl3 = new ArrayList<>(pl);
                pl3.add(n1-n0);
                if(dfs(pl3)) {
                    return true;
                }
                List<Double> pl4 = new ArrayList<>(pl);
                pl4.add(n0*n1);
                if(dfs(pl4)) {
                    return true;
                }
                List<Double> pl5 = new ArrayList<>(pl);
                pl5.add(n0/n1);
                if(dfs(pl5)) {
                    return true;
                }
                List<Double> pl6 = new ArrayList<>(pl);
                pl6.add(n1/n0);
                if(dfs(pl6)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean equals24(double num) {
        return Math.abs(num-24)<1E-10;
    }

}
