package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implement a magic directory with buildDict, and search methods.
 * For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.
 * For the method search, you'll be given a word,
 * and judge whether if you modify exactly one character into another character in this word,
 * the modified word is in the dictionary you just built.
 * Example 1:
 * Input: buildDict(["hello", "leetcode"]), Output: Null
 * Input: search("hello"), Output: False
 * Input: search("hhllo"), Output: True
 * Input: search("hell"), Output: False
 * Input: search("leetcoded"), Output: False
 * Note:
 * You may assume that all the inputs are consist of lowercase letters a-z.
 * For contest purpose, the test data is rather small by now.
 * You could think about highly efficient algorithm after the contest.
 * Please remember to RESET your class variables declared in class MagicDictionary,
 * as static/class variables are persisted across multiple test cases. Please see here for more details.
 */
public class MagicDictionary {
    Map<Integer, List<String>> dictionary = new HashMap<Integer, List<String>>();

    /** Initialize your data structure here. */
    public MagicDictionary() {

    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        if(dict==null||dict.length==0) {
            return;
        }
        for(String s : dict) {
            List tmp = dictionary.getOrDefault(s.length(), new ArrayList<String>());
            tmp.add(s);
            dictionary.put(s.length(), tmp);
        }
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        if(word==null||"".equals(word)) {
            return false;
        }
        int n = word.length();
        List<String> list = dictionary.get(n);
        if(list!=null&&list.size()>0) {
            int count;
            for(String s : list) {
                count = 0;
                for(int i=0;i<n;i++) {
                    if(s.charAt(i)!=word.charAt(i)) {
                        if(++count>1) {
                            break;
                        }
                    }
                }
                if(count==1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MagicDictionary md = new MagicDictionary();
        md.buildDict(new String[]{"hello","leetcode"});
        System.out.println(md.search("hello"));
        System.out.println(md.search("hhllo"));
        System.out.println(md.search("hell"));
        System.out.println(md.search("leetcoded"));
    }

}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 *
 * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
 * [[], [["hello","leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
 */
