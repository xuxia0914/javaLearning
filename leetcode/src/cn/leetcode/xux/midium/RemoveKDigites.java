package cn.leetcode.xux.midium;

/**
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 * Example 1:
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * Example 2:
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * Example 3:
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */
public class RemoveKDigites {

    public static String solution(String num, int k) {
        if(num.length()<=k) {
            return "0";
        }
        String result = num;
        int i = 0, j = 0;   //i表示已经移除的个数，j表示当前遍历到的位置
        while(i<k&&j<result.length()-1) {
            if(result.charAt(j)>result.charAt(j+1)) { //如果当前位置的数大于下一位数，则移除当前位的数
                if(j==0) {
                    result = result.substring(1);
                }else {
                    result = result.substring(0, j)+result.substring(j+1);
                }
                i++;
            }else {
                j++;
            }
        }
        if(i<k) {   //遍历到最后还没移除完k个数，则移除最末端的k-i个数
            result = result.substring(0, result.length()-k+i);
        }
        while(result.startsWith("0")) {
            result = result.substring(1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution("1432219", 3));
        System.out.println(solution("10200", 1));
        System.out.println(solution("10", 2));
        System.out.println(solution("10200123456", 4));
    }

}
