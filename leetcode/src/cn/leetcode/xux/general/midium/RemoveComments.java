package cn.leetcode.xux.general.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 移除注释
 */
public class RemoveComments {

    public List<String> solution(String[] source) {
        List<String> result = new ArrayList<>();
        if(source==null||source.length==0) {
            return result;
        }
        String tmp;
        for(String s : source) {
            tmp = helper(s);
            if(!"".equals(tmp)) {
                result.add(tmp);
            }
        }
        return result;
    }

    String pre = "";
    boolean flag = false;

    public String helper(String s) {
        if(s==null||"".equals(s)) {
            return "";
        }
        if(flag) {
            if(s.contains("*/")) {
                flag = false;
                return pre + helper(s.substring(s.indexOf("*/")+2));
            }else {
                return "";
            }
        }else {
            if(s.contains("/*") && s.contains("//")) {
                if(s.indexOf("//") < s.indexOf("/*")) {
                    if (!"".equals(s.substring(0, s.indexOf("//")))) {
                        return helper(s.substring(0, s.indexOf("//")));
                    }else {
                        return "";
                    }
                } else {
                    flag = true;
                    pre = s.substring(0, s.indexOf("/*"));
                    return helper(s.substring(s.indexOf("/*")+2));
                }
            } else if (s.contains("//")) {
                if (!"".equals(s.substring(0, s.indexOf("//")))) {
                    return helper(s.substring(0, s.indexOf("//")));
                }else {
                    return "";
                }
            } else if (s.contains("/*")) {
                flag = true;
                pre = s.substring(0, s.indexOf("/*"));
                return helper(s.substring(s.indexOf("/*")+2));
            } else {
                return s;
            }
        }
    }

    public static void main(String[] args) {
        RemoveComments rc = new RemoveComments();
//        System.out.println(rc.solution(new String[]{"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"}));
//        System.out.println(rc.solution(new String[]{"a/*comment", "line", "more_comment*/b"}));
//        System.out.println(rc.solution(new String[]{"struct Node{", "    /*/ declare members;/**/", "    int size;", "    /**/int val;", "};"}));
//        System.out.println(rc.solution(new String[]{"void func(int k) {", "// this function does nothing /*", "   k = k*2/4;", "   k = k/2;*/", "}"}));
//        System.out.println(rc.solution(new String[]{"a/*/b//*c","blank","d/*/e*//f"}));
        System.out.println(rc.solution(new String[]{"a//*b//*c","blank","d/*/e*//f"}));
    }

}
