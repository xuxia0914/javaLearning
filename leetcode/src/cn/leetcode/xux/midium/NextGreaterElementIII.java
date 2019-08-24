package cn.leetcode.xux.midium;

/**
 * Given a positive 32-bit integer n,
 * you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n
 * and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.
 * Example 1:
 * Input: 12
 * Output: 21
 * Example 2:
 * Input: 21
 * Output: -1
 */
public class NextGreaterElementIII {

    public int nextGreaterElement(int n) {
        if(n<12) {
            return -1;
        }
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();
        int len = chars.length;
        int i;
        for(i=len-2;i>=0;i--) {
            if(chars[i+1]>chars[i]) {
                break;
            }
        }
        if(i<0) {
            return -1;
        }else {
            int j;
            for(j=len-1;j>i;j--) {
                if(chars[j]>chars[i]) {
                    break;
                }
            }
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            reverse(chars, i+1, len-1);
        }
        long l = Long.valueOf(new String(chars));
        return l>(long)Integer.MAX_VALUE?-1:(int)l;
    }

    public void reverse(char[] chars, int start, int end) {
        while(start<end) {
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;
            end--;
        }
    }

}
