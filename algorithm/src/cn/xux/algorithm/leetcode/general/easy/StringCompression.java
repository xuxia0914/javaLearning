package cn.xux.algorithm.leetcode.general.easy;

/**
 * 字符串压缩
 * Given an array of characters, compress it in-place.
 * The length after compression must always be smaller than or equal to the original array.
 * Every element of the array should be a character (not int) of length 1.
 * After you are done modifying the input array in-place, return the new length of the array.
 * Follow up:
 * Could you solve it using only O(1) extra space?
 * Example 1:
 * Input:
 * ["a","a","b","b","c","c","c"]
 * Output:
 * Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 * Explanation:
 * "aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
 * Example 2:
 * Input:
 * ["a"]
 * Output:
 * Return 1, and the first 1 characters of the input array should be: ["a"]
 * Explanation:
 * Nothing is replaced.
 * Example 3:
 * Input:
 * ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * Output:
 * Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
 * Explanation:
 * Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
 * Notice each digit has it's own entry in the array.
 * Note:
 * All characters have an ASCII value in [35, 126].
 * 1 <= len(chars) <= 1000.
 */
public class StringCompression {

    public static int solution(char[] chars) {
        if(chars==null||chars.length==0) {
            return 0;
        }
        int index = 0;
        int res = 0;
        int len = chars.length;
        char pre = chars[0];
        char curr;
        int cnt = 1;
        String s;
        for(int i=1;i<len;i++) {
            curr = chars[i];
            if(curr==pre) {
                cnt++;
            }else if(cnt==1) {
                res++;
                chars[index++] = pre;
                pre = curr;
                cnt = 1;
            }else {
                res +=2;
                chars[index++] = pre;
                s = String.valueOf(cnt);
                for(int j=0;j<s.length();j++) {
                    chars[index++] = s.charAt(j);
                }
                pre = curr;
                cnt = 1;
            }
        }
        if(cnt==1) {
            chars[index++] = pre;
        }else {
            chars[index++] = pre;
            s = String.valueOf(cnt);
            for(int i=0;i<s.length();i++) {
                chars[index++] = s.charAt(i);
            }
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println(solution(new char[]{'a','a','b','b','c','c','c'}));
        System.out.println(solution(new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'}));
    }

}
