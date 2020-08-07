package cn.xux.algorithm.leetcode.vip.midium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 288. 单词的唯一缩写（哈希）
 * 一个单词的缩写需要遵循 <起始字母><中间字母数><结尾字母> 这样的格式。
 * 以下是一些单词缩写的范例：
 * a) it                      --> it    (没有缩写)
 *
 *      1
 *      ↓
 * b) d|o|g                   --> d1g
 *
 *               1    1  1
 *      1---5----0----5--8
 *      ↓   ↓    ↓    ↓  ↓
 * c) i|nternationalizatio|n  --> i18n
 *
 *               1
 *      1---5----0
 *      ↓   ↓    ↓
 * d) l|ocalizatio|n          --> l10n
 * 假设你有一个字典和一个单词，请你判断该单词的缩写在这本字典中是否唯一。
 * 若单词的缩写在字典中没有任何 其他 单词与其缩写相同，则被称为单词的唯一缩写。
 *
 * 示例：
 * 给定 dictionary = [ "deer", "door", "cake", "card" ]
 * isUnique("dear") -> false
 * isUnique("cart") -> true
 * isUnique("cane") -> false
 * isUnique("make") -> true
 */
public class UniqueWordAbbreviation {

    Map<String, Set<String>> map;

    public UniqueWordAbbreviation(String[] dictionary) {
        map = new HashMap<>();
        for (String s : dictionary) {
            String abbr = getAbbr(s);
            if (!map.containsKey(abbr)) {
                map.put(abbr, new HashSet<String>());
            }
            map.get(abbr).add(s);
        }
    }

    public boolean isUnique(String word) {
        String abbr = getAbbr(word);
        if (!map.containsKey(abbr) || (map.get(abbr).contains(word) && map.get(abbr).size() == 1)) {
            return true;
        }
        return false;
    }

    private String getAbbr(String s) {
        if (s.length() < 3) {
            return s;
        }
        int len = s.length();
        return s.substring(0, 1) + (len - 2) + s.substring(len - 1);
    }

}
