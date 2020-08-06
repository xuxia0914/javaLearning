package cn.xux.algorithm.leetcode.general.easy;

/**
 *Write a function that takes a string as input and returns the string reversed.
 * Example:
 * Given s = “hello”, return “olleh”.
 */
public class ReverseString {

    public static String solution1(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static String solution2(String s) {
        StringBuffer sb = new StringBuffer();
        for(int i=s.length()-1;i>=0;i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public void reverseString(char[] s) {
        if(s==null||s.length<2) {
            return;
        }
        int n = s.length;
        char tmp;
        for(int i=0;i<=(s.length-2)/2;i++) {
            tmp = s[i];
            s[i] = s[n-1-i];
            s[n-1-i] = tmp;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution1("hello"));
        System.out.println(solution2("hello"));
    }

}
