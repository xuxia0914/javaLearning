package cn.xux.algorithm.leetcode.general.midium;

/**
 * 395. 至少有K个重复字符的最长子串
 * 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
 *
 * 示例 1:
 * 输入:s = "aaabb", k = 3
 * 输出:3
 * 最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 *
 * 示例 2:
 * 输入:s = "ababbc", k = 2
 * 输出:5
 * 最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {

    public int longestSubstring(String s, int k) {
        if(s==null||s.length()<k) {
            return 0;
        }
        int n = s.length();
        return count(s.toCharArray(), k, 0, n-1);
    }

    public int count(char[] chars, int k, int left, int right) {
        if(left>right&&right-left<k-1) {
            return 0;
        }
        int[] cnts = new int[26];
        for(int i=left; i<=right;i++) {
            cnts[chars[i]-'a']++;
        }
        for(int i=left;i<=right;i++) {
            if(cnts[chars[i]-'a']<k) {
                return Math.max(count(chars, k, left, i-1), count(chars, k, i+1, right));
            }
        }
        return right-left+1;
    }

    public int longestSubstring1(String s, int k) {
        if(s==null||s.length()<k) {
            return 0;
        }
        int[] cnts = new int[26];
        int n = s.length();
        for(int i=0;i<n;i++) {
            cnts[s.charAt(i)-'a']++;
        }
        int res = 0;
        for(int i=0;i<n;i++) {
            int[] tmp = cnts.clone();
            for(int j=n-1;j>=i+k-1;j--) {
                if(helper(tmp, k)) {
                    res = Math.max(res, j-i+1);
                    break;
                }
                tmp[s.charAt(j)-'a']--;
            }
            cnts[s.charAt(i)-'a']--;
        }
        return res;
    }

    public boolean helper(int[] cnts, int k) {
        for(int cnt : cnts) {
            if(cnt>0&&cnt<k) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LongestSubstringWithAtLeastKRepeatingCharacters ls = new LongestSubstringWithAtLeastKRepeatingCharacters();
//        System.out.println(ls.longestSubstring("aaabb", 3));
//        System.out.println(ls.longestSubstring("ababbc", 2));
//        System.out.println(ls.longestSubstring("aacbbbdc", 2));
//        System.out.println(ls.longestSubstring("abcdedghijklmnopqrstuvwxyz", 2));
        System.out.println(ls.longestSubstring("aaabbbcdefcdefgggggggggggggggcde", 3));
    }

}
