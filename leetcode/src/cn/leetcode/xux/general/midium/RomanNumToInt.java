package cn.leetcode.xux.general.midium;

/**
 * Ⅰ（1）、Ⅴ（5）、Ⅹ（10）、L（50）、C（100）、D（500）、M（1000）
 * Input: "III"
 * Output: 3
 *
 * Example 2:
 * Input: "IV"
 * Output: 4
 *
 * Example 3:
 * Input: "IX"
 * Output: 9
 *
 * Example 4:
 * Input: "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 *
 * Example 5:
 * Input: "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */

public class RomanNumToInt {

    public static int solution(String romNum) {
        String s = "MDCLXVI";
        int[] is = new int[]{1000, 500, 100, 50, 10, 5, 1};

        int result = 0;

        for(int i=0;i<romNum.length();i++) {
            char c = romNum.charAt(i);
            if(s.indexOf(c)>1&&(i!=romNum.length()-1)&&(romNum.charAt(i+1)==s.charAt(s.indexOf(c)-1)
                    ||romNum.charAt(i+1)==s.charAt(s.indexOf(c)-2))) {
                result -= is[s.indexOf(c)];
            }else {
                result += is[s.indexOf(c)];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution("III"));
        System.out.println(solution("IV"));
        System.out.println(solution("IX"));
        System.out.println(solution("LVIII"));
        System.out.println(solution("MCMXCIV"));
    }

}
