package cn.leetcode.xux.midium;

/**
 * Given a string representing arbitrarily nested ternary expressions,
 * calculate the result of the expression.
 * You can always assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F (T and F represent True and False respectively).
 * Note:
 * The length of the given string is ≤ 10000.
 * Each number will contain only one digit.
 * The conditional expressions group right-to-left (as usual in most languages).
 * The condition will always be either T or F. That is, the condition will never be a digit.
 * The result of the expression will always evaluate to either a digit 0-9, T or F.
 * Example 1:
 * Input: "T?2:3"
 * Output: "2"
 * Explanation: If true, then result is 2; otherwise result is 3.
 * Example 2:
 * Input: "F?1:T?4:5"
 * Output: "4"
 * Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
 *              "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
 *           -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
 *           -> "4"                                    -> "4"
 * Example 3:
 * Input: "T?T?F:5:3"
 * Output: "F"
 * Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
 *              "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
 *           -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
 *           -> "F"                                    -> "F"
 */
public class TernaryExpressionParser {

    public static String solution(String s) {
        if(s==null||s.length()==0||s.indexOf("?")==-1) {
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
            return solution(str3);
        }else {
            return solution(str4);
        }
    }

    public static void main(String[] args) {
        System.out.println(solution("T?2:3"));
        System.out.println(solution("F?1:T?4:5"));
        System.out.println(solution("T?T?F:5:3"));
    }

}
