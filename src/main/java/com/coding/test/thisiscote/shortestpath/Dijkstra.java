package com.coding.test.thisiscote.shortestpath;

import com.coding.test.thisiscote.SpringConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
    private final int MAX = (int) 1e9;

    public int[] byHeap(int node, int edge, int start, int[][] roads) {

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
        distances[start] = 0;

        //BFS
        PriorityQueue<NodeDistance> distancesHeap = new PriorityQueue<>();
        distancesHeap.offer(new NodeDistance(start, 0));

        while (!distancesHeap.isEmpty()) {
            NodeDistance now = distancesHeap.poll();
            int nowNode = now.getNode();
            int nowDistance = now.getDistance(); //출발지 ~ nowNode 거리

            //distances[nowNode] > nowDistance인 경우는 없다
            //nowNode까지의 최소거리가 nowDistance보다 작으면 nowNode는 이미 heap에 들어간적 있음
            if (distances[nowNode] < nowDistance) {
                continue;
            }

            //nowDistance가 현재 최소 거리 -> distances[nowNode] == nowDistance
            //nowNode에서 갈 수 있는 node(next)들의 최소거리 계산해서 distances[]에 입력
            for (NodeDistance next : graph.get(nowNode)) {
                //다음 노드 최소거리 > 현재 노드 최소거리 + 현재 노드에서 다음 노드 거리
                if (distances[next.getNode()] > nowDistance + next.getDistance()) {
                    distances[next.getNode()] = nowDistance + next.getDistance();
                    distancesHeap.offer(new NodeDistance(next.getNode(), distances[next.getNode()]));
                }
            }
        }

        System.out.println(Arrays.toString(distances));
        return distances;
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

        Dijkstra sol = ac.getBean(Dijkstra.class);

        //[1000000000, 0, 2, 3, 1, 2, 4]
        sol.byHeap(6, 11, 1, new int[][]{{1, 2, 2}, {1, 3, 5}, {1, 4, 1}, {2, 3, 3}, {2, 4, 2}, {3, 2, 3}, {3, 6, 5}, {4, 3, 3}, {4, 5, 1}, {5, 3, 1}, {5, 6, 2}});
    }
}
