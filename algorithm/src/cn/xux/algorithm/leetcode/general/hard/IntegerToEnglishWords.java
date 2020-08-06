package cn.xux.algorithm.leetcode.general.hard;

/**
 * 273. 整数转换英文表示
 * 将非负整数转换为其对应的英文表示。可以保证给定输入小于 231 - 1 。
 *
 * 示例 1:
 * 输入: 123
 * 输出: "One Hundred Twenty Three"
 *
 * 示例 2:
 * 输入: 12345
 * 输出: "Twelve Thousand Three Hundred Forty Five"
 *
 * 示例 3:
 * 输入: 1234567
 * 输出: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 * 示例 4:
 * 输入: 1234567891
 * 输出: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven
 *          Thousand Eight Hundred Ninety One"
 */
public class IntegerToEnglishWords {

    public String numberToWords(int num) {
        if(num==0) {
            return "Zero";
        }
        String[] digits = {"One", "Two", "Three", "Four", "Five", "Six", "Seven",
                "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
                "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] tens = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy",
                "Eighty", "Ninety"};
        String[] ss = {"", "Thousand", "Million", "Billion"};
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
                        sb.append(digits[tmp1/100-1]).append(" Hundred ").append(tens[tmp1%100/10-2]);
                        if(tmp1%10>0) {
                            sb.append(" ").append(digits[tmp1%10-1]);
                        }
                    }else if(tmp1%100>0) {
                        sb.append(digits[tmp1/100-1]).append(" Hundred ").append(digits[tmp1%20-1]);
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

}
