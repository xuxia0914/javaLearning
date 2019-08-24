package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 282. 给表达式添加运算符
 *
 * 给定一个仅包含数字 0-9 的字符串和一个目标值，在数字之间添加二元运算符（不是一元）+、- 或 * ，返回所有能够得到目标值的表达式。
 *
 * 示例 1:
 * 输入: num = "123", target = 6
 * 输出: ["1+2+3", "1*2*3"]
 * 示例 2:
 * 输入: num = "232", target = 8
 * 输出: ["2*3+2", "2+3*2"]
 * 示例 3:
 * 输入: num = "105", target = 5
 * 输出: ["1*0+5","10-5"]
 * 示例 4:
 * 输入: num = "00", target = 0
 * 输出: ["0+0", "0-0", "0*0"]
 * 示例 5:
 * 输入: num = "3456237490", target = 9191
 * 输出: []
 */
public class ExpressionAddOperators {

    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        helper(res, "", 0l, 0l, target, num);
        return res;
    }

    void helper(List<String> res, String currStr, long currNum, long preNum, long target, String num) {
        if(num.length()==0) {
            if(target==currNum) {
                res.add(currStr);
            }
            return;
        }
        for(int i=1;i<=num.length();i++) {
            String tmp = num.substring(0, i);
            long val = Long.valueOf(tmp);
            if(currStr.length()==0) {
                helper(res, tmp, val, val, target, num.substring(i));
            }else {
                helper(res, currStr+'+'+tmp, currNum+val, val, target, num.substring(i));
                helper(res, currStr+'-'+tmp, currNum-val, -val, target, num.substring(i));
                helper(res, currStr+'*'+tmp, currNum-preNum+preNum*val, preNum*val, target, num.substring(i));
            }
            if(val==0l) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new ExpressionAddOperators().addOperators("123", 6));
    }

}
