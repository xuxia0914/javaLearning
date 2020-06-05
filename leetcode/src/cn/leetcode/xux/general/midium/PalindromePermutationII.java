package cn.leetcode.xux.general.midium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 267. Palindrome Permutation II
 * Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.
 *
 * For example:
 * Given s = “aabb”, return [“abba”, “baab”].
 * Given s = “abc”, return [].
 */
public class PalindromePermutationII {

    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
        if(s==null||s.length()==0) {
            return res;
        }
        int len = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0;i<len;i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
        }
        int cnt = 0;
        char[] chars = new char[len/2];
        int j = 0;
        boolean flag = false;
        char c = '0';
        for(Map.Entry<Character, Integer> e : map.entrySet()) {
            if(e.getValue()%2==1) {
                cnt++;
                flag = true;
                c = e.getKey();
            }
            for(int i=0;i<e.getValue()/2;i++) {
                chars[j++] = e.getKey();
            }
        }
        if(cnt>1) {
            return res;
        }
        helper(res, new StringBuilder(), chars, new boolean[chars.length], flag, c);
        return res;
    }

    public void helper(List<String> res, StringBuilder sb, char[] chars,boolean[] flags, boolean flag, char c) {
        if(sb.length()==chars.length) {
            if(flag) {
                res.add(sb.toString()+c+new StringBuilder(sb).reverse());
            }else {
                res.add(sb.toString()+new StringBuilder(sb).reverse());
            }
            return;
        }
        for(int i=0;i<chars.length;i++) {
            if(!flags[i]) {
                flags[i] = true;
                sb.append(chars[i]);
                helper(res, sb, chars, flags, flag, c);
                flags[i] = false;
                sb.deleteCharAt(sb.length()-1);
                while(i<chars.length-1&&chars[i]==chars[i+1]) {
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        PalindromePermutationII pp = new PalindromePermutationII();
        System.out.println(pp.generatePalindromes("aabb"));
        System.out.println(pp.generatePalindromes("abc"));
        System.out.println(pp.generatePalindromes("aabbcd"));
        System.out.println(pp.generatePalindromes("aabbcbb"));
    }

}
