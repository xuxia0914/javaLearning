package cn.xux.algorithm.leetcode.general.midium;

import java.util.*;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T,
 * for example: "ACGAATTCCG". When studying DNA,
 * it is sometimes useful to identify repeated sequences within the DNA.
 * Write a function to find all the 10-letter-long sequences (substrings)
 * that occur more than once in a DNA molecule.
 * Example:
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 */
public class RepeatedDNASequences {

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        if(s==null||s.length()<10) {
            return res;
        }
        Map<String, Integer> map = new HashMap<>();
        String tmp;
        for(int i=0;i<s.length()-9;i++) {
            tmp = s.substring(i, i+10);
            map.put(tmp, map.getOrDefault(tmp, 0)+1);
        }
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue()>1) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

}
