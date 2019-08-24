package cn.leetcode.xux.easy;

import java.util.Stack;

/**
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 * Example 1:
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 * Example 2:
 * Input: S = "ab##", T = "c#d#"
 * Output: true
 * Explanation: Both S and T become "".
 * Example 3:
 * Input: S = "a##c", T = "#a#c"
 * Output: true
 * Explanation: Both S and T become "c".
 * Example 4:
 * Input: S = "a#c", T = "b"
 * Output: false
 * Explanation: S becomes "c" while T becomes "b".
 * Note:
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S and T only contain lowercase letters and '#' characters.
 * Follow up:
 * Can you solve it in O(N) time and O(1) space?
 */
public class BackspaceStringCompare {

    public boolean backspaceCompare(String S, String T) {
        Stack<Character> stackS = new Stack<>();
        Stack<Character> stackT = new Stack<>();
        for(int i=0;i<S.length();i++) {
            if(S.charAt(i)=='#') {
                if(!stackS.isEmpty()) {
                    stackS.pop();
                }
            }else {
                stackS.push(S.charAt(i));
            }
        }
        for(int i=0;i<T.length();i++) {
            if(T.charAt(i)=='#') {
                if(!stackT.isEmpty()) {
                    stackT.pop();
                }
            }else {
                stackT.push(T.charAt(i));
            }
        }
        return stackS.equals(stackT);
    }

    public boolean backspaceCompare1(String S, String T) {
        int cnt1 = 0, cnt2 = 0;
        int index1 = S.length()-1, index2 = T.length()-1;
        while(index1>=0||index2>=0) {
            while(index1>=0&&(cnt1>0||S.charAt(index1)=='#')) {
                if(S.charAt(index1)=='#') {
                    cnt1++;
                }else {
                    cnt1--;
                }
                index1--;
            }
            while(index2>=0&&(cnt2>0||T.charAt(index2)=='#')) {
                if(T.charAt(index2)=='#') {
                    cnt2++;
                }else {
                    cnt2--;
                }
                index2--;
            }
            if(index1>=0&&index2>=0) {
                if(S.charAt(index1)==T.charAt(index2)) {
                    index1--;
                    index2--;
                }else {
                    return false;
                }
            }else if((index1>=0&&S.charAt(index1)!='#')
                    ||(index2>=0&&T.charAt(index2)!='#')) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BackspaceStringCompare bsc = new BackspaceStringCompare();
//        System.out.println(bsc.backspaceCompare1("ab#c", "ad#c"));
//        System.out.println(bsc.backspaceCompare1("ab##", "c#d#"));
//        System.out.println(bsc.backspaceCompare1("xywrrmp", "xywrrmu#p"));
        System.out.println(bsc.backspaceCompare1("nzp#o#g", "b#nzp#o#g"));
    }

}
