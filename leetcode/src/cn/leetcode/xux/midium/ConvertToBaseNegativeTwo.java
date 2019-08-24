package cn.leetcode.xux.midium;

/**
 * 负二进制转换
 * Given a number N, return a string consisting of "0"s and "1"s that represents its value in base -2 (negative two).
 * The returned string must have no leading zeroes, unless the string is "0".
 *
 * Example 1:
 * Input: 2
 * Output: "110"
 * Explantion: (-2) ^ 2 + (-2) ^ 1 = 2
 *
 * Example 2:
 * Input: 3
 * Output: "111"
 * Explantion: (-2) ^ 2 + (-2) ^ 1 + (-2) ^ 0 = 3
 *
 * Example 3:
 * Input: 4
 * Output: "100"
 * Explantion: (-2) ^ 2 = 4
 *
 * Note:
 * 0 <= N <= 10^9
 */
public class ConvertToBaseNegativeTwo {

    public String baseNeg2(int N) {
        StringBuilder sb = new StringBuilder();
        if(N==0) {
            return "0";
        }
        while(N!=1&&N!=-1) {
            if(N%-2==-1) {
                sb.append(1);
                N = N/-2+1;
            }else {
                sb.append(N%-2);
                N = N/-2;
            }
        }
        if(N==1) {
            sb.append(1);
        }else {
            sb.append("11");
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new ConvertToBaseNegativeTwo().baseNeg2(6));
    }

}
