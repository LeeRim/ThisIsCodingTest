package com.coding.test.thisiscote.shortestpath;

import com.coding.test.thisiscote.SpringConfig;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 최단거리
 * 다익스트라 알고리즘 구현
 */
@Slf4j
@Component
public class Dijkstra {

    public int[] byHeap(int node, int edge, int source, int[][] roads) {
        final int MAX = (int) 1e9;

        Map<Integer, List<NodeDistance>> graph = new HashMap<>();
        for (int i = 0; i <= node; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] road : roads) {
            graph.get(road[0]).add(new NodeDistance(road[1], road[2]));
        }

        //해당 노드까지의 최단 거리
        int[] distances = new int[node + 1];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = MAX;
        }
        distances[source] = 0;

        //BFS
        PriorityQueue<NodeDistance> distancesHeap = new PriorityQueue<>();
        distancesHeap.offer(new NodeDistance(1, 0));

        while (!distancesHeap.isEmpty()) {
            log.debug("\nDijkstra.byHeap : distancesHeap = {}", distancesHeap);
            NodeDistance now = distancesHeap.poll();
            int nowNode = now.getNode();
            int nowDistance = now.getDistance();

            //nowNode까지의 최소거리가 nowDistance(now에 오기까지 총 거리)보다 작으면 nowNode는 이미 방문 이력이 있음
            if (distances[nowNode] < nowDistance) {
                continue;
            }

            //nowNode에서 갈 수 있는 node(next)들의 최소거리 계산해서 distances[]에 입력
            for (int i = 0; i < graph.get(nowNode).size(); i++) {
                NodeDistance next = graph.get(nowNode).get(i);

                //다음 노드 최소거리 > 현재 노드 최소거리 + 현재 노드에서 다음 노드 거리
                if (distances[next.getNode()] > distances[nowNode] + next.getDistance()) {
                    distances[next.getNode()] = distances[nowNode] + next.getDistance();
                    distancesHeap.offer(new NodeDistance(next.getNode(), distances[next.getNode()]));
                }
            }
        }

        log.debug("\nDijkstra.byHeap : distances = {}", Arrays.toString(distances));
        return distances;
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

        Dijkstra sol = ac.getBean(Dijkstra.class);

        //[1000000000, 0, 2, 3, 1, 2, 4]
        sol.byHeap(6, 11, 1, new int[][]{{1, 2, 2}, {1, 3, 5}, {1, 4, 1}, {2, 3, 3}, {2, 4, 2}, {3, 2, 3}, {3, 6, 5}, {4, 3, 3}, {4, 5, 1}, {5, 3, 1}, {5, 6, 2}});
    }
}
