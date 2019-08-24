package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 249 Group Shifted Strings 群组偏移字符串
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
 * "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
 *
 * For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 * Return:
 * [
 *   ["abc","bcd","xyz"],
 *   ["az","ba"],
 *   ["acef"],
 *   ["a","z"]
 * ]
 *
 * Note: For the return value, each inner list's elements must follow the lexicographic order.
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
