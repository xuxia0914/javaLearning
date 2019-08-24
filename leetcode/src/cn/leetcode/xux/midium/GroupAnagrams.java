package cn.leetcode.xux.midium;

import java.util.*;

/**
 * Given an array of strings, group anagrams together.
 * Example:
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * Note:
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 */
public class GroupAnagrams {

    /**
     * Runtime: 1135 ms, faster than 5.00% of Java online submissions for Group Anagrams.
     * Memory Usage: 45 MB, less than 34.16% of Java online submissions for Group Anagrams.
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        Map<int[], List<String>> map = new HashMap<>();
        for(String str : strs) {
            int[] array = new int[26];
            for(int i=0;i<str.length();i++) {
                array[str.charAt(i)-'a']++;
            }
            boolean flag = false;
            for(int[] tmp : map.keySet()) {
                if(Arrays.equals(tmp, array)) {
                    List<String> list = map.get(tmp);
                    list.add(str);
                    map.put(tmp, list);
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(array, list);
            }

        }
        for(Map.Entry<int[], List<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    public static void main(String[] args) {
        GroupAnagrams ga = new GroupAnagrams();
        System.out.println(ga.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

}
