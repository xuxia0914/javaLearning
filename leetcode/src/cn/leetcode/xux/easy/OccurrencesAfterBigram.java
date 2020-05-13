package cn.leetcode.xux.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 1078. Bigram 分词
 * 给出第一个词 first 和第二个词 second，考虑在某些文本 text 中可能以 "first second third" 形式出现的情况，
 * 其中 second 紧随 first 出现，third 紧随 second 出现。
 * 对于每种这样的情况，将第三个词 "third" 添加到答案中，并返回答案。
 *
 * 示例 1：
 * 输入：text = "alice is a good girl she is a good student", first = "a", second = "good"
 * 输出：["girl","student"]
 *
 * 示例 2：
 * 输入：text = "we will we will rock you", first = "we", second = "will"
 * 输出：["we","rock"]
 *
 * 提示：
 * 1 <= text.length <= 1000
 * text 由一些用空格分隔的单词组成，每个单词都由小写英文字母组成
 * 1 <= first.length, second.length <= 10
 * first 和 second 由小写英文字母组成
 */
public class OccurrencesAfterBigram {

    public String[] findOcurrences(String text, String first, String second) {
        if(text==null||text.length()==0) {
            return new String[0];
        }
        String[] words = text.trim().split(" ");
        if(words.length<3) {
            return new String[0];
        }
        List<String> list = new ArrayList<>();
        int len = words.length;
        for(int i=0;i<len-2;i++) {
            if(words[i].equals(first)&&words[i+1].equals(second)) {
                list.add(words[i+2]);
            }
        }
        return list.toArray(new String[]{});
    }

}
