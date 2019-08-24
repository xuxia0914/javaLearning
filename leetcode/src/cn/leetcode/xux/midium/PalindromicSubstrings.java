package cn.leetcode.xux.midium;

public class PalindromicSubstrings {

    public static int solution(String s) {
        if(s==null||s.length()==0) {
            return 0;
        }
        char[] ss = s.toCharArray();
        int res= 0;
        for(int i=0;i<ss.length;i++) {
            res += helper(i, i, ss)+helper(i, i+1, ss);
        }
        return res;
    }

    public static int helper(int left, int right, char[] ss) {
        int res = 0;
        while(left>=0&&right<ss.length) {
            if(ss[left]==ss[right]) {
                res++;
                left--;
                right++;
            }else {
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(solution("abc"));
        System.out.println(solution("aaa"));
    }

}
