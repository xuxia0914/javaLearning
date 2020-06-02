package cn.leetcode.xux.midium;

/**
 * 1432. 改变一个整数能得到的最大差值
 * 给你一个整数 num 。你可以对它进行如下步骤恰好 两次 ：
 * 选择一个数字 x (0 <= x <= 9).
 * 选择另一个数字 y (0 <= y <= 9) 。数字 y 可以等于 x 。
 * 将 num 中所有出现 x 的数位都用 y 替换。
 * 得到的新的整数 不能 有前导 0 ，得到的新整数也 不能 是 0 。
 * 令两次对 num 的操作得到的结果分别为 a 和 b 。
 * 请你返回 a 和 b 的 最大差值 。
 *
 * 示例 1：
 * 输入：num = 555
 * 输出：888
 * 解释：第一次选择 x = 5 且 y = 9 ，并把得到的新数字保存在 a 中。
 * 第二次选择 x = 5 且 y = 1 ，并把得到的新数字保存在 b 中。
 * 现在，我们有 a = 999 和 b = 111 ，最大差值为 888
 *
 * 示例 2：
 * 输入：num = 9
 * 输出：8
 * 解释：第一次选择 x = 9 且 y = 9 ，并把得到的新数字保存在 a 中。
 * 第二次选择 x = 9 且 y = 1 ，并把得到的新数字保存在 b 中。
 * 现在，我们有 a = 9 和 b = 1 ，最大差值为 8
 *
 * 示例 3：
 * 输入：num = 123456
 * 输出：820000
 *
 * 示例 4：
 * 输入：num = 10000
 * 输出：80000
 *
 * 示例 5：
 * 输入：num = 9288
 * 输出：8700
 *
 * 提示：
 * 1 <= num <= 10^8
 */
public class MaxDifferenceYouCanGetFromChangingAnInteger {

    public int maxDiff(int num) {
        String str = String.valueOf(num);
        int len = str.length();
        int ans = 0;
        int a = -1;
        int b = -1;
        char c = str.charAt(0);
        if(c=='1') {
            b = 8;
            ans += b*Math.pow(10, len-1);
            for(int i=1;i<len;i++) {
                c = str.charAt(i);
                if(c=='1') {
                    ans += b*Math.pow(10, len-i-1);
                }else if(c!='0') {
                    if(a==-1) {
                        a = c-'0';
                    }
                    if(a==c-'0') {
                        ans += a * Math.pow(10, len - i - 1);
                    }
                }
            }
        }else if(c=='9') {
            a = 8;
            ans += a*Math.pow(10, len-1);
            for(int i=1;i<len;i++) {
                c = str.charAt(i);
                if(c=='9') {
                    ans += a*Math.pow(10, len-i-1);
                }else {
                    if(b==-1) {
                        b = '9'-c;
                    }
                    if(b=='9'-c) {
                        ans += b*Math.pow(10, len-i-1);
                    }
                }
            }
        }else {
            a = c-'1';
            b = '9'-c;
            ans += (a+b)*Math.pow(10, len-1);
            for(int i=1;i<len;i++) {
                c = str.charAt(i);
                if(a==c-'1') {
                    ans += (a+b)*Math.pow(10, len-i-1);
                }
            }
        }
        return ans;
    }

}
