package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用简写
 * Write a function to generate the generalized abbreviations of a word.
 * Example:
 * Given word = "word", return the following list (order does not matter):
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 */
public class GeneralizedAbbreviation {

    public List<String> generateAbbreviations(String word) {
        if(word==null) {
            return null;
        }
        List<String> res = new ArrayList<>();
        if(word.length()==0) {
            res.add("");
            return res;
        }
        if(word.length()==1) {
            res.add("1");
            res.add(word);
            return res;
        }
        char c = word.charAt(0);
        List<String> postRes = generateAbbreviations(word.substring(1));
        for(String s : postRes) {
            res.add(c+s);
            if(s.charAt(0)>='1'&&s.charAt(0)<='9') {
                int end = 1;
                for(int i=1;i<s.length();i++) {
                    if(s.charAt(i)>='0'&&s.charAt(i)<='9') {
                        end++;
                    }else {
                        break;
                    }
                }
                int tmp = Integer.valueOf(s.substring(0, end))+1;
                res.add(tmp+s.substring(end));
            }else {
                res.add("1"+s);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        GeneralizedAbbreviation ga = new GeneralizedAbbreviation();
        System.out.println(ga.generateAbbreviations("word"));
    }

}
