package cn.leetcode.xux.midium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 402. 移掉K位数字
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 *
 * 注意:
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 *
 * 示例 1 :
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 *
 * 示例 2 :
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 *
 * 示例 3 :
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 */
public class RemoveKDigites {

    public String removeKdigits(String num, int k) {
        if(num.length()<=k) {
            return "0";
        }
        int n = num.length();
        Deque<Character> deque = new LinkedList<>();
        for(int i=0;i<n;i++) {
            while(k>0&&!deque.isEmpty()&&deque.peekLast()>num.charAt(i)) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(num.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }
        String result = sb.toString();
        if(k>0) {
            result = result.substring(0, result.length()-k);
        }
        int i = 0;
        while(i<result.length()&&result.charAt(i)=='0') {
            i++;
        }
        result = result.substring(i);
        return result.equals("")?"0":result;
    }

    public String removeKdigits1(String num, int k) {
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
                if(j!=0) {
                    j--;
                }
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
        return result.equals("")?"0":result;
    }

    public static void main(String[] args) {
        RemoveKDigites rkd = new RemoveKDigites();
//        System.out.println(rkd.removeKdigits("1432219", 3));
//        System.out.println(rkd.removeKdigits("10200", 1));
//        System.out.println(rkd.removeKdigits("10", 2));
//        System.out.println(rkd.removeKdigits("10200123456", 4));
//        System.out.println(rkd.removeKdigits1("1234567890", 9));
        System.out.println(rkd.removeKdigits1("1173", 2));
    }

}
