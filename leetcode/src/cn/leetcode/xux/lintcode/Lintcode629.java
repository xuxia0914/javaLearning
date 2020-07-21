package cn.leetcode.xux.lintcode;

import java.util.*;

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
public class Lintcode629 {

    /**
     * @param connections given a list of connections include two cities and cost
     * @return a list of connections from results
     */
    public List<Connection> lowestCost(List<Connection> connections) {
        // Write your code here
        Collections.sort(connections, comp);
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

    static Comparator<Connection> comp = new Comparator<Connection>() {
        @Override
        public int compare(Connection a, Connection b) {
            if (a.cost != b.cost) {
                return a.cost - b.cost;
            }
            if (a.city1.equals(b.city1)) {
                return a.city2.compareTo(b.city2);
            }
            return a.city1.compareTo(b.city1);
        }
    };

    public int find(int num, int[] father) {
        if (father[num] == 0) {
            return num;
        }
        return father[num] = find(father[num], father);
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
