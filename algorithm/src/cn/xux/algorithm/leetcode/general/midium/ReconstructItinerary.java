package cn.xux.algorithm.leetcode.general.midium;

import java.util.*;

/**
 * 332. 重新安排行程
 * 给定一个机票的字符串二维数组 [from, to]，
 * 子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。
 * 所有这些机票都属于一个从JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 出发。
 *
 * 说明:
 * 如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。
 * 例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
 * 所有的机场都用三个大写字母表示（机场代码）。
 * 假定所有机票至少存在一种合理的行程。
 *
 * 示例 1:
 * 输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * 输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 *
 * 示例 2:
 * 输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
 */
public class ReconstructItinerary {

    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new LinkedList<>();
        if(tickets==null||tickets.size()==0) {
            return res;
        }
        int n = tickets.size();
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for(List<String> list : tickets) {
            if(!map.containsKey(list.get(0))) {
                map.put(list.get(0), new PriorityQueue<String>());
            }
            map.get(list.get(0)).offer(list.get(1));
        }
        String start = "JFK";
        helper(map, "JFK", res);
        return res;
    }

    //递归
    /*public void helper(Map<String, PriorityQueue<String>> map, String src, List<String> res) {
        PriorityQueue<String> nbr = map.get(src);
        while(nbr!=null&&!nbr.isEmpty()) {
            String dest = nbr.poll();
            helper(map, dest, res);
        }
        res.add(0, src);
    }*/

    //迭代
    public void helper(Map<String, PriorityQueue<String>> map, String src, List<String> res) {
        Stack<String> stack = new Stack<>();
        stack.push(src);
        while (!stack.isEmpty()) {
            PriorityQueue<String> nbr;
            while ((nbr=map.get(stack.peek()))!=null&&nbr.size()>0) {
                stack.push(nbr.poll());
            }
            res.add(0, stack.pop());
        }
    }


    public static void main(String[] args) {
        ReconstructItinerary ri = new ReconstructItinerary();
        //[["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
        List<String> ticket01 = new ArrayList<>();
        ticket01.add("MUC");
        ticket01.add("LHR");
        List<String> ticket02 = new ArrayList<>();
        ticket02.add("JFK");
        ticket02.add("MUC");
        List<String> ticket03 = new ArrayList<>();
        ticket03.add("SFO");
        ticket03.add("SJC");
        List<String> ticket04 = new ArrayList<>();
        ticket04.add("LHR");
        ticket04.add("SFO");
        List<List<String>> tickets1 = new ArrayList<>();
        tickets1.add(ticket01);
        tickets1.add(ticket02);
        tickets1.add(ticket03);
        tickets1.add(ticket04);
        System.out.println(ri.findItinerary(tickets1));

        //[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
        List<String> ticket05 = new ArrayList<>();
        ticket05.add("JFK");
        ticket05.add("SFO");
        List<String> ticket06 = new ArrayList<>();
        ticket06.add("JFK");
        ticket06.add("ATL");
        List<String> ticket07 = new ArrayList<>();
        ticket07.add("SFO");
        ticket07.add("ATL");
        List<String> ticket08 = new ArrayList<>();
        ticket08.add("ATL");
        ticket08.add("JFK");
        List<String> ticket09 = new ArrayList<>();
        ticket09.add("ATL");
        ticket09.add("SFO");
        List<List<String>> tickets2 = new ArrayList<>();
        tickets2.add(ticket05);
        tickets2.add(ticket06);
        tickets2.add(ticket07);
        tickets2.add(ticket08);
        tickets2.add(ticket09);
        System.out.println(ri.findItinerary(tickets2));

        //[["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
        List<String> ticket10 = new ArrayList<>();
        ticket10.add("JFK");
        ticket10.add("KUL");
        List<String> ticket11 = new ArrayList<>();
        ticket11.add("JFK");
        ticket11.add("NRT");
        List<String> ticket12 = new ArrayList<>();
        ticket12.add("NRT");
        ticket12.add("JFK");
        List<List<String>> tickets3 = new ArrayList<>();
        tickets3.add(ticket10);
        tickets3.add(ticket11);
        tickets3.add(ticket12);
        System.out.println(ri.findItinerary(tickets3));
    }

}
