package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1864. 构成交替字符串需要的最小交换次数
 * 给你一个二进制字符串 s ，现需要将其转化为一个 交替字符串 。
 * 请你计算并返回转化所需的 最小 字符交换次数，如果无法完成转化，返回 -1 。
 * 交替字符串 是指：相邻字符之间不存在相等情况的字符串。
 * 例如，字符串 "010" 和 "1010" 属于交替字符串，但 "0100" 不是。
 * 任意两个字符都可以进行交换，不必相邻 。
 *
 * 示例 1：
 * 输入：s = "111000"
 * 输出：1
 * 解释：交换位置 1 和 4："111000" -> "101010" ，字符串变为交替字符串。
 *
 * 示例 2：
 * 输入：s = "010"
 * 输出：0
 * 解释：字符串已经是交替字符串了，不需要交换。
 *
 * 示例 3：
 * 输入：s = "1110"
 * 输出：-1
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s[i] 的值为 '0' 或 '1'
 */
public class MinimumNumberOfSwapsToMakeTheBinaryStringAlternating {

    public int minSwaps(String s) {
        int cnt0 = 0;
        int cnt1 = 0;
        for(char c : s.toCharArray()) {
            if(c=='0') {
                cnt0++;
            }else {
                cnt1++;
            }
        }
        if(Math.abs(cnt0-cnt1)>1) {
            return -1;
        }
        int ans = 1000;
        // 情况一：转化后的字符串的首字母是0
        if(cnt1-cnt0<1) {
            int change0 = 0;
            int change1 = 0;
            for(int i=0;i<s.length();i++) {
                change0 += (i&1)==1&&s.charAt(i)=='0'?1:0;
                change1 += (i&1)==0&&s.charAt(i)=='1'?1:0;
            }
            if(change0==change1) {
                ans = Math.min(ans, change0);
            }
        }
        // 情况二：转化后的字符串的首字母是1
        if(cnt0-cnt1<1) {
            int change0 = 0;
            int change1 = 0;
            for(int i=0;i<s.length();i++) {
                change0 += (i&1)==0&&s.charAt(i)=='0'?1:0;
                change1 += (i&1)==1&&s.charAt(i)=='1'?1:0;
            }
            if(change0==change1) {
                ans = Math.min(ans, change0);
            }
        }
        return ans;
    }

}
