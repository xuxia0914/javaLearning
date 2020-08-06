package cn.xux.algorithm.leetcode.general.easy;

/**
 * 相似的红绿蓝颜色
 * In the following, every capital letter represents some hexadecimal digit from 0 to f.
 * The red-green-blue color "#AABBCC" can be written as "#ABC" in shorthand.  For example, "#15c" is shorthand for the color "#1155cc".
 * Now, say the similarity between two colors "#ABCDEF" and "#UVWXYZ" is -(AB - UV)^2 - (CD - WX)^2 - (EF - YZ)^2.
 * Given the color "#ABCDEF", return a 7 character color that is most similar to #ABCDEF, and has a shorthand (that is, it can be represented as some "#XYZ"
 * Example 1:
 * Input: color = "#09f166"
 * Output: "#11ee66"
 * Explanation:
 * The similarity is -(0x09 - 0x11)^2 -(0xf1 - 0xee)^2 - (0x66 - 0x66)^2 = -64 -9 -0 = -73.
 * This is the highest among any shorthand color.
 * Note:
 * color is a string of length 7.
 * color is a valid RGB color: for i > 0, color[i] is a hexadecimal digit from 0 to f
 * Any answer which has the same (highest) similarity as the best answer will be accepted.
 * All inputs and outputs should use lowercase letters, and the output is 7 characters.
 */
public class SimilarRGBColor {

    public String similarRGB(String color) {
        StringBuilder sb = new StringBuilder();
        sb.append("#").append(nearest(color.substring(1,3)))
                .append(nearest(color.substring(3,5)))
                .append(nearest(color.substring(5)));
        return sb.toString();
    }

    //Integer.toHexString(int i)  i转成16进制表示的字符串
    public String nearest(String s) {
        Integer i = Integer.valueOf(s, 16);
        int i1 = i/16;
        int a = i1*17;
        int res = a;
        if(i1>0) {
            int b = (i1-1)*17;
            if(Math.abs(b-i)<Math.abs(res-i)) {
                res = b;
            }
        }
        if(i1<15) {
            int c = (i1+1)*17;
            if(Math.abs(c-i)<Math.abs(res-i)) {
                res = c;
            }
        }
        return res<10?"0"+Integer.toHexString(res):Integer.toHexString(res);
    }

    public static void main(String[] args) {
        SimilarRGBColor s = new SimilarRGBColor();
        System.out.println(s.similarRGB("#09f166")); //#11ee66
        System.out.println(s.similarRGB("#66ff00")); //#11ee66
    }

}
