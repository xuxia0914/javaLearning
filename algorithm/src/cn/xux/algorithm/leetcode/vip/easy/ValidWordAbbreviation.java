package cn.xux.algorithm.leetcode.vip.easy;

/**
 * 408.有效单词缩写
 * 给一个 非空 字符串 s 和一个单词缩写 abbr ，
 * 判断这个缩写是否可以是给定单词的缩写。
 * 字符串 "word" 的所有有效缩写为：
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd",
 * "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2",
 * "2r1", "3d", "w3", "4"]
 * 注意单词 "word" 的所有有效缩写仅包含以上这些。
 * 任何其他的字符串都不是 "word" 的有效缩写。
 *
 * 注意:
 * 假设字符串 s 仅包含小写字母且 abbr 只包含小写字母和数字。
 *
 * 示例 1:
 * 给定 s = "internationalization", abbr = "i12iz4n":
 * 函数返回 true.
 * 示例 2:
 * 给定 s = "apple", abbr = "a2e":
 * 函数返回 false.
 */
public class ValidWordAbbreviation {

    public boolean validWordAbbreviation(String word, String abbr) {
        if(word==null||word.length()==0||abbr==null||abbr.length()==0) {
            return false;
        }
        int len1 = word.length();
        int len2 = abbr.length();
        int idx1 = 0;
        int idx2 = 0;
        while(idx1<len1&&idx2<len2) {
            if(abbr.charAt(idx2)>='1'&&abbr.charAt(idx2)<='9') {
                int end = idx2+1;
                while(end<len2&&abbr.charAt(end)>=0&&abbr.charAt(end)<='9') {
                    end++;
                }
                int len = Integer.parseInt(abbr.substring(idx2, end));
                idx1 += len;
                idx2 = end;
            }else {
                if(word.charAt(idx1)!=abbr.charAt(idx2)) {
                    return false;
                }else {
                    idx1++;
                    idx2++;
                }
            }
        }
        return idx1==len1&&idx2==len2;
    }

}
