package cn.leetcode.xux.easy;

/**
 *实现 atoi，将字符串转为整数。
 *
 * 提示：仔细考虑所有输入情况。如果你想挑战自己，请不要看下面并自己考虑所有可能的输入情况。
 *
 * 说明：这题解释的比较模糊（即没有指定输入格式）。你得事先汇集所有的输入情况。
 *
 * atoi的要求：
 *
 * 这个函数需要丢弃之前的空白字符，直到找到第一个非空白字符。之后从这个字符开始，选取一个可选的正号或负号后面跟随尽可能多的数字，并将其解释为数字的值。
 * 字符串可以在形成整数的字符后包括多余的字符，将这些字符忽略，这些字符对于函数的行为没有影响。
 * 如果字符串中的第一个非空白的字符不是有效的整数，或者没有这样的序列存在，字符串为空或者只包含空白字符则不进行转换。
 * 如果不能执行有效的转换，则返回 0。如果正确的值超过的可表示的范围，则返回 INT_MAX（2147483647）或 INT_MIN（-2147483648）。
 */

public class AToI {

    public static int solution(String s) {
        if(s==null) {
            return 0;
        }
        s = s.trim();
        if(s.equals("")) {
            return 0;
        }
        StringBuffer sb = new StringBuffer();
        boolean valueConfirm = false;
        boolean isPositive = true;
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if(!valueConfirm) {
                if(c=='-') {
                    isPositive = false;
                }else if('0'<c&&c<='9') {
                    valueConfirm = true;
                    sb.append(c);
                    continue;
                }else {
                    return 0;
                }
            }else {
                if('0'<=c&&c<='9') {
                    sb.append(c);
                    if(sb.length()>10) {
                        break;
                    }
                    continue;
                }else {
                    break;
                }
            }
        }
        Long l = Long.valueOf(sb.toString());
        if(isPositive&&l>=Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }else if(!isPositive&&l>Integer.MAX_VALUE){
            return Integer.MIN_VALUE;
        }
        return Integer.valueOf(isPositive?sb.toString():("-"+sb.toString()));
    }

    public static int myAtoi(String str) {
        if(str==null||"".equals(str)) {
            return 0;
        }
        StringBuffer sb = new StringBuffer();
        boolean positive = true;
        for(int i=0;i<str.length();i++) {
            if('-'==str.charAt(i)) {
                positive = !positive;
            }else if(str.charAt(i)=='0'&&sb.length()!=0) {
                sb.append(str.charAt(i));
            }else if(str.charAt(i)>'0'&&str.charAt(i)<='9') {
                sb.append(str.charAt(i));
            }else if(' '==str.charAt(i)) {
                continue;
            }else {
                return 0;
            }
        }
        if(sb.length()==0) {
            return 0;
        }
        try {
            if(positive) {
                return Integer.parseInt(sb.toString());
            }else {
                return Integer.parseInt("-"+sb.toString());
            }
        }catch(NumberFormatException e) {
            if(positive) {
                return Integer.MAX_VALUE;
            }else {
                return Integer.MIN_VALUE;
            }
        }
    }

    public static void main(String[] args) {
        /*System.out.println(solution(" -42sada"));
        System.out.println(solution("42dasdasd"));
        System.out.println(solution("5634523423434sdasd"));
        System.out.println(solution("  -342346789327489asd"));
        System.out.println(solution("  -2147483648asd"));
        System.out.println(solution("  -2147483649asd"));
        System.out.println(solution("  2147483647asds"));
        System.out.println(solution("  2147483649asds"));*/
        System.out.println(myAtoi(" -42"));
    }

}
