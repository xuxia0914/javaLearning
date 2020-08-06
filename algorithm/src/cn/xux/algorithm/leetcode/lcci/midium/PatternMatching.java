package cn.xux.algorithm.leetcode.lcci.midium;

/**
 * 面试题 16.18. 模式匹配
 * 你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。
 * 例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），
 * 该字符串也匹配像"a"、"ab"和"b"这样的模式。但需注意"a"和"b"不能同时表示相同的字符串。
 * 编写一个方法判断value字符串是否匹配pattern字符串。
 *
 * 示例 1：
 * 输入： pattern = "abba", value = "dogcatcatdog"
 * 输出： true
 *
 * 示例 2：
 * 输入： pattern = "abba", value = "dogcatcatfish"
 * 输出： false
 *
 * 示例 3：
 * 输入： pattern = "aaaa", value = "dogcatcatdog"
 * 输出： false
 *
 * 示例 4：
 * 输入： pattern = "abba", value = "dogdogdogdog"
 * 输出： true
 * 解释： "a"="dogdog",b=""，反之也符合规则
 *
 * 提示：
 * 0 <= len(pattern) <= 1000
 * 0 <= len(value) <= 1000
 * 你可以假设pattern只包含字母"a"和"b"，value仅包含小写字母。
 */
public class PatternMatching {

    public boolean patternMatching(String pattern, String value) {
        if(pattern==null||value==null) {
            return false;
        }
        int lenP = pattern.length();
        int lenV = value.length();
        if(lenP==0) {
            return lenV==0;
        }
        int na = 0;
        int nb = 0;
        for(char c : pattern.toCharArray()) {
            if(c=='a') {
                na++;
            }else {
                nb++;
            }
        }
        if(lenV==0) {
            return na==0||nb==0;
        }
        if(na==0||nb==0) {
            int n = na+nb;
            if(lenV%n!=0) {
                return false;
            }
            int lenA = lenV/n;
            String va = value.substring(0, lenA);
            int idx = lenA;
            while(idx<lenV) {
                String curr = value.substring(idx, idx+lenA);
                if(!curr.equals(va)) {
                    return false;
                }
                idx += lenA;
            }
            return true;
        }

        for(int lenA=0;lenA*na<=lenV;lenA++) {
            if((lenV-lenA*na)%nb!=0) {
                continue;
            }
            int lenB = (lenV-lenA*na)/nb;
            String va = null;
            String vb = null;
            boolean flag = true;
            int idx = 0;
            for(int i=0;i<lenP;i++) {
                char c = pattern.charAt(i);
                if(c=='a') {
                    String s = value.substring(idx, idx+lenA);
                    idx += lenA;
                    if(va==null) {
                        va = s;
                    }else if(!va.equals(s)) {
                        flag = false;
                        break;
                    }
                }else {
                    String s = value.substring(idx, idx+lenB);
                    idx += lenB;
                    if(vb==null) {
                        vb = s;
                    }else if(!vb.equals(s)) {
                        flag = false;
                        break;
                    }
                }
            }
            if(flag&&!va.equals(vb)) {
                return true;
            }
        }
        return false;
    }

}
