package cn.leetcode.xux.general.easy;

/**
 * 字符串中的加粗单词
 * Given a set of keywords words and a string S,
 * make all appearances of all keywords in S bold.
 * Any letters between <b> and </b> tags become bold.
 * The returned string should use the least number of tags possible,
 * and of course the tags should form a valid combination.
 * For example, given that words = ["ab", "bc"] and S = "aabcd",
 * we should return "a<b>abc</b>d". Note that returning "a<b>a<b>b</b>c</b>d" would use more tags,
 * so it is incorrect.
 * Note:
 * words has length in range [0, 50].
 * words[i] has length in range [1, 10].
 * S has length in range [0, 500].
 * All characters in words[i] and S are lowercase letters.
 */
public class BoldWordsInString {

    public String boldWords(String[] words, String S) {
        boolean[] flags = new boolean[S.length()];
        for(String word : words) {
            for(int i=0;i<S.length()-word.length();i++) {
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
