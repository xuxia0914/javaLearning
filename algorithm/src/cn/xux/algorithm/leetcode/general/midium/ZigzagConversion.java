package cn.xux.algorithm.leetcode.general.midium;

/**
 * 题目描述：
 *
 * 将字符串 "PAYPALISHIRING" 以Z字形排列成给定的行数：（下面这样的形状）
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后按逐行顺序依次排列："PAHNAPLSIIGYIR"
 * convert("PAYPALISHIRING", 3) 应当返回 "PAHNAPLSIIGYIR"
 * convert("PAYPALISHIRING", 4) 应当返回 "PINALSIGYAHRPI"
 */
public class ZigzagConversion {
    public static String solution(String s, int rowNum) {
        StringBuffer sb = new StringBuffer();
        if(rowNum<2) {
            return s;
        }
        for(int i=0;i<s.length();i+=(2*rowNum-2)) { //第一行
            sb.append(s.charAt(i));
        }
        for(int j=1;j<rowNum-1;j++) { //中间rowNum-2行
            for(int i=j;i<s.length();i+=(2*rowNum-2)) {
                sb.append(s.charAt(i));
                if(2*(rowNum-j-2)+2+i<s.length()) {
                    sb.append(s.charAt(2*(rowNum-j-2)+2+i));
                }
            }
        }
        for(int i=rowNum-1;i<s.length();i+=(2*rowNum-2)) {  //最后一行
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(solution("PAYPALISHIRING", 3));
        System.out.println(solution("PAYPALISHIRING", 4));
    }

}
