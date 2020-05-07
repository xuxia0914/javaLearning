package cn.leetcode.xux.midium;

/**
 * 984. 不含 AAA 或 BBB 的字符串
 * 给定两个整数 A 和 B，返回任意字符串 S，要求满足：
 * S 的长度为 A + B，且正好包含 A 个 'a' 字母与 B 个 'b' 字母；
 * 子串 'aaa' 没有出现在 S 中；
 * 子串 'bbb' 没有出现在 S 中。
 *
 * 示例 1：
 * 输入：A = 1, B = 2
 * 输出："abb"
 * 解释："abb", "bab" 和 "bba" 都是正确答案。
 *
 * 示例 2：
 * 输入：A = 4, B = 1
 * 输出："aabaa"
 *
 * 提示：
 * 0 <= A <= 100
 * 0 <= B <= 100
 * 对于给定的 A 和 B，保证存在满足要求的 S。
 */
public class StringWithoutAaaOrBbb {

    public static void main(String[] args) {
        System.out.println(new StringWithoutAaaOrBbb().strWithout3a3b(1,2));
    }

    public String strWithout3a3b(int A, int B) {
        int flag = 0;
        StringBuilder sb = new StringBuilder();
        while(A>0||B>0) {
            if(flag>=-1&&flag<=1) {
                if(A>=B) {
                    sb.append("a");
                    A--;
                    flag = flag==1?2:1;
                }else {
                    sb.append("b");
                    B--;
                    flag = flag==-1?-2:-1;
                }
            }else if(flag==-2) {
                sb.append("a");
                A--;
                flag = 1;
            }else {
                sb.append("b");
                B--;
                flag = -1;
            }
        }
        return sb.toString();
    }



}
