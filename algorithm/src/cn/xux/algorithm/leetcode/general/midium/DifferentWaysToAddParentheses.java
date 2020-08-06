package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 为运算表达式设计优先级
 *
 * 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。你需要给出所有可能的组合的结果
 * 有效的运算符号包含 +, - 以及 * 。
 *
 * 示例 1:
 * 输入: "2-1-1"
 * 输出: [0, 2]
 * 解释:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 *
 * 示例 2:
 * 输入: "2*3-4*5"
 * 输出: [-34, -14, -10, -10, 10]
 * 解释:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 */
public class DifferentWaysToAddParentheses {

    /**
     * 执行用时 : 11 ms, 在所有 Java 提交中击败了38.34%的用户
     * 内存消耗 : 37.7 MB, 在所有 Java 提交中击败了28.24%的用户
     * @param input
     * @return
     */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        if(input==null||input.length()==0) {
            return res;
        }
        int n = input.length();
        boolean flag = true;
        for(int i=0;i<n;i++) {
            if(input.charAt(i)=='*'||input.charAt(i)=='-'||input.charAt(i)=='+') {
                flag = false;
                break;
            }
        }
        if(flag) {
            res.add(Integer.valueOf(input));
            return res;
        }
        for(int i=0;i<n;i++) {
            if(input.charAt(i)=='*'||input.charAt(i)=='-'||input.charAt(i)=='+') {
                List<Integer> leftRes = diffWaysToCompute(input.substring(0, i));
                List<Integer> rightRes = diffWaysToCompute(input.substring(i+1, n));
                if(input.charAt(i)=='*') {
                    for(Integer left : leftRes) {
                        for(Integer right : rightRes) {
                            res.add(left*right);
                        }
                    }
                }
                if(input.charAt(i)=='-') {
                    for(Integer left : leftRes) {
                        for(Integer right : rightRes) {
                            res.add(left-right);
                        }
                    }
                }
                if(input.charAt(i)=='+') {
                    for(Integer left : leftRes) {
                        for(Integer right : rightRes) {
                            res.add(left+right);
                        }
                    }
                }
            }
        }
        return res;
    }


    Map<String, List<Integer>> map = new HashMap<>();

    /**
     * 执行用时 :3 ms, 在所有 Java 提交中击败了96.78%的用户
     * 内存消耗 :5.3 MB, 在所有 Java 提交中击败了83.97%的用户
     * @param input
     * @return
     */
    public List<Integer> diffWaysToCompute1(String input) {
        if(map.containsKey(input)) {
            return map.get(input);
        }
        List<Integer> res = new ArrayList<>();
        if(input==null||input.length()==0) {
            return res;
        }
        int n = input.length();
        boolean flag = true;
        for(int i=0;i<n;i++) {
            if(input.charAt(i)=='*'||input.charAt(i)=='-'||input.charAt(i)=='+') {
                flag = false;
                break;
            }
        }
        if(flag) {
            res.add(Integer.valueOf(input));
            return res;
        }
        for(int i=0;i<n;i++) {
            if(input.charAt(i)=='*'||input.charAt(i)=='-'||input.charAt(i)=='+') {
                List<Integer> leftRes = diffWaysToCompute(input.substring(0, i));
                List<Integer> rightRes = diffWaysToCompute(input.substring(i+1, n));
                if(input.charAt(i)=='*') {
                    for(Integer left : leftRes) {
                        for(Integer right : rightRes) {
                            res.add(left*right);
                        }
                    }
                }
                if(input.charAt(i)=='-') {
                    for(Integer left : leftRes) {
                        for(Integer right : rightRes) {
                            res.add(left-right);
                        }
                    }
                }
                if(input.charAt(i)=='+') {
                    for(Integer left : leftRes) {
                        for(Integer right : rightRes) {
                            res.add(left+right);
                        }
                    }
                }
            }
        }
        map.put(input, res);
        return res;
    }

}
