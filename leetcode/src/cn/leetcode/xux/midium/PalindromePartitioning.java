package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * Example:
 * Input: "aab"
 * Output:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 */
public class PalindromePartitioning {

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if(s==null||s.length()==0) {
            return res;
        }
        for(int i=1;i<=s.length();i++) {
            String tmp = s.substring(0, i);
            if(isPalindrome(tmp)) {
                List<List<String>> post = partition(s.substring(i));
                if(post.size()==0) {
                    List<String> tmp1 = new ArrayList<>();
                    tmp1.add(tmp);
                    res.add(tmp1);
                }else {
                    for(List<String> list : post) {
                        List<String> tmp1 = new ArrayList<>(list);
                        tmp1.add(0, tmp);
                        res.add(tmp1);
                    }
                }
            }
        }
        return res;
    }

    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length()-1;
        while(left<right) {
            if(s.charAt(left)!=s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartitioning pp = new PalindromePartitioning();
        System.out.println(pp.partition("aab"));
    }

}
