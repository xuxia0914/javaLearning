package cn.xux.algorithm.leetcode.general.easy;

/**
 * 28. 实现 strStr()
 * 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，
 * 在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 *
 * 示例 2:
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 *
 * 说明:
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。
 * 这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */
public class ImplementStrStr {

    public int strStr(String haystack, String needle) {
        if(needle==null||haystack==null||haystack.length()<needle.length()) {
            return -1;
        }
        int m = haystack.length(), n = needle.length();
        for(int i=0;i<=m-n;i++) {
            if(haystack.substring(i, i+n).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    //KMP
    public int strStr1(String haystack, String needle) {
        // Write your code here
        if(haystack==null||needle==null||needle.length()>haystack.length()) {
            return -1;
        }
        int len1 = haystack.length();
        int len2 = needle.length();
        if(len2==0) {
            return 0;
        }
        //获得next[]
        int[] next = getNext(needle);
        //开始匹配
        int idx1 = 0;
        int idx2 = 0;
        while(idx1<len1&&idx2<len2&&len1-idx1>=idx2-idx1) {
            if(idx2==-1||haystack.charAt(idx1)==needle.charAt(idx2)) {
                idx1++;
                idx2++;
            }else {
                idx2 = next[idx2];
            }
        }
        return idx2==len2?idx1-len2:-1;
    }

    // 生成失配数组next[]
    private int[] getNext(String needle) {
        int len = needle.length();
        int[] next = new int[len];
        next[0] = -1;
        int i = 0;
        int j = -1;
        while(i<len-1) {
            if(j==-1||needle.charAt(i)==needle.charAt(j)) {
                i++;
                j++;
                //next[i] = j;
                //next优化
                if(needle.charAt(i)==needle.charAt(j)) {
                    next[i] = next[j];
                }else {
                    next[i] = j;
                }
            }else {
                j = next[j];
            }
        }
        return next;
    }

    public static void main(String[] args) {
        ImplementStrStr is = new ImplementStrStr();
//        System.out.println(is.strStr1("hello", "ll"));
//        System.out.println(is.strStr1("hello", ""));
//        System.out.println(is.strStr1("aaaaa", "bba"));
        System.out.println(is.strStr1("aaabaaabbbabaa", "babb"));
    }

}
