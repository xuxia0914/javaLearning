package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1702. 修改后的最大二进制字符串
 * 给你一个二进制字符串 binary ，它仅有 0 或者 1 组成。
 * 你可以使用下面的操作任意次对它进行修改：
 * 操作 1 ：如果二进制串包含子字符串 "00" ，你可以用 "10" 将其替换。
 * 比方说， "00010" -> "10010"
 * 操作 2 ：如果二进制串包含子字符串 "10" ，你可以用 "01" 将其替换。
 * 比方说， "00010" -> "00001"
 * 请你返回执行上述操作任意次以后能得到的 最大二进制字符串 。
 * 如果二进制字符串 x 对应的十进制数字大于二进制字符串 y 对应的十进制数字，
 * 那么我们称二进制字符串 x 大于二进制字符串 y 。
 *
 * 示例 1：
 * 输入：binary = "000110"
 * 输出："111011"
 * 解释：一个可行的转换为：
 * "000110" -> "000101"
 * "000101" -> "100101"
 * "100101" -> "110101"
 * "110101" -> "110011"
 * "110011" -> "111011"
 *
 * 示例 2：
 * 输入：binary = "01"
 * 输出："01"
 * 解释："01" 没办法进行任何转换。
 *
 * 提示：
 * 1 <= binary.length <= 105
 * binary 仅包含 '0' 和 '1' 。
 */
public class MaximumBinaryStringAfterChange {

    public static void main(String[] args) {
        System.out.println(new MaximumBinaryStringAfterChange()
                .maximumBinaryString("01111001100000110010"));
    }

    // 统计0的个数cnt0和第一个0所在的位置first0，最终结果必然只含有一个0，且0的位置在first+cnt0-1
    // 特判：cnt0<2直接返回
    public String maximumBinaryString(String binary) {
        int cnt0 = 0;
        int first0 = -1;
        for(int i=0;i<binary.length();i++) {
            if(binary.charAt(i)=='0') {
                cnt0++;
                if(first0==-1) {
                    first0 = i;
                }
            }
        }
        if(cnt0<2) {
            return binary;
        }
        StringBuilder ans = new StringBuilder();
        int pos0 = first0+cnt0-1;
        for(int i=0;i<binary.length();i++) {
            ans.append(i==pos0?'0':'1');
        }
        return ans.toString();
    }

}
