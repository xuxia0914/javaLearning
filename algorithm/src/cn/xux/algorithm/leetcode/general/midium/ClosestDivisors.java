package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1362. 最接近的因数
 * 给你一个整数 num，请你找出同时满足下面全部要求的两个整数：
 * 两数乘积等于  num + 1 或 num + 2
 * 以绝对差进行度量，两数大小最接近
 * 你可以按任意顺序返回这两个整数。
 *
 * 示例 1：
 * 输入：num = 8
 * 输出：[3,3]
 * 解释：对于 num + 1 = 9，最接近的两个因数是 3 & 3；对于 num + 2 = 10,
 * 最接近的两个因数是 2 & 5，因此返回 3 & 3 。
 *
 * 示例 2：
 * 输入：num = 123
 * 输出：[5,25]
 *
 * 示例 3：
 * 输入：num = 999
 * 输出：[40,25]
 *
 * 提示：
 * 1 <= num <= 10^9
 */
public class ClosestDivisors {

    public int[] closestDivisors(int num) {
        int num1 = num+1;
        int res11 = (int)Math.sqrt(num1);
        while(num1%res11!=0) {
            res11--;
        }
        int res12 = num1/res11;
        int num2 = num+2;
        int res21 = (int)Math.sqrt(num2);
        while(num2%res21!=0) {
            res21--;
        }
        int res22 = num2/res21;
        return res22-res21<res12-res11?new int[]{res21, res22}:new int[]{res11, res12};
    }

}
