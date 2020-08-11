package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 439. 三元表达式解析器
 * 给定一个以字符串表示的任意嵌套的三元表达式，计算表达式的值。
 * 你可以假定给定的表达式始终都是有效的并且只包含数字 0-9, ?, :,
 * T 和 F (T 和 F 分别表示真和假）。
 *
 * 注意：
 * 给定的字符串长度 ≤ 10000。
 * 所包含的数字都只有一位数。
 * 条件表达式从右至左结合（和大多数程序设计语言类似）。
 * 条件是 T 和 F其一，即条件永远不会是数字。
 * 表达式的结果是数字 0-9, T 或者 F。
 *
 * 示例 1：
 * 输入： "T?2:3"
 * 输出： "2"
 * 解释： 如果条件为真，结果为 2；否则，结果为 3。
 *
 * 示例 2：
 * 输入： "F?1:T?4:5"
 * 输出： "4"
 * 解释： 条件表达式自右向左结合。使用括号的话，相当于：
 * "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
 * -> "(F ? 1 : 4)"                 或者     -> "(T ? 4 : 5)"
 * -> "4"                                    -> "4"
 *
 * 示例 3：
 * 输入： "T?T?F:5:3"
 * 输出： "F"
 * 解释： 条件表达式自右向左结合。使用括号的话，相当于：
 * "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
 * -> "(T ? F : 3)"                 或者       -> "(T ? F : 5)"
 * -> "F"                                     -> "F"
 */
public class TernaryExpressionParser {

    public String parseTernary(String s) {
        if(s==null||s.length()==0||!s.contains("?")) {
            return s;
        }
        int i1 = s.indexOf("?");
        String str1 = s.substring(0, i1);
        String str2 = s.substring(i1+1);
        int cnt = 0;
        int i2 = 0;
        for(int j=0;j<str2.length();j++) {
            if(cnt==0&&str2.charAt(j)==':') {
                i2 = j;
                break;
            }
            if(str2.charAt(j)=='?') {
                cnt++;
            }
            if(str2.charAt(j)==':') {
                cnt--;
            }
        }
        String str3 = str2.substring(0, i2);
        String str4 = str2.substring(i2+1);

        if(str1.equals("T")) {
            return parseTernary(str3);
        }else {
            return parseTernary(str4);
        }
    }

}
