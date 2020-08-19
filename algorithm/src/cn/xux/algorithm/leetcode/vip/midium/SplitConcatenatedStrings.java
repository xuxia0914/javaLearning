package cn.xux.algorithm.leetcode.vip.midium;

import java.util.List;

/**
 * 555. 分割连接字符串
 * 给定一个字符串列表，你可以将这些字符串连接成一个循环字符串，对于每个字符串，你可以选择是否翻转它。
 * 在所有可能的循环字符串中，你需要分割循环字符串（这将使循环字符串变成一个常规的字符串），然后找到字典序最大的字符串。
 * 具体来说，要找到字典序最大的字符串，你需要经历两个阶段：
 * 将所有字符串连接成一个循环字符串，你可以选择是否翻转某些字符串，并按照给定的顺序连接它们。
 * 在循环字符串的某个位置分割它，这将使循环字符串从分割点变成一个常规的字符串。
 * 你的工作是在所有可能的常规字符串中找到字典序最大的一个。
 *
 * 示例:
 * 输入: "abc", "xyz"
 * 输出: "zyxcba"
 * 解释: 你可以得到循环字符串 "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-"，
 * 其中 '-' 代表循环状态。
 * 答案字符串来自第四个循环字符串，
 * 你可以从中间字符 'a' 分割开然后得到 "zyxcba"。
 *
 * 注意:
 * 输入字符串只包含小写字母。
 * 所有字符串的总长度不会超过 1,000。
 */
public class SplitConcatenatedStrings {

    public String splitLoopedString(List<String> strs) {
        // write your code here
        if(strs==null||strs.size()==0) {
            return "";
        }
        int size = strs.size();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<size;i++) {
            String rev = new StringBuilder(strs.get(i)).reverse().toString();
            if(rev.compareTo(strs.get(i))>0) {
                strs.set(i, rev);
            }
            sb.append(strs.get(i));
        }
        String orig = sb.toString();
        int start = 0;
        String ans = orig;
        for(int i=0;i<size;i++) {
            String str = strs.get(i);
            int end = start+str.length()-1;
            String pre = orig.substring(0,start);
            String post = orig.substring(end+1);
            String rev = new StringBuilder(str).reverse().toString();
            for(int j=0;j<str.length();j++) {
                String curr1 = str.substring(j)+post+pre+str.substring(0,j);
                if(curr1.compareTo(ans)>0) {
                    ans = curr1;
                }
                String curr2 = rev.substring(j)+post+pre+rev.substring(0,j);
                if(curr2.compareTo(ans)>0) {
                    ans = curr2;
                }
                start = end+1;
            }
        }
        return ans;
    }

}
