package cn.xux.algorithm.leetcode.vip.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 604. 迭代压缩字符串
 * 对于一个压缩字符串，设计一个数据结构，它支持如下两种操作： next 和 hasNext。
 * 给定的压缩字符串格式为：每个字母后面紧跟一个正整数，这个整数表示该字母在解压后的字符串里连续出现的次数。
 * next() - 如果压缩字符串仍然有字母未被解压，则返回下一个字母，否则返回一个空格。
 * hasNext() - 判断是否还有字母仍然没被解压。
 *
 * 注意：
 * 请记得将你的类在 StringIterator 中 初始化 ，因为静态变量或类变量在多组测试数据中不会被自动清空。
 *
 * Example:
 *
 * StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
 *
 * iterator.next(); // return 'L'
 * iterator.next(); // return 'e'
 * iterator.next(); // return 'e'
 * iterator.next(); // return 't'
 * iterator.next(); // return 'C'
 * iterator.next(); // return 'o'
 * iterator.next(); // return 'd'
 * iterator.hasNext(); // return true
 * iterator.next(); // return 'e'
 * iterator.hasNext(); // return false
 * iterator.next(); // return ' '
 */
public class DesignCompressedStringIterator {

    List<Character> charList;
    List<Integer> cnts;
    int curr;
    int cnt;


    public DesignCompressedStringIterator(String compressedString) {
        charList = new ArrayList<>();
        cnts = new ArrayList<>();
        int idx = 0;
        while(idx<compressedString.length()) {
            charList.add(compressedString.charAt(idx));
            idx++;
            int cnt = 0;
            while(idx<compressedString.length()&&compressedString.charAt(idx)>=0&&compressedString.charAt(idx)<=9) {
                cnt = cnt*10+(compressedString.charAt(idx)-'0');
                idx++;
            }
            cnts.add(cnt);
        }
        curr = 0;
        cnt = 1;
    }

    public char next() {
        char ans = charList.get(curr);
        cnt++;
        while(cnt>cnts.get(curr)) {
            curr++;
            cnt = 1;
        }
        return ans;
    }

    public boolean hasNext() {
        return curr<charList.size();
    }

}

/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator obj = new StringIterator(compressedString);
 * char param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */