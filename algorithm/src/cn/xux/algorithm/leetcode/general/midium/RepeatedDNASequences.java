package cn.xux.algorithm.leetcode.general.midium;

import java.util.*;

/**
 * 187. 重复的DNA序列
 * 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，
 * 例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 *
 * 编写一个函数来找出所有目标子串，目标子串的长度为 10，
 * 且在 DNA 字符串 s 中出现次数超过一次。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 * 示例 2：
 *
 * 输入：s = "AAAAAAAAAAAAA"
 * 输出：["AAAAAAAAAA"]
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 105
 * s[i] 为 'A'、'C'、'G' 或 'T'
 */
public class RepeatedDNASequences {

    Map<Character, Integer> bin = new HashMap<Character, Integer>() {{
        put('A', 0);
        put('C', 1);
        put('G', 2);
        put('T', 3);
    }};

    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length();
        List<String> ans = new ArrayList<>();
        int P = 131313;
        int[] p = new int[n+1];
        p[0] = 1;
        int[] h = new int[n+1];
        for(int i=1;i<=n;i++) {
            h[i] = h[i-1]*P+s.charAt(i);
            p[i] = p[i-1]*P;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=1;i+9<=n;i++) {
            int j = i+9;
            int hash = h[j]-h[i-1]*p[10];
            int cnt = map.getOrDefault(hash, 0);
            if(cnt==1) {
                ans.add(s.substring(i-1,j));
            }
            map.put(hash, cnt+1);
        }
        return ans;
    }

    // 位+滑动窗口
    public List<String> findRepeatedDnaSequences1(String s) {
        List<String> ans = new ArrayList<>();
        int n = s.length();
        int max = 1<<20;
        if(n<=10) {
            return ans;
        }
        int curr = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for(int i=0;i<n;i++) {
            curr = (curr*4+bin.get(s.charAt(i)))%max;
            if(i>=9) {
                int fre = cnt.getOrDefault(curr, 0)+1;
                cnt.put(curr, fre);
                if(fre==2) {
                    ans.add(s.substring(i-9, i+1));
                }
            }
        }
        return ans;
    }

    // hash
    public List<String> findRepeatedDnaSequences2(String s) {
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
