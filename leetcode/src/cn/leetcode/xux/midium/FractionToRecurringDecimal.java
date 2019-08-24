package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * Example 1:
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 * Example 2:
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 * Example 3:
 * Input: numerator = 2, denominator = 3
 * Output: "0.(6)"
 */
public class FractionToRecurringDecimal {

    public String fractionToDecimal(int numerator, int denominator) {
        if(denominator==0) {
            return "";
        }
        if(numerator==0) {
            return "0";
        }
        boolean sign = true;
        long l1 = (long)numerator;
        long l2 = (long)denominator;
        if(l1<0) {
            sign = !sign;
            l1 = -l1;
        }
        if(l2<0) {
            sign = !sign;
            l2 = -l2;
        }
        String res = fractionToDecimal(l1, l2);
        return sign?res:"-"+res;
    }

    public String fractionToDecimal(long numerator, long denominator) {
        StringBuilder sb = new StringBuilder();
        long a = numerator/denominator;
        sb.append(a);
        long b = numerator%denominator;
        if(b==0) {
            return sb.toString();
        }
        sb.append('.');
        List<Long> list = new ArrayList<>();
        while(!list.contains(b)&&b!=0) {
            list.add(b);
            b = b*10%denominator;
        }
        if(b==0) {
            for(int i=0;i<list.size();i++) {
                sb.append(list.get(i)*10/denominator);
            }
        }else {
            for(int i=0;i<list.indexOf(b);i++) {
                sb.append(list.get(i)*10/denominator);
            }
            sb.append('(');
            for(int i=list.indexOf(b);i<list.size();i++) {
                sb.append(list.get(i)*10/denominator);
            }
            sb.append(')');
        }
        return sb.toString();
    }

}
