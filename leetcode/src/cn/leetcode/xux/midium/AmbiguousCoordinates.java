package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 816. 模糊坐标
 * 我们有一些二维坐标，如 "(1, 3)" 或 "(2, 0.5)"，然后我们移除所有逗号，小数点和空格，得到一个字符串S。
 * 返回所有可能的原始字符串到一个列表中。
 * 原始的坐标表示法不会存在多余的零，
 * 所以不会出现类似于"00", "0.0", "0.00", "1.0", "001", "00.01"或一些其他更小的数来表示坐标。
 * 此外，一个小数点前至少存在一个数，所以也不会出现“.1”形式的数字。
 * 最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。
 *
 * 示例 1:
 * 输入: "(123)"
 * 输出: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
 *
 * 示例 2:
 * 输入: "(00011)"
 * 输出:  ["(0.001, 1)", "(0, 0.011)"]
 * 解释: 0.0, 00, 0001 或 00.01 是不被允许的。
 *
 * 示例 3:
 * 输入: "(0123)"
 * 输出: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
 *
 * 示例 4:
 * 输入: "(100)"
 * 输出: [(10, 0)]
 * 解释: 1.0 是不被允许的。
 *
 * 提示:
 * 4 <= S.length <= 12.
 * S[0] = "(", S[S.length - 1] = ")", 且字符串 S 中的其他元素都是数字。
 */
public class AmbiguousCoordinates {

    public static void main(String[] args) {
        AmbiguousCoordinates ac = new AmbiguousCoordinates();
        System.out.println(ac.ambiguousCoordinates("(123)"));
        System.out.println(ac.ambiguousCoordinates("(00011)"));
        System.out.println(ac.ambiguousCoordinates("(0123)"));
        System.out.println(ac.ambiguousCoordinates("(100)"));
    }

    public List<String> ambiguousCoordinates(String S) {
        List<String> result = new ArrayList<>();
        if(S==null||S.length()<4) {
            return result;
        }
        int len = S.length();
        for(int i=2;i<len-1;i++) {
            List<String> pres = strToNum(S.substring(1, i));
            List<String> posts = strToNum(S.substring(i, len-1));
            for(String pre : pres) {
                for(String post : posts) {
                    StringBuilder sb = new StringBuilder();
                    sb.append('(').append(pre).append(", ").append(post).append(')');
                    result.add(sb.toString());
                }
            }
        }
        return result;
    }

    public List<String> strToNum(String s) {
        List<String> result = new ArrayList<>();
        int len = s.length();
        //不添加小数点
        if(len==1||s.charAt(0)!='0') {
            result.add(s);
        }
        //添加小数点
        if(len>1&&s.charAt(len-1)!='0') {
            if(s.charAt(0)=='0') {
                result.add("0."+s.substring(1));
            }else {
                for(int i=1;i<len;i++) {
                    result.add(s.substring(0, i)+'.'+s.substring(i));
                }
            }
        }
        return result;
    }

}
