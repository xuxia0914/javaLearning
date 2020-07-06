package cn.leetcode.xux.common;

import java.util.Stack;

public class Test1 {

    public static void main(String[] args) {
        System.out.println(new Test1().MaximumSum(new int[]{51, 71, 17, 42}));
    }

    /**
     * 1604. 两数最大和
     * 中文English
     * 给定一个由N个整数组成的数组A，返回两个数字的最大总和，规定这两个数的所有位加起来相等。
     * 如果没有两个数字的各个位相加和相等，则该函数应返回-1。
     *
     * 样例
     * 示例1:
     * 输入:
     * A = [51, 71, 17, 42]
     * 输出: 93
     * 解释：这里有两对各个位相加和相等的数：(51, 42) 和 (17,71)，第一对的和是93
     * 示例2:
     * 输入:
     * A = [42, 33, 60]
     * 输出: 102
     * 解释：所有的数各个位相加的和都相等，选择42 + 60 = 102
     * 示例3:
     * 输入:
     * A = [51, 32, 43]
     * 输出: -1
     * 解释: 所有数的各个位相加和都不一样，因此返回-1
     * 注意事项
     * N的范围是 [1, 200000]
     * A中的每一个参数的范围是 [1, 1000000000]
     */
    public int MaximumSum(int[] A) {
        // write your code ere
        int[] max = new int[82 ];
        int ans = -1;
        for(int num : A) {
            int ds = digitSum(num);
            if(max[ds]>0) {
                ans = Math.max(ans, num+max[ds]);
            }
            max[ds] = Math.max(max[ds], num);
        }
        return ans;
    }

    private int digitSum(int num) {
        int ans = 0;
        while(num>0) {
            ans += num%10;
            num /= 10;
        }
        return ans;
    }

}
