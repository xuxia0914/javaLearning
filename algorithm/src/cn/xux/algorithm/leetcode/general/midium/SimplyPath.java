package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 简化路径
 * 给定一个文档 (Unix-style) 的完全路径，请进行路径简化。
 * 例如，
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * 边界情况:
 * 你是否考虑了 路径 = "/../" 的情况？
 * 在这种情况下，你需返回 "/" 。
 * 此外，路径中也可能包含多个斜杠 '/' ，如 "/home//foo/" 。
 * 在这种情况下，你可忽略多余的斜杠，返回 "/home/foo" 。
 */
public class SimplyPath {

    public static String solution(String path) {
        if(path.charAt(0)!='/') {
            return "";
        }
        List<String> sa = Arrays.asList(path.split("/"));
        Stack<String> ss = new Stack<String>();
        for(int i=0;i<sa.size();i++) {
            if(".".equals(sa.get(i))
                    ||"".equals(sa.get(i))) {
                continue;
            }
            if("..".equals(sa.get(i))) {
                ss.pop();
            }else {
                ss.push(sa.get(i));
            }
        }
        String result = "";
        while(!ss.isEmpty()) {
            result = "/"+ss.pop()+result;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution("/home/"));
        System.out.println(solution("/a/./b/../../c/"));
        System.out.println(solution("/home//foo/"));
    }

}
