package cn.leetcode.xux.general.hard;

/**
 * 248 Strobogrammatic Number III 对称数之三
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
 *
 * Example:
 * Input: low = "50", high = "100"
 * Output: 3
 * Explanation: 69, 88, and 96 are three strobogrammatic numbers.
 *
 * Note:
 * Because the range might be a large number, the lowand high numbers are represented as string.
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
