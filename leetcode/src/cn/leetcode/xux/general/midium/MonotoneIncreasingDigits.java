package cn.leetcode.xux.general.midium;

/**
 * 738. 单调递增的数字
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 *
 * 示例 1:
 * 输入: N = 10
 * 输出: 9
 *
 * 示例 2:
 * 输入: N = 1234
 * 输出: 1234
 *
 * 示例 3:
 * 输入: N = 332
 * 输出: 299
 *
 * 说明: N 是在 [0, 10^9] 范围内的一个整数。
 */
public class MonotoneIncreasingDigits {

    public int monotoneIncreasingDigits(int N) {
        if(N<10) {
            return N;
        }
        String s = String.valueOf(N);
        int i;
        for(i=0;i<s.length()-1;i++) {
            if(s.charAt(i)>s.charAt(i+1)) {
                break;
            }
        }
        if(i==s.length()-1) {
            return N;
        }
        while(i>0&&s.charAt(i)==s.charAt(i-1)) {
            i--;
        }
        StringBuilder sb = new StringBuilder();
        for(int j=0;j<i;j++) {
            sb.append(s.charAt(j));
        }
        sb.append(s.charAt(i)-'1');
        for(int j=i+1;j<s.length();j++) {
            sb.append(9);
        }
        return Integer.valueOf(sb.toString());
    }

    public static void main(String[] args) {
        MonotoneIncreasingDigits mid = new MonotoneIncreasingDigits();
        System.out.println(mid.monotoneIncreasingDigits(10));
        System.out.println(mid.monotoneIncreasingDigits(1000));
        System.out.println(mid.monotoneIncreasingDigits(1234));
        System.out.println(mid.monotoneIncreasingDigits(12221));
        System.out.println(mid.monotoneIncreasingDigits(2221));
        System.out.println(mid.monotoneIncreasingDigits(344565));
    }

}
