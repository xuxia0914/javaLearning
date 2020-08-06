package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 777. 在LR字符串中交换相邻字符
 * 在一个由 'L' , 'R' 和 'X' 三个字符组成的字符串（例如"RXXLRXRXL"）中进行移动操作。
 * 一次移动操作指用一个"LX"替换一个"XL"，或者用一个"XR"替换一个"RX"。
 * 现给定起始字符串start和结束字符串end，请编写代码，当且仅当存在一系列移动操作使得start可以转换成end时， 返回True。
 *
 * 示例 :
 * 输入: start = "RXXLRXRXL", end = "XRLXXRRLX"
 * 输出: True
 * 解释:
 * 我们可以通过以下几步将start转换成end:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 *
 * 提示：
 * 1 <= len(start) = len(end) <= 10000。
 * start和end中的字符串仅限于'L', 'R'和'X'。
 */
public class SwapAdjacentInLrString {

    public static void main(String[] args) {
        System.out.println(new SwapAdjacentInLrString().canTransform("RXXLRXRXL", "XRLXXRRLX"));
    }

    public boolean canTransform(String start, String end) {
        int len = start.length();
        int si = 0;
        int ei = 0;
        while(si<len||ei<len) {
            while(si<len&&start.charAt(si)=='X') {
                si++;
            }
            while(ei<len&&end.charAt(ei)=='X') {
                ei++;
            }
            if(si==len^ei==len||(si<len&&ei<len&&start.charAt(si)!=end.charAt(ei))) {
                return false;
            }
            if(si<len&&si<len&&((start.charAt(si)=='L'&&si<ei)||(start.charAt(si)=='R'&&si>ei))) {
                return false;
            }
            si++;
            ei++;
        }
        return true;
    }

    //转换不变性 和 可到达性
    public boolean canTransform0(String start, String end) {
        if(!start.replaceAll("X", "").equals(end.replaceAll("X", ""))) {
            return false;
        }
        int li = 0;
        int ri = 0;
        for(int i=0;i<start.length();i++) {
            if(start.charAt(i)=='L') {
                while(end.charAt(li)!='L') {
                    li++;
                }
                if(li++>i) {
                    return false;
                }
            }else if(start.charAt(i)=='R') {
                while(end.charAt(ri)!='R') {
                    ri++;
                }
                if(ri++<i) {
                    return false;
                }
            }
        }
        return true;
    }

    //TLE
    public boolean canTransform1(String start, String end) {
        if(start.equals(end)) {
            return true;
        }
        Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        queue.add(start);
        int len = start.length();
        while(!queue.isEmpty()) {
            String curr = queue.poll();
            for(int i=0;i<len-1;i++) {
                if(curr.substring(i, i+2).equals("XL")
                        ||curr.substring(i, i+2).equals("RX")) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(curr.substring(0, i))
                            .append(curr.charAt(i+1))
                            .append(curr.charAt(i))
                            .append(curr.substring(i+2));
                    String tmp = sb.toString();
                    if(tmp.equals(end)) {
                        return true;
                    }else if(set.add(tmp)) {
                        queue.offer(tmp);
                    }
                }
            }
        }
        return false;
    }

}
