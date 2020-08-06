package cn.xux.algorithm.leetcode.general.hard;

/**
 * 65. 有效数字
 * 验证给定的字符串是否可以解释为十进制数字。
 *
 * 例如:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 *
 * 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：
 *
 * 数字 0-9
 * 指数 - "e"
 * 正/负号 - "+"/"-"
 * 小数点 - "."
 * 当然，在输入中，这些字符的上下文也很重要。
 */
public class ValidNumber {

    public static void main(String[] args) {
        ValidNumber vn = new ValidNumber();
        System.out.println(vn.isNumber("0.."));
        System.out.println(vn.isNumber("121e"));
        System.out.println(vn.isNumber(".111"));
        System.out.println(vn.isNumber("0"));
        System.out.println(vn.isNumber(" 0.1 "));
        System.out.println(vn.isNumber("abc"));
        System.out.println(vn.isNumber(" -90e3   "));
        System.out.println(vn.isNumber("-+3"));
        System.out.println(vn.isNumber("95a54e53"));
    }

    public boolean isNumber(String s) {
        if(s==null||s.trim().length()==0) {
            return false;
        }
        s = s.trim();
        String[] ss = s.split("e", -1);
        if(ss.length==0||ss.length>2) {
            return false;
        }
        boolean pre = isNum(ss[0]);
        if(ss.length==1) {
            return pre;
        }else {
            boolean post = ss[1].startsWith("+")||ss[1].startsWith("-")?isInteger(ss[1].substring(1)):isInteger(ss[1]);
            return pre&&post;
        }
    }
    public boolean isNum(String s) {
        if(s.length()==0) {
            return false;
        }
        if(s.startsWith("+")||s.startsWith("-")) {
            s = s.substring(1);
        }
        String[] ss = s.split("\\.", -1);
        if(ss.length==0||ss.length>2) {
            return false;
        }
        boolean pre = isInteger(ss[0]);
        if(ss.length==1) {
            return isInteger(ss[0]);
        }else {
            boolean post = isInteger(ss[1]);
            return (ss[0].length()==0&&post)||(ss[1].length()==0&&pre)||(pre&&post);
        }
    }
    public boolean isInteger(String s) {
        if(s.length()==0) {
            return false;
        }
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if(c>'9'||c<'0') {
                return false;
            }
        }
        return true;
    }

}
