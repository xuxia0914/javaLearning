package cn.leetcode.xux.midium;

/**
 * Given a string representing an expression of fraction addition and subtraction,
 * you need to return the calculation result in string format.
 * The final result should be irreducible fraction.
 * If your final result is an integer, say 2,
 * you need to change it to the format of fraction that has denominator 1.
 * So in this case, 2 should be converted to 2/1.
 * Example 1:
 * Input:"-1/2+1/2"
 * Output: "0/1"
 * Example 2:
 * Input:"-1/2+1/2+1/3"
 * Output: "1/3"
 * Example 3:
 * Input:"1/3-1/2"
 * Output: "-1/6"
 * Example 4:
 * Input:"5/3+1/3"
 * Output: "2/1"
 * Note:
 * The input string only contains '0' to '9', '/', '+' and '-'. So does the output.
 * Each fraction (input and output) has format ±numerator/denominator.
 * If the first input fraction or the output is positive, then '+' will be omitted.
 * The input only contains valid irreducible fractions,
 * where the numerator and denominator of each fraction will always be in the range [1,10].
 * If the denominator is 1, it means this fraction is actually an integer in a fraction format defined above.
 * The number of given fractions will be in the range [1,10].
 * The numerator and denominator of the final result are guaranteed to be valid and in the range of 32-bit int.
 */
public class FractionAdditionAndSubtraction {

    public static String fractionAddition(String expression) {
        int start = 0;
        int a = 0;
        int b = 1;
        int end = 0;
        int i = expression.indexOf("+", start+1);
        int j = expression.indexOf("-", start+1);
        if(i!=-1&&j!=-1) {
            end = Math.min(i, j);
        }else if(i!=-1) {
            end = i;
        }else {
            end = j;
        }
        String curr;
        String[] tmp = new String[2];
        while(end!=-1) {
            curr = expression.substring(start, end);
            tmp = curr.split("/");
            a = a*Integer.valueOf(tmp[1])+b*Integer.valueOf(tmp[0]);
            b *= Integer.valueOf(tmp[1]);
            start = end;
            i = expression.indexOf("+", start+1);
            j = expression.indexOf("-", start+1);
            if(i!=-1&&j!=-1) {
                end = Math.min(i, j);
            }else if(i!=-1) {
                end = i;
            }else {
                end = j;
            }
        }
        curr = expression.substring(start);
        tmp = curr.split("/");
        a = a*Integer.valueOf(tmp[1])+b*Integer.valueOf(tmp[0]);
        b *= Integer.valueOf(tmp[1]);
        if(a%b==0) {
            return "" + a/b + "/1";
        }
        if(b%a==0) {
            return "" + (a>0?"1/"+b/a:"-1/"+b/-a);
        }
        boolean sign = true;
        if(a<0) {
            a = -a;
            sign = !sign;
        }
        if(b<0) {
            b = -b;
            sign = !sign;
        }
        int c = Math.min(a, b);
        int d = 1;
        for(int k=c/2;k>0;k--) {
            if(a%k==0&&b%k==0) {
                d = Math.abs(k);
                break;
            }
        }
        return (sign?"":"-") + a/d + "/" + b/d;
    }

    public static void main(String[] args) {
//        System.out.println(fractionAddition("-1/2+1/2"));
//        System.out.println(fractionAddition("-1/2+1/2+1/3"));
//        System.out.println(fractionAddition("1/3-1/2"));
//        System.out.println(fractionAddition("5/3+1/3"));
//        System.out.println(fractionAddition("7/2+2/3-3/4"));
        System.out.println(fractionAddition("-1/4-4/5-1/4"));
    }

}
