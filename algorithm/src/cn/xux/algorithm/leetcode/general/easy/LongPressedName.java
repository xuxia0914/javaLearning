package cn.xux.algorithm.leetcode.general.easy;

/**
 * 925. 长按键入
 * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
 * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
 *
 * 示例 1：
 * 输入：name = "alex", typed = "aaleex"
 * 输出：true
 * 解释：'alex' 中的 'a' 和 'e' 被长按。
 *
 * 示例 2：
 * 输入：name = "saeed", typed = "ssaaedd"
 * 输出：false
 * 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
 *
 * 示例 3：
 * 输入：name = "leelee", typed = "lleeelee"
 * 输出：true
 *
 * 示例 4：
 * 输入：name = "laiden", typed = "laiden"
 * 输出：true
 * 解释：长按名字中的字符并不是必要的。
 *
 * 提示：
 * name.length <= 1000
 * typed.length <= 1000
 * name 和 typed 的字符都是小写字母。
 */
public class LongPressedName {

    public static void main(String[] args) {
        System.out.println(new LongPressedName().isLongPressedName(
                "alex", "aaleex"
        ));
    }

    public boolean isLongPressedName(String name, String typed) {
        if(name==null||typed==null
                ||typed.length()<name.length()) {
            return false;
        }
        int len1 = name.length();
        int len2 = typed.length();
        if(len1==0&&len2==0) {
            return true;
        }
        int idx1 = 0;
        int idx2 = 0;
        while(idx1<len1&&idx2<len2) {
            if(name.charAt(idx1++)!=typed.charAt(idx2++)) {
                return false;
            }
            int cnt1 = 1;
            while(idx1<len1&&name.charAt(idx1)==name.charAt(idx1-1)) {
                cnt1++;
                idx1++;
            }
            int cnt2 = 1;
            while(idx2<len2&&typed.charAt(idx2)==typed.charAt(idx2-1)) {
                cnt2++;
                idx2++;
            }
            if(cnt2<cnt1) {
                return false;
            }
        }
        return idx1==len1&&idx2==len2;
    }

}
