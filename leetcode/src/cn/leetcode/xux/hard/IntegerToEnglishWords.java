package cn.leetcode.xux.hard;

/**
 * 整数转为英文单词
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 * For example,
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * Hint:
 * Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
 * Group the number by thousands (3 digits).
 * You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
 * There are many edge cases. What are some good test cases?
 * Does your code work with input such as 0? Or 1000010? (middle chunk is zero and should not be printed out)
 */
public class IntegerToEnglishWords {

    public static String solution(int num) {
        if(num==0) {
            return "zero";
        }
        String[] digits = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seveneteen", "eighteen", "nineteen"};
        String[] tens = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        String[] ss = {"", "thousand", "million", "billion"};
        StringBuffer sb = new StringBuffer();
        for(int i=ss.length-1;i>=0;i--) {
            int tmp1 = num;
            for(int j=0;j<i;j++) {
                tmp1 = tmp1/1000;
            }
            if(tmp1>0) {
                tmp1 = tmp1%1000;
                if(!"".equals(sb.toString())&&!sb.toString().endsWith(" ")) {
                    sb.append(" ");
                }
                if(tmp1>100) {
                    if(tmp1%100>20) {
                        sb.append(digits[tmp1/100-1]).append(" hundred and ").append(tens[tmp1%100/10-2]);
                        if(tmp1%10>0) {
                            sb.append(" ").append(digits[tmp1%10-1]);
                        }
                    }else if(tmp1%100>0) {
                        sb.append(digits[tmp1/100-1]).append(" hundred and ").append(digits[tmp1%20-1]);
                    }
                    sb.append(" ").append(ss[i]);
                }else if(tmp1%100>20) {
                    sb.append(tens[tmp1/10-2]);
                    if(tmp1%10>0) {
                        sb.append(" ").append(digits[tmp1%10-1]);
                    }
                    sb.append(" ").append(ss[i]);
                }else if(tmp1%100>0) {
                    sb.append(digits[tmp1-1]);
                    sb.append(" ").append(ss[i]);
                }
            }
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(solution(0));
        System.out.println(solution(123));
        System.out.println(solution(12345));
        System.out.println(solution(1234567));
        System.out.println(solution(1000010));
    }

}
