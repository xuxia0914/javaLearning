package cn.leetcode.xux.general.midium;

/**
 * Given two strings S and T, determine if they are both one edit distance apart.
 */
public class OneEditDistance {

    public boolean isOneEditDistance(String s, String t) {
        if(s==null||t==null) {
            return false;
        }
        if(s.equals(t)) {
            return true;
        }
        int lenS = s.length();
        int lenT = t.length();
        if(Math.abs(lenS-lenT)>1) {
            return false;
        }
        if(lenS==lenT) {
            for(int i=0;i<lenS;i++) {
                if(s.charAt(i)!=t.charAt(i)) {
                    return s.substring(i+1).equals(t.substring(i+1));
                }
            }
            return true;
        }else {
            if(lenS-lenT==1) {
                String tmp = s;
                s = t;
                t = tmp;
            }
            for(int i=0;i<s.length();i++) {
                if(s.charAt(i)!=t.charAt(i)) {
                    return s.substring(i).equals(t.substring(i+1));
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        OneEditDistance oed = new OneEditDistance();
        System.out.println(oed.isOneEditDistance("", ""));
        System.out.println(oed.isOneEditDistance("abab", "bab"));
    }

}
