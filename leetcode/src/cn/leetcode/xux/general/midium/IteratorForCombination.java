package cn.leetcode.xux.general.midium;

/**
 * 1286. 字母组合迭代器
 * 请你设计一个迭代器类，包括以下内容：
 * 一个构造函数，输入参数包括：一个 有序且字符唯一 的字符串 characters（该字符串只包含小写英文字母）和一个数字 combinationLength 。
 * 函数 next() ，按 字典序 返回长度为 combinationLength 的下一个字母组合。
 * 函数 hasNext() ，只有存在长度为 combinationLength 的下一个字母组合时，才返回 True；否则，返回 False。
 *
 * 示例：
 * CombinationIterator iterator = new CombinationIterator("abc", 2); // 创建迭代器 iterator
 * iterator.next(); // 返回 "ab"
 * iterator.hasNext(); // 返回 true
 * iterator.next(); // 返回 "ac"
 * iterator.hasNext(); // 返回 true
 * iterator.next(); // 返回 "bc"
 * iterator.hasNext(); // 返回 false
 *
 * 提示：
 * 1 <= combinationLength <= characters.length <= 15
 * 每组测试数据最多包含 10^4 次函数调用。
 * 题目保证每次调用函数 next 时都存在下一个字母组合。
 */
public class IteratorForCombination {

    public static void main(String[] args) {
        CombinationIterator ci = new CombinationIterator("abc", 2);
        System.out.println(ci.next());
        System.out.println(ci.hasNext());
        System.out.println(ci.next());
        System.out.println(ci.hasNext());
        System.out.println(ci.next());
        System.out.println(ci.hasNext());
    }

}

class CombinationIterator {

    String curr;
    String characters;
    int combinationLength;

    public CombinationIterator(String characters, int combinationLength) {
        this.characters = characters;
        this.combinationLength = combinationLength;
    }

    public String next() {
        StringBuilder sb = new StringBuilder();
        if(curr==null) {
            int i = 0;
            while(i<combinationLength) {
                sb.append(characters.charAt(i));
                i++;
            }
        }else {
            int idx = characters.length()-1;
            int i = curr.length()-1;
            while(curr.charAt(i)==characters.charAt(idx)) {
                i--;
                idx--;
            }
            while(curr.charAt(i)!=characters.charAt(idx)) {
                idx--;
            }
            for(int j=0;j<i;j++) {
                sb.append(curr.charAt(j));
            }
            int currLen = sb.length();
            sb.append(characters.substring(idx+1, idx+combinationLength-currLen+1));
        }
        curr = sb.toString();
        return curr;
    }

    public boolean hasNext() {
        if(curr==null) {
            return true;
        }
        int idx = characters.length()-1;
        for(int i=combinationLength-1;i>=0;i--) {
            if(curr.charAt(i)!=characters.charAt(idx)) {
                return true;
            }
            idx--;
        }
        return false;
    }

}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
