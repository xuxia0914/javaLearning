package cn.leetcode.xux.general.midium;

/**
 * Given two strings representing two complex numbers.
 * You need to return a string representing their multiplication.
 * Note i2 = -1 according to the definition.
 * Example 1:
 * Input: "1+1i", "1+1i"
 * Output: "0+2i"
 * Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i,
 * and you need convert it to the form of 0+2i.
 * Example 2:
 * Input: "1+-1i", "1+-1i"
 * Output: "0+-2i"
 * Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i,
 * and you need convert it to the form of 0+-2i.
 * Note:
 * The input strings will not have extra blank.
 * The input strings will be given in the form of a+bi,
 * where the integer a and b will both belong to the range of [-100, 100].
 * And the output should be also in this form.
 */
public class ComplexNumberMultiplication {

    public String complexNumberMultiply(String a, String b) {
        if(a==null||b==null) {
            return "";
        }
        int a1, a2, b1, b2;
        String[] as = a.split("\\+");
        a1 = Integer.valueOf(as[0]);
        a2 = Integer.valueOf(as[1].substring(0, as[1].length()-1));
        String[] bs = b.split("\\+");
        b1 = Integer.valueOf(bs[0]);
        b2 = Integer.valueOf(bs[1].substring(0, bs[1].length()-1));
        int res1 = a1*b1 - a2*b2;
        int res2 = a1*b2 + a2*b1;
        return res1 + "+" + res2 + "i";
    }

}
