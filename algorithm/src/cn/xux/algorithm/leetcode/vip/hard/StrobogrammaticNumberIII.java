package cn.xux.algorithm.leetcode.vip.hard;

/**
 * 248. 中心对称数 III
 * 中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
 * 写一个函数来计算范围在 [low, high] 之间中心对称数的个数。
 *
 * 示例:
 * 输入: low = "50", high = "100"
 * 输出: 3
 * 解释: 69，88 和 96 是三个在该范围内的中心对称数
 *
 * 注意:
 * 由于范围可能很大，所以 low 和 high 都用字符串表示。
 */
public class StrobogrammaticNumberIII {

    int res;

    public int strobogrammaticInRange(String low, String high) {
        res = 0;
        helper(low, high, "");
        helper(low, high, "0");
        helper(low, high, "1");
        helper(low, high, "8");
        return res;
    }

    public void helper(String low, String high, String curr) {
        if(curr.length()>=low.length()&&curr.length()<=high.length()) {
            if(low.length()==high.length()) {
                if(compare(curr, low)&&compare(high, curr)) {
                    res++;
                }
            }else {
                if(curr.length()==low.length()&&compare(curr, low)) {
                    res++;
                }else if(!curr.startsWith("0")&&curr.length()>low.length()&&curr.length()<high.length()) {
                    res++;
                }else if(!curr.startsWith("0")&&curr.length()==high.length()&&compare(high, curr)) {
                    res++;
                }
            }
        }
        if(curr.length()>=high.length()) {
            return;
        }
        helper(low, high, "0"+curr+"0");
        helper(low, high, "1"+curr+"1");
        helper(low, high, "8"+curr+"8");
        helper(low, high, "6"+curr+"9");
        helper(low, high, "9"+curr+"6");
    }

    public boolean compare(String num1, String num2) {
        for(int i=0;i<num1.length();i++) {
            if(num1.charAt(i)>num2.charAt(i)) {
                return true;
            }
            if(num1.charAt(i)<num2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        StrobogrammaticNumberIII sn = new StrobogrammaticNumberIII();
        System.out.println(sn.strobogrammaticInRange("50", "100"));
        System.out.println(sn.strobogrammaticInRange("0", "100"));
    }

}
