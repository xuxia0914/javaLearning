package cn.xux.algorithm.leetcode.vip.midium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 249. 移位字符串分组（哈希）
 * 给定一个字符串，对该字符串可以进行 “移位” 的操作，
 * 也就是将字符串中每个字母都变为其在字母表中后续的字母，
 * 比如：“abc” -> “bcd”。这样，我们可以持续进行 “移位” 操作，从而生成如下移位序列：
 * "abc" -> "bcd" -> ... -> "xyz"
 * 给定一个包含仅小写字母字符串的列表，将该列表中所有满足 “移位” 操作规律的组合进行分组并返回。
 *
 * 示例：
 * 输入：["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"]
 * 输出：
 * [
 *   ["abc","bcd","xyz"],
 *   ["az","ba"],
 *   ["acef"],
 *   ["a","z"]
 * ]
 * 解释：可以认为字母表首尾相接，所以 'z' 的后续为 'a'，
 * 所以 ["az","ba"] 也满足 “移位” 操作规律。
 */
public class GroupShiftedStrings {

    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strings) {
            if("".equals(str)) {
                if(!map.containsKey("")) {
                    List<String> list = new ArrayList<>();
                    list.add("");
                    map.put("", list);
                }else {
                    map.get("").add(str);
                }
            }else {
                StringBuilder sb = new StringBuilder();
                char first = str.charAt(0);
                for(int i=0;i<str.length();i++) {
                    sb.append((str.charAt(i)-first+26)%26+10);  //保证结果为两位数
                }
                if(!map.containsKey(sb.toString())) {
                    List<String> list = new ArrayList<>();
                    list.add(str);
                    map.put(sb.toString(), list);
                }else {
                    map.get(sb.toString()).add(str);
                }
            }
        }
        for(Map.Entry<String, List<String>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }

    public static void main(String[] args) {
        GroupShiftedStrings gss = new GroupShiftedStrings();
        System.out.println(gss.groupStrings(new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"}));
    }

}
