package cn.leetcode.xux.general.easy;

/**
 * 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 */
public class ImplementStrStr {

    public static int solution(String haystack, String needle) {
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

    public static void main(String[] args) {
        System.out.println(solution("hello", "ll"));
        System.out.println(solution("hello", ""));
        System.out.println(solution("aaaaa", "bba"));
    }

}
