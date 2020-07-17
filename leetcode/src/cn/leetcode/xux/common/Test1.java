package cn.leetcode.xux.common;

import javafx.util.Pair;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Test1 {

    public static void main(String[] args) {
//        System.out.println(new Test1().getAns(new int[]{8,9,7,3,0,5,11}));
    }

    /**
     * 629. 最小生成树
     * 中文English
     * 给出一些Connections，即Connections类，找到一些能够将所有城市都连接起来并且花费最小的边。
     * 如果说可以将所有城市都连接起来，则返回这个连接方法；不然的话返回一个空列表。
     *
     * 样例
     * 样例 1:
     *
     * 输入:
     * ["Acity","Bcity",1]
     * ["Acity","Ccity",2]
     * ["Bcity","Ccity",3]
     * 输出:
     * ["Acity","Bcity",1]
     * ["Acity","Ccity",2]
     * 样例 2:
     *
     * 输入:
     * ["Acity","Bcity",2]
     * ["Bcity","Dcity",5]
     * ["Acity","Dcity",4]
     * ["Ccity","Ecity",1]
     * 输出:
     * []
     *
     * 解释:
     * 没有办法连通
     * 注意事项
     * 返回按cost排序的连接方法，如果cost相同就按照city1进行排序，如果city1也相同那么就按照city2进行排序。
     */
    public List<Connection> lowestCost(List<Connection> connections) {
        Collections.sort(connections, (o1,o2)->o1.cost!=o2.cost?o1.cost-o2.cost:(!o1.city1.equals(o2.city1)?o1.city1.compareTo(o2.city1):o1.city2.compareTo(o2.city2)));
        Map<String, Integer> hash = new HashMap<>();
        int n = 0;
        for (Connection connection : connections) {
            if (!hash.containsKey(connection.city1)) {
                hash.put(connection.city1, ++n);
            }
            if (!hash.containsKey(connection.city2)) {
                hash.put(connection.city2, ++n);
            }
        }

        int[] father = new int[n + 1];

        List<Connection> results = new ArrayList<>();
        for (Connection connection : connections) {
            int num1 = hash.get(connection.city1);
            int num2 = hash.get(connection.city2);

            int root1 = find(num1, father);
            int root2 = find(num2, father);
            if (root1 != root2) {
                father[root1] = root2;
                results.add(connection);
            }
        }

        if (results.size() != n - 1) {
            return new ArrayList<>();
        }
        return results;
    }

    public int find(int num, int[] father) {
        if (father[num] == 0) {
            return num;
        }

        return father[num] = find(father[num], father);
    }

    public List<Connection> lowestCost1(List<Connection> connections) {
        // Write your code here
        Set<String> set = new HashSet<>();
        for(Connection con : connections) {
            set.add(con.city1);
            set.add(con.city2);
        }
        int n = set.size();
        Set<String> init = new HashSet<>();
        init.add(connections.get(0).city1);
        dfs(connections, new ArrayList<>(), 0, init, new boolean[connections.size()], n);
        Collections.sort(ans, (o1,o2)->o1.cost!=o2.cost?o1.cost-o2.cost:(!o1.city1.equals(o2.city1)?o1.city1.compareTo(o2.city1):o1.city2.compareTo(o2.city2)));
        return ans;
    }

    List<Connection> ans = new ArrayList<>();
    int ansCost = Integer.MAX_VALUE;

    private void dfs(List<Connection> connections, List<Connection> curr,
                    int currCost, Set<String> set, boolean[] visited, int n) {
        if(set.size()==n) {
            if(ansCost>currCost) {
                ans = curr;
                ansCost = currCost;
            }
            return;
        }
        if(currCost>=ansCost) {
            return;
        }
        for(int i=0;i<connections.size();i++) {
            if(!visited[i]) {
                Connection con = connections.get(i);
                if(set.contains(con.city1)!=set.contains(con.city2)) {
                    visited[i] = true;
                    Set<String> nextSet = new HashSet(set);
                    nextSet.add(con.city1);
                    nextSet.add(con.city2);
                    List<Connection> nextList = new ArrayList<>(curr);
                    nextList.add(con);
                    dfs(connections, nextList, currCost+con.cost, nextSet, visited, n);
                    visited[i] = false;
                }
            }
        }
    }

}

//Definition for a Connection.
class Connection {
    public String city1, city2;
    public int cost;
    public Connection(String city1, String city2, int cost) {
        this.city1 = city1;
        this.city2 = city2;
        this.cost = cost;
    }
}
