package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 1156. 单字符重复子串的最大长度  显示英文描述
 * 如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。
 * 给你一个字符串 text，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。
 *
 * 示例 1：
 * 输入：text = "ababa"
 * 输出：3
 *
 * 示例 2：
 * 输入：text = "aaabaaa"
 * 输出：6
 *
 * 示例 3：
 * 输入：text = "aaabbaaa"
 * 输出：4
 *
 * 示例 4：
 * 输入：text = "aaaaa"
 * 输出：5
 *
 * 示例 5：
 * 输入：text = "abcdef"
 * 输出：1
 *
 * 提示：
 * 1 <= text.length <= 20000
 * text 仅由小写英文字母组成。
 */
public class SwapForMaximumRepeatedSubstring {

    public int maxRepOpt1(String text) {
        if(text==null||text.length()==0) {
            return 0;
        }
        int res = 1;
        List<int[]> list = new ArrayList<>();
        char pre = text.charAt(0);
        int cnt = 1;
        for(int i=1;i<text.length();i++) {
            if(text.charAt(i)==pre) {
                cnt++;
            }else {
                res = Math.max(res, cnt);
                list.add(new int[]{(int)pre, cnt});
                pre = text.charAt(i);
                cnt = 1;
            }
        }
        res = Math.max(res, cnt);
        list.add(new int[]{(int)pre, cnt});
        for(int i=0;i<list.size();i++) {
            for(int j=0;j<list.size();j++) {
                if(j==i) {
                    continue;
                }
                if(list.get(i)[0]==list.get(j)[0]) {
                    res = Math.max(res, list.get(i)[1]+1);
                    break;
                }
            }
        }
        for(int i=1;i<list.size()-1;i++) {
            if(list.get(i-1)[0]==list.get(i+1)[0]&&list.get(i)[1]==1) {
                res = Math.max(res, list.get(i-1)[1]+list.get(i+1)[1]);
                for(int j=0;j<list.size();j++) {
                    if(j==i||j==i-1||j==i+1) {
                        continue;
                    }else if(list.get(j)[0]==list.get(i-1)[0]) {
                        res = Math.max(res, list.get(i-1)[1]+list.get(i+1)[1]+1);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new SwapForMaximumRepeatedSubstring().maxRepOpt1("aaaaabbbbbbaabbaabbaaabbbbab"));
    }

}
