package com.coding.test.thisiscote.shortestpath;

import com.coding.test.thisiscote.SpringConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 전보 : level3
 * 출발지에서 갈 수 있는 모든 node 수, 동시에 출발해서 모두 방문하는데 걸리는 비용(max 비용)
 *
 * [입력 조건]
 * 1 <= node <= 30,000
 * 1 <= edge <= 200,000
 * 1 <= start <= node
 *
 * roads[i] = {node1, node2, 비용} node1에서 node2까지 비용
 * 1 <= roads[i][0], roads[i][1] <= node
 * 1 <= roads[i][2] <= 1,000
 * [풀이시간] 60분
 * [시간제한] 1초
 */
@Component
public class ShortestPath2 {
    private static final int MAX = (int) 1e9;

    public String solution(int node, int edge, int start, int[][] roads) {
        Map<Integer, List<NodeDistance>> graph = new HashMap<>();
        for (int i = 1; i <= node; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] road : roads) {
            graph.get(road[0]).add(new NodeDistance(road[1], road[2]));
        }

        int[] cost = new int[node + 1];
        for (int i = 0; i <= node; i++) {
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

            for (NodeDistance next : graph.get(now.getNode())) {
                if (cost[next.getNode()] > now.getDistance() + next.getDistance()) {
                    cost[next.getNode()] = now.getDistance() + next.getDistance();
                    nexts.offer(new NodeDistance(next.getNode(), cost[next.getNode()]));
                }
            }
        }

        int count = -1;
        int maxCost = 0;
        for (int c : cost) {
            if (c < MAX) {
                count++;
                maxCost = Math.max(maxCost, c);
            }
        }

        System.out.println(count + " " + maxCost);
        return count + " " + maxCost;
    }

    @ToString
    @Getter
    @RequiredArgsConstructor
    class NodeDistance implements Comparable<NodeDistance> {
        private final int node;
        private final int distance;

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

        ShortestPath2 sol = ac.getBean(ShortestPath2.class);

        //2 4
        sol.solution(3, 2, 1, new int[][]{{1, 2, 4}, {1, 3, 2}});
    }
}
