package cn.leetcode.xux.general.midium;

/**
 * To some string S, we will perform some replacement operations that replace groups of letters with new ones (not necessarily the same size).
 * Each replacement operation has 3 parameters: a starting index i, a source word x and a target word y.
 * The rule is that if x starts at position i in the original string S, then we will replace that occurrence of x with y.  If not, we do nothing.
 * For example, if we have S = "abcd" and we have some replacement operation i = 2, x = "cd", y = "ffff",
 * then because "cd" starts at position 2 in the original string S, we will replace it with "ffff".
 * Using another example on S = "abcd", if we have both the replacement operation i = 0, x = "ab", y = "eee",
 * as well as another replacement operation i = 2, x = "ec", y = "ffff",
 * this second operation does nothing because in the original string S[2] = 'c', which doesn't match x[0] = 'e'.
 * All these operations occur simultaneously.
 * It's guaranteed that there won't be any overlap in replacement:
 * for example, S = "abc", indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.
 * Example 1:
 * Input: S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
 * Output: "eeebffff"
 * Explanation: "a" starts at index 0 in S, so it's replaced by "eee".
 * "cd" starts at index 2 in S, so it's replaced by "ffff".
 * Example 2:
 * Input: S = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
 * Output: "eeecd"
 * Explanation: "ab" starts at index 0 in S, so it's replaced by "eee".
 * "ec" doesn't starts at index 2 in the original S, so we do nothing.
 * Notes:
 * 0 <= indexes.length = sources.length = targets.length <= 100
 * 0 < indexes[i] < S.length <= 1000
 * All characters in given inputs are lowercase letters.
 */
public class FindAndReplaceInString {

    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        if(S==null||S.length()==0||indexes==null||indexes.length==0) {
            return S;
        }
        int len = indexes.length;
        sort(indexes, sources, targets, 0, len-1);
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int end;
        String tmp;
        for(int i=0;i<len;i++) {
            if(indexes[i]>start) {
                sb.append(S.substring(start, indexes[i]));
            }
            end = indexes[i]+sources[i].length();
            tmp = S.substring(indexes[i], end);
            if(sources[i].equals(tmp)) {
                sb.append(targets[i]);
            }else {
                sb.append(tmp);
            }
            start = end;
        }
        if(start<S.length()) {
            sb.append(S.substring(start));
        }
        return sb.toString();
    }

    public void sort(int[] indexes, String[] sources, String[] targets, int start, int end) {
        if(start>=end) {
            return;
        }
        int i = start;
        int j = end;
        int key1 = indexes[i];
        String key2 = sources[i];
        String key3 = targets[i];
        int tmp1;
        String tmp2;
        String tmp3;
        while(i<j) {
            while(i<j&&indexes[j]>=key1) {
                j--;
            }
            while(i<j&&indexes[i]<=key1) {
                i++;
            }
            if(i<j) {
                tmp1 = indexes[i];
                indexes[i] = indexes[j];
                indexes[j] = tmp1;
                tmp2 = sources[i];
                sources[i] = sources[j];
                sources[j] = tmp2;
                tmp3 = targets[i];
                targets[i] = targets[j];
                targets[j] = tmp3;
            }
        }
        if(i!=start) {
            indexes[start] = indexes[i];
            indexes[i] = key1;
            sources[start] = sources[i];
            sources[i] = key2;
            targets[start] = targets[i];
            targets[i] = key3;
        }
        sort(indexes, sources, targets, start, i-1);
        sort(indexes, sources, targets, i+1, end);
    }

    public static void main(String[] args) {
        FindAndReplaceInString f = new FindAndReplaceInString();
        /*System.out.println(
                f.findReplaceString(
                    "abcd",
                        new int[]{0,2},
                        new String[]{"a","cd"},
                        new String[]{"eee","ffff"}
                )
        );*/
        System.out.println(
                f.findReplaceString(
                        "vmokgggqzp",
                        new int[]{3,5,1},
                        new String[]{"kg","ggq","mo"},
                        new String[]{"s","so","bfr"}
                )
        );
    }

}