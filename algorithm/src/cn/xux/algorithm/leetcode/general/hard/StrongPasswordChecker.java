package cn.xux.algorithm.leetcode.general.hard;

import java.util.Map;
import java.util.TreeMap;

/**
 * 420. 强密码检验器
 * 一个强密码应满足以下所有条件：
 *
 * 由至少6个，至多20个字符组成。
 * 至少包含一个小写字母，一个大写字母，和一个数字。
 * 同一字符不能连续出现三次 (比如 "...aaa..." 是不允许的, 但是 "...aa...a..." 是可以的)。
 * 编写函数 strongPasswordChecker(s)，s 代表输入字符串，
 * 如果 s 已经符合强密码条件，则返回0；否则返回要将 s 修改为满足强密码条件的字符串所需要进行修改的最小步数。
 *
 * 插入、删除、替换任一字符都算作一次修改。
 */
public class StrongPasswordChecker {

    public static void main(String[] args) {
        System.out.println(new StrongPasswordChecker()
                .strongPasswordChecker("..................!!!"));   //2
//                .strongPasswordChecker("ABABABABABABABABABAB1"));   //2
//                .strongPasswordChecker("dddddaaaaasssssdsdddddsgg")); //8
    }

    public int strongPasswordChecker(String s) {
        if(s==null) {
            return -1;
        }
        int len = s.length();
        if(len==0) {
            return 6;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>(((o1, o2) -> o1%3==o2%3?o1-o2:o1%3-o2%3));
        char pre = s.charAt(0);
        int cnt = 0;
        //增加或者修改的次数
        int add = 0;
        //删除的次数
        int del = 0;
        //大写字母，小写字母，数字 是否包含
        boolean[] typeFlag = new boolean[3];
        for(char c : s.toCharArray()) {
            if(c>='a'&&c<='z') {
                typeFlag[0] = true;
            }else if(c>='A'&&c<='Z') {
                typeFlag[1] = true;
            }else if(c>='0'&&c<='9') {
                typeFlag[2] = true;
            }
            if(c==pre) {
                cnt++;
            }else {
                if(cnt>2) {
                    map.put(cnt, map.getOrDefault(cnt, 0)+1);
                }
                pre = c;
                cnt = 1;
            }
        }
        if(cnt>2) {
            map.put(cnt, map.getOrDefault(cnt, 0)+1);
        }
        //缺少的类型数
        int needType = 0;
        for(boolean b : typeFlag) {
            needType += !b?1:0;
        }
        while(len<6&&map.size()>0) {
            Map.Entry<Integer, Integer> entry = map.pollFirstEntry();
            int key = entry.getKey();
            int val = entry.getValue();
            if(key>4) {
                int newKey = key-2;
                map.put(newKey, map.getOrDefault(newKey, 0)+1);
            }
            len++;
            if(val>1) {
                map.put(key, val-1);
            }
            add++;
        }
        while(len>20&&map.size()>0) {
            //删除的优先级，连续的len%3==0>len%3==1<len>2
            Map.Entry<Integer, Integer> entry = map.pollFirstEntry();
            int key = entry.getKey();
            int val = entry.getValue();
            if(key>3) {
                int newKey = key-1;
                map.put(newKey, map.getOrDefault(newKey, 0)+1);
            }
            len--;
            if(val>1) {
                map.put(key, val-1);
            }
            del++;
        }
        if(map.size()==0) {
            if(len<6) {
                add += 6-len;
            }else if(len>20) {
                del += len-20;
            }
        }else {
            while(map.size()>0) {
                Map.Entry<Integer, Integer> entry = map.pollFirstEntry();
                int key = entry.getKey();
                int val = entry.getValue();
                add += key/3 * val;
            }
        }
        return Math.max(add, needType)+del;
    }

}
