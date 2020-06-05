package cn.leetcode.xux.general.midium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 127. 单词接龙
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 *
 * 说明:
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 *
 * 示例 1:
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出: 5
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *      返回它的长度 5。
 *
 * 示例 2:
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 输出: 0
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(beginWord.length()<1||wordList.size()==0) {
            return 0;
        }
        int n = wordList.size();
        Queue<String> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        boolean flag = true;
        int res = 1;
        int cnt = 0;
        for(int i=0;i<n;i++) {
            if(beginWord.equals(wordList.get(i))) {
                visited[i] = true;
                cnt++;
            }
            if(endWord.equals(wordList.get(i))) {
                flag = false;
            }
        }
        if(flag) {
            return 0;
        }else {
            queue.offer(beginWord);
        }
        while(!queue.isEmpty()) {
            int size = queue.size();
            res++;
            for(int i=0;i<size;i++) {
                String curr = queue.poll();
                for(int j=0;j<n;j++) {
                    if(!visited[j]&&helper(curr, wordList.get(j))) {
                        if(wordList.get(j).equals(endWord)) {
                            return res;
                        }
                        visited[j] = true;
                        cnt++;
                        if(cnt==n) {
                            return 0;
                        }
                        queue.offer(wordList.get(j));
                    }
                }
            }
        }
        return 0;
    }

    public boolean helper(String src, String target) {
        int cnt = 0;
        for(int i=0;i<src.length();i++) {
            if(src.charAt(i)!=target.charAt(i)) {
                cnt++;
            }
            if(cnt>1) {
                return false;
            }
        }
        return cnt==1;
    }

    public static void main(String[] args) {
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        System.out.println(new WordLadder().ladderLength("hit", "cog", wordList));  //5
    }

}
