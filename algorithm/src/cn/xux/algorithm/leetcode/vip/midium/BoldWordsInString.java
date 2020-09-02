package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 758.字符串中的加粗单词
 * 给定一个关键词集合 words 和一个字符串 S，将所有 S 中出现的关键词加粗。
 * 所有在标签 <b> 和 </b> 中的字母都会加粗。
 * 返回的字符串需要使用尽可能少的标签，当然标签应形成有效的组合。
 * 例如，给定 words = ["ab", "bc"] 和 S = "aabcd"，需要返回 "a<b>abc</b>d"。
 * 注意返回 "a<b>a<b>b</b>c</b>d" 会使用更多的标签，因此是错误的。
 *
 * 注：
 * words 长度的范围为 [0, 50]。
 * words[i] 长度的范围为 [1, 10]。
 * S 长度的范围为 [0, 500]。
 * 所有 words[i] 和 S 中的字符都为小写字母。
 */
public class BoldWordsInString {

    public String boldWords(String[] words, String S) {
        boolean[] flags = new boolean[S.length()];
        for(String word : words) {
            for(int i=0;i<=S.length()-word.length();i++) {
                if(word.equals(S.substring(i, i+word.length()))) {
                    for(int j=i;j<i+word.length();j++) {
                        flags[j] = true;
                    }
                }
            }
        }
        boolean pre = false;
        StringBuilder sb = new StringBuilder();
        boolean flag;
        for(int i=0;i<S.length();i++) {
            flag = flags[i];
            if(!pre) {
                if(!flag) {
                    sb.append(S.charAt(i));
                }else {
                    sb.append("<b>").append(S.charAt(i));
                    pre = true;
                }
            }else {
                if(!flag) {
                    sb.append("</b>").append(S.charAt(i));
                    pre = false;
                }else {
                    sb.append(S.charAt(i));
                }
            }
        }
        if(pre) {
            sb.append("</b>");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        BoldWordsInString b = new BoldWordsInString();
        System.out.println(b.boldWords(new String[]{"ab", "bc"}, "aabcd"));
    }

}
