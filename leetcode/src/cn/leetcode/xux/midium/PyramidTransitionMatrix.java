package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * We are stacking blocks to form a pyramid. Each block has a color which is a one letter string.
 * We are allowed to place any color block C on top of two adjacent blocks of colors A and B,
 * if and only if ABC is an allowed triple.
 * We start with a bottom row of bottom, represented as a single string.
 * We also start with a list of allowed triples allowed.
 * Each allowed triple is represented as a string of length 3.
 * Return true if we can build the pyramid all the way to the top, otherwise false.
 * Example 1:
 * Input: bottom = "BCD", allowed = ["BCG", "CDE", "GEA", "FFF"]
 * Output: true
 * Explanation:
 * We can stack the pyramid like this:
 *     A
 *    / \
 *   G   E
 *  / \ / \
 * B   C   D
 * We are allowed to place G on top of B and C because BCG is an allowed triple.
 * Similarly, we can place E on top of C and D, then A on top of G and E.
 * Example 2:
 * Input: bottom = "AABA", allowed = ["AAA", "AAB", "ABA", "ABB", "BAC"]
 * Output: false
 * Explanation:
 * We can't stack the pyramid to the top.
 * Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.
 * Note:
 * bottom will be a string with length in range [2, 8].
 * allowed will have length in range [0, 200].
 * Letters in all strings will be chosen from the set {'A', 'B', 'C', 'D', 'E', 'F', 'G'}.
 */
public class PyramidTransitionMatrix {

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        if(allowed==null||allowed.size()==0) {
            return false;
        }
        if(bottom.length()==1) {
            return true;
        }
        List<String> list = helper(bottom, allowed);
        for(String s : list) {
            if(pyramidTransition(s, allowed)) {
                return true;
            }
        }
        return false;
    }

    public List<String> helper(String s, List<String> allowed) {
        List<String> res = new ArrayList<>();
        if(s.length()==2) {
            for(String str : allowed) {
                if(s.equals(str.substring(0, 2))) {
                    res.add(str.substring(2));
                }
            }
            return res;
        }
        List<String> posts = helper(s.substring(1), allowed);
        String pre = s.substring(0, 2);
        for(String str : allowed) {
            if(pre.equals(str.substring(0, 2))) {
                for(String post : posts) {
                    res.add(str.substring(2)+post);
                }
            }
        }
        return res;
    }

}
