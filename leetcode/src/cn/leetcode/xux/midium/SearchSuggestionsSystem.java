package cn.leetcode.xux.midium;

import java.util.*;

/**
 * 1268. 搜索推荐系统
 * 给你一个产品数组 products 和一个字符串 searchWord ，products  数组中每个产品都是一个字符串。
 * 请你设计一个推荐系统，在依次输入单词 searchWord 的每一个字母后，
 * 推荐 products 数组中前缀与 searchWord 相同的最多三个产品。如果前缀相同的可推荐产品超过三个，
 * 请按字典序返回最小的三个。
 * 请你以二维列表的形式，返回在输入 searchWord 每个字母后相应的推荐产品的列表。
 *
 * 示例 1：
 * 输入：products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * 输出：[
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * 解释：按字典序排序后的产品列表是 ["mobile","moneypot","monitor","mouse","mousepad"]
 * 输入 m 和 mo，由于所有产品的前缀都相同，所以系统返回字典序最小的三个产品 ["mobile","moneypot","monitor"]
 * 输入 mou， mous 和 mouse 后系统都返回 ["mouse","mousepad"]
 *
 * 示例 2：
 * 输入：products = ["havana"], searchWord = "havana"
 * 输出：[["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 *
 * 示例 3：
 * 输入：products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
 * 输出：[["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 *
 * 示例 4：
 * 输入：products = ["havana"], searchWord = "tatiana"
 * 输出：[[],[],[],[],[],[],[]]
 *
 * 提示：
 * 1 <= products.length <= 1000
 * 1 <= Σ products[i].length <= 2 * 10^4
 * products[i] 中所有的字符都是小写英文字母。
 * 1 <= searchWord.length <= 1000
 * searchWord 中所有字符都是小写英文字母。
 */
public class SearchSuggestionsSystem {

    public static void main(String[] args) {
        System.out.println(new SearchSuggestionsSystem().suggestedProducts(
                new String[]{"mobile","mouse","moneypot","monitor","mousepad"},
                "mouse"
        ));
    }

    //快排+二分
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        int n = products.length;
        List<List<String>> result = new ArrayList<>();
        int len = searchWord.length();
        for(int i=1;i<=len;i++) {
            String target = searchWord.substring(0, i);
            int left = 0;
            int right = n-1;
            while(left<right) {
                int mid = (left+right)/2;
                if(products[mid].compareTo(target)>=0) {
                    right = mid;
                }else {
                    left = mid+1;
                }
            }
            List<String> tmp = new ArrayList<>();
            while(left<n&&products[left].startsWith(target)&&tmp.size()<3) {
                tmp.add(products[left++]);
            }
            result.add(tmp);
        }
        return result;
    }

    //字典树
    public List<List<String>> suggestedProducts1(String[] products, String searchWord) {
        Trie trie = new Trie();
        trie.insert(products);
        return trie.searchWord(searchWord);
    }

    private class TrieNode{
        boolean end = false;
        String str = null;
        int count = 0;
        TrieNode[] children = new TrieNode[26];
    }

    private class Trie{
        TrieNode root = new TrieNode();
        public void insert(String[] products){
            for(String str : products){
                insertWord(str);
            }
        }
        private void insertWord(String products){
            TrieNode node = root;
            for(char c : products.toCharArray()){
                if(node.children[c - 'a'] == null){
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            if(node.end != true){
                node.end = true;
                node.str = products;
            }
            node.count++;
        }
        public List<List<String>> searchWord(String word){
            List<List<String>> result = new ArrayList<>();
            for(int i = 1; i <= word.length(); i++){
                result.add(search(word.substring(0, i)));
            }
            return result;
        }
        private List<String> search(String pattern){
            List<String> result = new ArrayList<>();
            TrieNode node = root;
            for(char c : pattern.toCharArray()){
                if(node.children[c - 'a'] == null){
                    return result;
                }
                node = node.children[c - 'a'];
            }
            Solution(node, result);
            return result;
        }
        private void Solution(TrieNode root, List<String> result){
            if(root.end){
                for(int i = 0; i < root.count; i++){
                    result.add(root.str);
                    if(result.size() == 3){
                        return;
                    }
                }
            }
            for(TrieNode node : root.children){
                if(node != null){
                    Solution(node, result);
                }
                if(result.size() == 3){
                    return;
                }
            }
        }
    }

}