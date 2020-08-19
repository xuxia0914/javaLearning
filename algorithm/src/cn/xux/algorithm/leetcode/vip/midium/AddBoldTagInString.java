package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 616. 给字符串添加加粗标签
 * 给一个字符串 s 和一个字符串列表 dict ，你需要将在字符串列表中出现过的 s 的子串添加加粗闭合标签 <b> 和 </b> 。
 * 如果两个子串有重叠部分，你需要把它们一起用一个闭合标签包围起来。
 * 同理，如果两个子字符串连续被加粗，那么你也需要把它们合起来用一个加粗标签包围。
 *
 * 样例 1：
 * 输入：
 * s = "abcxyz123"
 * dict = ["abc","123"]
 * 输出：
 * "<b>abc</b>xyz<b>123</b>"
 *
 * 样例 2：
 * 输入：
 * s = "aaabbcc"
 * dict = ["aaa","aab","bc"]
 * 输出：
 * "<b>aaabbc</b>c"
 *
 * 注意：
 * 给定的 dict 中不会有重复的字符串，且字符串数目不会超过 100 。
 * 输入中的所有字符串长度都在范围 [1, 1000] 内。
 */
public class AddBoldTagInString {

    public String addBoldTag(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()];
        for(int i=0,end=0;i<s.length();i++) {
            for(String word : dict) {
                if(s.startsWith(word, i)) {
                    end = Math.max(end, i+word.length());
                }
            }
            bold[i] = i<end;
        }
        StringBuilder sb = new StringBuilder();
        int left = 0;
        for(int right=1;right<s.length();right++) {
            if(bold[right]!=bold[right-1]) {
                if(bold[right-1]) {
                    sb.append("<b>").append(s, left, right).append("</b>");
                }else {
                    sb.append(s, left, right);
                }
                left = right;
            }
        }
        if(bold[bold.length-1]) {
            sb.append("<b>").append(s.substring(left)).append("</b>");
        }else {
            sb.append(s.substring(left));
        }
        return sb.toString();
    }

}
