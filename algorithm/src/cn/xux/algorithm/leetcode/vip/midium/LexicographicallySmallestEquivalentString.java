package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 1061-2. 按字典序排列最小的等效字符串
 * 给出长度相同的两个字符串：A 和 B，其中 A[i] 和 B[i] 是一组等价字符。
 * 举个例子，如果 A = "abc" 且 B = "cde"，那么就有 'a' == 'c', 'b' == 'd', 'c' == 'e'。
 * 等价字符遵循任何等价关系的一般规则：
 * 自反性：'a' == 'a'
 * 对称性：'a' == 'b' 则必定有 'b' == 'a'
 * 传递性：'a' == 'b' 且 'b' == 'c' 就表明 'a' == 'c'
 * 例如，A 和 B 的等价信息和之前的例子一样，那么 S = "eed", "acd" 或 "aab"，
 * 这三个字符串都是等价的，而 "aab" 是 S 的按字典序最小的等价字符串
 * 利用 A 和 B 的等价信息，找出并返回 S 的按字典序排列最小的等价字符串。
 *
 * Example 1:
 * Input: A = "parker", B = "morris", S = "parser"
 * Output: "makkek"
 * Explanation: Based on the equivalency information in A and B, we can group their characters as [m,p], [a,o], [k,r,s], [e,i]. The characters in each group are equivalent and sorted in lexicographical order. So the answer is "makkek".
 *
 * Example 2:
 * Input: A = "hello", B = "world", S = "hold"
 * Output: "hdld"
 * Explanation:  Based on the equivalency information in A and B, we can group their characters as [h,w], [d,e,o], [l,r]. So only the second letter 'o' in S is changed to 'd', the answer is "hdld".
 *
 * Example 3:
 * Input: A = "leetcode", B = "programs", S = "sourcecode"
 * Output: "aauaaaaada"
 * Explanation:  We group the equivalent characters in A and B as [a,o,e,r,s,c], [l,p], [g,t] and [d,m], thus all letters in S except 'u' and 'd' are transformed to 'a', the answer is "aauaaaaada".
 *
 * 提示：
 * 字符串 A，B 和 S 仅有从 'a' 到 'z' 的小写英文字母组成。
 * 字符串 A，B 和 S 的长度在 1 到 1000 之间。
 * 字符串 A 和 B 长度相同。
 */
public class LexicographicallySmallestEquivalentString {

    public static void main(String[] args) {
        LexicographicallySmallestEquivalentString ls = new LexicographicallySmallestEquivalentString();
        System.out.println(ls.smallestEquivalentString("parker", "morris", "parser"));
        System.out.println(ls.smallestEquivalentString("hello", "world", "hold"));
        System.out.println(ls.smallestEquivalentString("leetcode", "programs", "sourcecode"));
    }

    public String smallestEquivalentString(String A, String B, String S) {
        if(A==null||A.length()==0) {
            return S;
        }
        int[] parent = new int[26];
        for(int i=0;i<26;i++) {
            parent[i] = i;
        }
        for(int i=0;i<A.length();i++) {
            int x = A.charAt(i)-'a';
            int y = B.charAt(i)-'a';
            union(Math.min(x, y), Math.max(x, y), parent);
        }
        StringBuilder ans = new StringBuilder();
        for(char c : S.toCharArray()) {
            ans.append((char)(find(c-'a', parent)+'a'));
        }
        return ans.toString();
    }

    private int find(int x, int[] parent) {
        if(parent[x]!=x) {
            return parent[x] = find(parent[x], parent);
        }
        return x;
    }

    private void union(int x, int y, int[] parent) {
        parent[find(y,parent)] = find(x,parent);
    }

}
