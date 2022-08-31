package com.coding.test.thisiscote.shortestpath;

import com.coding.test.thisiscote.SpringConfig;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 미래 도시 : level2
 * node간 이동시간은 1로 동일하다
 * 1번에서 k를 거쳐 x로 가는 최소 이동시간
 *
 * [입력 조건]
 * 1 <= node, edge <= 100
 * 1 <= x, k <= node
 * [풀이시간] 40분
 * [시간제한] 1초
 */
@Component
public class ShortestPath1 {

    private static final int MAX = (int) 1e9;
    private Map<Integer, Set<Integer>> graph;

    //1에서 k를 거쳐 x
    public int solution(int node, int edge, int[][] roads, int x, int k) {

        graph = new HashMap<>();
        for (int i = 1; i <= node; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int[] road : roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }

        int totalCost = getCost(1, k, node) + getCost(k, x, node);
        if (totalCost >= MAX) {
            System.out.println(-1);
            return -1;
        }

        System.out.println(totalCost);
        return totalCost;
    }

    public int getCost(int start, int end, int node) {
        int[] cost = new int[node + 1];
        for (int i = 1; i <= node; i++) {
            cost[i] = MAX;
        }
        cost[start] = 0;

        PriorityQueue<NodeDistance> nexts = new PriorityQueue<>();
        nexts.offer(new NodeDistance(start, 0));

        while (!nexts.isEmpty()) {
            NodeDistance now = nexts.poll();

            if (cost[now.getNode()] < now.getDistance()) {
                continue;
            }

            for (Integer next : graph.get(now.getNode())) {
                if (cost[next] > now.getDistance() + 1) {
                    cost[next] = now.getDistance() + 1;
                    nexts.offer(new NodeDistance(next, cost[next]));
                }
            }
        }

        return cost[end];
    }

    @ToString
    @Getter
    class NodeDistance implements Comparable<NodeDistance> {
        private int node;
        private int distance;

        public NodeDistance(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(NodeDistance o) {
            if (this.distance == o.distance) {
                return this.node - o.node;
            }
            return this.distance - o.distance;
        }
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        ShortestPath1 sol = ac.getBean(ShortestPath1.class);

        //3
        sol.solution(5, 7, new int[][]{{1, 2}, {1, 3}, {1, 4}, {2, 4}, {3, 4}, {3, 5}, {4, 5}}, 4, 5);
        //-1
        sol.solution(4, 2, new int[][]{{1, 3}, {2, 4}}, 3, 4);
    }
}
