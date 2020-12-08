package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 842. 将数组拆分成斐波那契序列
 * 给定一个数字字符串 S，比如 S = "123456579"，
 * 我们可以将它分成斐波那契式的序列 [123, 456, 579]。
 * 形式上，斐波那契式序列是一个非负整数列表 F，且满足：
 * 0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
 * F.length >= 3；
 * 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
 * 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
 * 返回从 S 拆分出来的所有斐波那契式的序列块，如果不能拆分则返回 []。
 *
 * 示例 1：
 * 输入："123456579"
 * 输出：[123,456,579]
 *
 * 示例 2：
 * 输入: "11235813"
 * 输出: [1,1,2,3,5,8,13]
 *
 * 示例 3：
 * 输入: "112358130"
 * 输出: []
 * 解释: 这项任务无法完成。
 *
 * 示例 4：
 * 输入："0123"
 * 输出：[]
 * 解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。
 *
 * 示例 5：
 * 输入: "1101111"
 * 输出: [110, 1, 111]
 * 解释: 输出 [11,0,11,11] 也同样被接受。
 *
 * 提示：
 * 1 <= S.length <= 200
 * 字符串 S 中只含有数字。
 */
public class SplitArrayIntoFibonacciSequence {

    public List<Integer> splitIntoFibonacci(String S) {
        if(S==null||S.length()<3) {
            return new ArrayList<>();
        }
        int len = S.length();
        for(int i=1;len-i>i;i++) {
            String num1 = S.substring(0, i);
            if(!isInt(num1)) {
                break;
            }
            for(int j=i+1;len-j>=Math.max(i, j-i);j++) {
                String num2 = S.substring(i, j);
                if(!isInt(num2)) {
                    break;
                }
                List<Integer> res = new ArrayList<>();
                res.add(Integer.valueOf(num1));
                res.add(Integer.valueOf(num2));
                res = helper(num1, num2, S.substring(j), res);
                if(res.size()>0) {
                    return res;
                }
                if(num2.equals("0")) {
                    break;
                }
            }
            if(num1.equals("0")) {
                break;
            }
        }
        return new ArrayList<>();
    }

    public List<Integer> helper(String num1, String num2, String num, List<Integer> curr) {
        if(num.length()<Math.max(num1.length(), num2.length())) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>(curr);
        String sum = sum(num1, num2);
        if(num.equals(sum)&&isInt(sum)) {
            list.add(Integer.valueOf(sum));
            return list;
        }else if(num.startsWith(sum)&&isInt(sum)) {
            list.add(Integer.valueOf(sum));
            return helper(num2, sum, num.substring(sum.length()), list);
        }else {
            return new ArrayList<>();
        }
    }

    public String sum(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int idx1 = num1.length()-1;
        int idx2 = num2.length()-1;
        int curr;
        int carry = 0;
        while(idx1>=0||idx2>=0) {
            curr = carry;
            if(idx1>=0) {
                curr += num1.charAt(idx1)-'0';
            }
            if(idx2>=0) {
                curr += num2.charAt(idx2)-'0';
            }
            carry = curr/10;
            res.append(curr%10);
            idx1--;
            idx2--;
        }
        if(carry>0) {
            res.append(carry);
        }
        return res.reverse().toString();
    }

    public boolean isInt(String s) {
        Long l = Long.valueOf(s);
        if(l>Integer.MAX_VALUE) {
            return false;
        }else {
            return true;
        }
    }

    public static void main(String[] args) {
        SplitArrayIntoFibonacciSequence sa = new SplitArrayIntoFibonacciSequence();
        System.out.println(sa.splitIntoFibonacci("123456579"));
        System.out.println(sa.splitIntoFibonacci("11235813"));
        System.out.println(sa.splitIntoFibonacci("112358130"));
        System.out.println(sa.splitIntoFibonacci("0123"));
        System.out.println(sa.splitIntoFibonacci("1101111"));
    }

}
