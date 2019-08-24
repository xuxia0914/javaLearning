package cn.leetcode.xux.midium;

/**
 * Return the lexicographically smallest subsequence of text that contains all the distinct characters of text exactly once.
 * Example 1:
 * Input: "cdadabcc"
 * Output: "adbc"
 * Example 2:
 * Input: "abcd"
 * Output: "abcd"
 * Example 3:
 * Input: "ecbacba"
 * Output: "eacb"
 * Example 4:
 * Input: "leetcode"
 * Output: "letcod"
 * Note:
 * 1 <= text.length <= 1000
 * text consists of lowercase English letters.
 */
public class SmallestSubsequenceOfDistinctCharacters {

    public String smallestSubsequence(String text) {
        if(text==null||text.length()==0) {
            return text;
        }
        int len = text.length();
        int[] cnts = new int[26];
        for(int i=0;i<len;i++) {
            cnts[text.charAt(i)-'a']++;
        }
        boolean[] used = new boolean[26];
        StringBuilder sb = new StringBuilder();
        char currChar;
        int last;
        for(int i=0;i<len;i++) {
            currChar = text.charAt(i);
            if(used[currChar-'a']) {
                cnts[currChar-'a']--;
                continue;
            }
            last = sb.length()-1;
            while(last>=0&&sb.charAt(last)>currChar&&cnts[sb.charAt(last)-'a']>0) {
                used[sb.charAt(last)-'a'] = false;
                sb.deleteCharAt(last);
                last--;
            }
            sb.append(currChar);
            used[currChar-'a'] = true;
            cnts[currChar-'a']--;
        }
        return sb.toString();
    }

}
