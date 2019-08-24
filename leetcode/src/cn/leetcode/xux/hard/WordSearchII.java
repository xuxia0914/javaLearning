package cn.leetcode.xux.hard;

import cn.leetcode.xux.common.Trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board =
 * [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * Return ["eat","oath"].
 */
public class WordSearchII {

    /**
     * Runtime: 691 ms, faster than 5.00% of Java online submissions for Word Search II.
     * Memory Usage: 41.5 MB, less than 89.71% of Java online submissions for Word Search II.
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> res = new HashSet<>();
        if(board==null||board.length==0||board[0].length==0||words==null||words.length==0) {
            return new ArrayList<>(res);
        }
        int m = board.length;
        int n = board[0].length;

        for(String word : words) {
            for(int i=0;i<m;i++) {
                for(int j=0;j<n;j++) {
                    if(board[i][j]==word.charAt(0)) {
                        boolean[][] visited = new boolean[m][n];
                        visited[i][j] = true;
                        helper(res, board, visited, word, "", i, j);
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

    public void helper(Set<String> res, char[][] board, boolean[][] visited, String word, String curr, int i, int j) {
        String newCurr = curr + board[i][j];
        if(newCurr.equals(word)) {
            res.add(word);
            return;
        }
        if(i>0&&!visited[i-1][j]&&word.charAt(newCurr.length())==board[i-1][j]) {
            visited[i][j] = true;
            helper(res, board, visited, word, newCurr, i-1, j);
            visited[i][j] = false;
        }
        if(i<board.length-1&&!visited[i+1][j]&&word.charAt(newCurr.length())==board[i+1][j]) {
            visited[i][j] = true;
            helper(res, board, visited, word, newCurr, i+1, j);
            visited[i][j] = false;
        }
        if(j>0&&!visited[i][j-1]&&word.charAt(newCurr.length())==board[i][j-1]) {
            visited[i][j] = true;
            helper(res, board, visited, word, newCurr, i, j-1);
            visited[i][j] = false;
        }
        if(j<board[0].length-1&&!visited[i][j+1]&&word.charAt(newCurr.length())==board[i][j+1]) {
            visited[i][j] = true;
            helper(res, board, visited, word, newCurr, i, j+1);
            visited[i][j] = false;
        }
    }

    static List<String> resList = new ArrayList<String>();

    public static List<String> solution(char[][] board, String[] words) {
        if(board.length==0||board[0].length==0||words.length==0) {
            return resList;
        }
        Trie trie = new Trie();
        for(String word : words) {
            trie.insert(word);
        }
        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[0].length;j++) {
                char c = board[i][j];
                board[i][j] = '1';
                helper(board, i, j, trie, c+"");
                board[i][j] = c;
            }
        }
        return resList;
    }

    public static void helper(char[][] board, int i, int j, Trie t, String currStr) {
        if(t.search(currStr)) {
            resList.add(currStr);
        }
        if(t.startsWith(currStr)) {
            if(i>0&&board[i-1][j]!='1') {
                char c = board[i-1][j];
                board[i-1][j] = '1';
                helper(board, i-1, j, t, currStr+c);
                board[i-1][j] = c;
            }
            if(i<board.length-1&&board[i+1][j]!='1') {
                char c = board[i+1][j];
                board[i+1][j] = '1';
                helper(board, i+1, j, t, currStr+c);
                board[i+1][j] = c;
            }
            if(j>0&&board[i][j-1]!='1') {
                char c = board[i][j-1];
                board[i][j-1] = '1';
                helper(board, i, j-1, t, currStr+c);
                board[i][j-1] = c;
            }
            if(j<board[0].length-1&&board[i][j+1]!='1') {
                char c = board[i][j+1];
                board[i][j+1] = '1';
                helper(board, i, j+1, t, currStr+c);
                board[i][j+1] = c;
            }
        }
        return;
    }

    public static void main(String[] args) {
        /*System.out.println(solution(new char[][]{
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}},
                new String[]{"oath","pea","eat","rain"}));*/

        System.out.println(new WordSearchII().findWords(new char[][]{
                        {'o','a','a','n'},
                        {'e','t','a','e'},
                        {'i','h','k','r'},
                        {'i','f','l','v'}},
                new String[]{"oath","pea","eat","rain"}));
    }

}
