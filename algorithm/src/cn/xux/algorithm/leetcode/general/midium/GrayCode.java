package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 89. 格雷编码
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
 * 格雷编码序列必须以 0 开头。
 *
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * 对于给定的 n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 *
 * 示例 2:
 * 输入: 0
 * 输出: [0]
 * 解释: 我们定义格雷编码序列必须以 0 开头。
 *      给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
 *      因此，当 n = 0 时，其格雷编码序列为 [0]。
 */
public class GrayCode {

    /**
     * Runtime: 1 ms, faster than 47.61% of Java online submissions for Gray Code.
     * Memory Usage: 34.1 MB, less than 12.95% of Java online submissions for Gray Code.
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        if(n<0) {
            return res;
        }
        res.add(0);
        if(n==0) {
            return res;
        }
        res = grayCode(n-1);
        int num = (int)Math.pow(2, n-1);
        int i = res.size();
        while(i-->0) {
            res.add(num+res.get(i));
        }
        return res;
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Gray Code.
     * Memory Usage: 34.3 MB, less than 6.83% of Java online submissions for Gray Code.
     * @param n
     * @return
     */
    public List<Integer> grayCode1(int n) {
        List<Integer> res = new ArrayList<>();

        int num = (int)Math.pow(2, n);
        for(int i=0;i<num;i++) {
            res.add((i>>1)^i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new GrayCode().grayCode1(3));
    }

}
