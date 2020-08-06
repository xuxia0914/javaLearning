package cn.xux.algorithm.lintcode;

import java.util.*;

/**
 * 805. 最大关联集合
 * 中文English
 * 亚麻卖书，每本书都有与其关联性很强的书，给出ListA与ListB，表示ListA[i]与ListB[i]有关联，
 * 输出互相关联的最大集合。(输出任意顺序)，题目保证只有一个最大的集合。
 *
 * 样例
 * 样例 1:
 * 	输入:  ListA = ["abc","abc","abc"], ListB = ["bcd","acd","def"]
 * 	输出:  ["abc","acd","bcd","def"]
 * 	解释:
 * 	"abc" 和其他书均有关联，全集就是最大集合。
 *
 * 样例 2:
 * 	输入:  ListA = ["a","b","d","e","f"], ListB = ["b","c","e","g","g"]
 * 	输出:  ["d","e","f","g"]
 * 	解释:
 * 	关联的集合有 [a, b, c] 和 [d, e, g, f], 最大的是 [d, e, g, f]
 * 注意事项
 * 书籍的数量不会超过5000。
 */
public class Lintcode805 {

    /**
     * @param ListA: The relation between ListB's books
     * @param ListB: The relation between ListA's books
     * @return: The answer
     */
    public List<String> maximumAssociationSet(String[] ListA, String[] ListB) {
        // Write your code here
        List<String> ans = new ArrayList<>();
        Map<String, Set<String>> map = new HashMap<>();
        for(int i=0;i<ListA.length;i++) {
            if(!map.containsKey(ListA[i])) {
                map.put(ListA[i], new HashSet<>());
            }
            map.get(ListA[i]).add(ListB[i]);
            if(!map.containsKey(ListB[i])) {
                map.put(ListB[i], new HashSet<>());
            }
            map.get(ListB[i]).add(ListA[i]);
        }
        Set<String> visited = new HashSet<>();
        for(String key : map.keySet()) {
            if(visited.add(key)) {
                List<String> list = new ArrayList<>();
                Queue<String> queue = new LinkedList<>();
                list.add(key);
                queue.offer(key);
                while(!queue.isEmpty()) {
                    String curr = queue.poll();
                    for(String nei : map.get(curr)) {
                        if(visited.add(nei)) {
                            list.add(nei);
                            queue.offer(nei);
                        }
                    }
                }
                if(list.size()>ans.size()) {
                    ans = list;
                }
            }
        }
        return ans;
    }

}
