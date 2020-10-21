package cn.xux.algorithm.leetcode.general.easy;

import java.util.Stack;

/**
 * 844. 比较含退格的字符串
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，
 * 判断二者是否相等，并返回结果。 # 代表退格字符。
 * 注意：如果对空文本输入退格字符，文本继续为空。
 *
 * 示例 1：
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 *
 * 示例 2：
 * 输入：S = "ab##", T = "c#d#"
 * 输出：true
 * 解释：S 和 T 都会变成 “”。
 *
 * 示例 3：
 * 输入：S = "a##c", T = "#a#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “c”。
 *
 * 示例 4：
 * 输入：S = "a#c", T = "b"
 * 输出：false
 * 解释：S 会变成 “c”，但 T 仍然是 “b”。
 *
 * 提示：
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S 和 T 只含有小写字母以及字符 '#'。
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
