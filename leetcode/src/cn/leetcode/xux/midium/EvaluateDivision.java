package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 399. 除法求值
 * 给出方程式 A / B = k, 其中 A 和 B 均为代表字符串的变量， k 是一个浮点型数字。根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回 -1.0。
 *
 * 示例 :
 * 给定 a / b = 2.0, b / c = 3.0
 * 问题: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * 返回 [6.0, 0.5, -1.0, 1.0, -1.0 ]
 *
 * 输入为: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries(方程式，方程式结果，问题方程式)， 其中 equations.size() == values.size()，即方程式的长度与方程式结果长度相等（程式与结果一一对应），并且结果值均为正数。以上为方程式的描述。 返回vector<double>类型。
 *
 * 基于上述例子，输入如下：
 *
 * equations(方程式) = [ ["a", "b"], ["b", "c"] ],
 * values(方程式结果) = [2.0, 3.0],
 * queries(问题方程式) = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 * 输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果。
 */
public class EvaluateDivision {

    public static void main(String[] args) {
        EvaluateDivision ed = new EvaluateDivision();
        ed.calcEquation1(new String[][]{{"x1","x2"},{"x2","x3"},{"x3","x4"},{"x4","x5"}},
                new double[]{3.0,4.0,5.0,6.0},
                new String[][]{{"x1","x5"},{"x5","x2"},{"x2","x4"},{"x2","x2"},{"x2","x9"},{"x9","x9"}});
    }

    public double[] calcEquation1(String[][] equations, double[] values, String[][] queries) {
        Map<String, Graph> map = new HashMap<>();
        int n = values.length;
        String[] currList;
        double currK;
        for(int i=0;i<n;i++) {
            currList = equations[i];
            currK = values[i];
            Graph graph1 = map.getOrDefault(currList[0], new Graph(currList[0]));
            Graph graph2 = map.getOrDefault(currList[1], new Graph(currList[1]));
            graph1.neighbors.add(graph2);
            graph1.ks.add(currK);
            graph2.neighbors.add(graph1);
            graph2.ks.add(1/currK);
            map.put(currList[0], graph1);
            map.put(currList[1], graph2);
        }
        int m = queries.length;
        double[] res = new double[m];
        String[] curr;
        for(int i=0;i<m;i++) {
            curr = queries[i];
            if(!map.containsKey(curr[0])||!map.containsKey(curr[1])) {
                res[i] = -1.0;
                continue;
            }
            if(curr[0].equals(curr[1])) {
                res[i] = 1.0;
                continue;
            }
            res[i] = dfs(map.get(curr[0]), 1.0, null, curr[1]);
        }
        return res;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Graph> map = new HashMap<>();
        int n = values.length;
        List<String> currList;
        double currK;
        for(int i=0;i<n;i++) {
            currList = equations.get(i);
            currK = values[i];
            Graph graph1 = map.getOrDefault(currList.get(0), new Graph(currList.get(0)));
            Graph graph2 = map.getOrDefault(currList.get(1), new Graph(currList.get(1)));
            graph1.neighbors.add(graph2);
            graph1.ks.add(currK);
            graph2.neighbors.add(graph1);
            graph2.ks.add(1/currK);
            map.put(currList.get(0), graph1);
            map.put(currList.get(1), graph2);
        }
        int m = queries.size();
        double[] res = new double[m];
        List<String> curr;
        for(int i=0;i<m;i++) {
            curr = queries.get(i);
            if(!map.containsKey(curr.get(0))||!map.containsKey(curr.get(1))) {
                res[i] = -1.0;
                continue;
            }
            if(curr.get(0).equals(curr.get(1))) {
                res[i] = 1.0;
                continue;
            }
            res[i] = dfs(map.get(curr.get(0)), 1.0, null, curr.get(1));
        }
        return res;
    }

    public double dfs(Graph graph, double res, String pre, String target) {
        Graph neighbor;
        for(int i=0;i<graph.neighbors.size();i++) {
            double tmp = res;
            neighbor = graph.neighbors.get(i);
            if(neighbor.val.equals(pre)) {
                continue;
            }
            tmp = tmp*graph.ks.get(i);
            if(neighbor.val.equals(target)) {
                return tmp;
            }
            tmp = dfs(neighbor, tmp, graph.val, target);
            if(tmp!=-1.0) {
                return tmp;
            }
        }
        return -1.0;
    }

}

class Graph {
    String val ;
    List<Graph> neighbors = new ArrayList<>();
    List<Double> ks = new ArrayList<>();

    Graph(String val) {
        this.val = val;
    }

}