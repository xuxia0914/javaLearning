package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 1256：加密数字
 * 加密的过程是把一个整数用某个未知函数进行转化，你需要从下表推测出该转化函数：
 * 0->"", 1->"0", 2->"1", 3->"00", 4->"01", 5->"10", 6->"11", 7->"000", 8->"0001",
 * 示例 1：
 * 输入：num = 23
 * 输出："1000"
 *
 * 示例 2：
 * 输入：num = 107
 * 输出："101100"
 *
 * 提示：
 * 0 <= num <= 10^9
 */
public class EncodeNumber {

    public static void main(String[] args) {
        EncodeNumber en = new EncodeNumber();
        System.out.println(en.encode(0));
        System.out.println(en.encode(1));
        System.out.println(en.encode(2));
        System.out.println(en.encode(3));
        System.out.println(en.encode(4));
        System.out.println(en.encode(5));
        System.out.println(en.encode(6));
        System.out.println(en.encode(7));
        System.out.println(en.encode(8));
        System.out.println(en.encode(23));
        System.out.println(en.encode(107));
    }

    public String encode(int num) {
        StringBuilder sb = new StringBuilder();
        while(num>0) {
            if(num%2==0) {
                sb.append(1);
                num = num/2-1;
            }else {
                sb.append(0);
                num >>= 1;
            }
        }
        return sb.reverse().toString();
    }

}
