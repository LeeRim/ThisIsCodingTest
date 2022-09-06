package com.coding.test.thisiscote.graph;

import com.coding.test.thisiscote.SpringConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 도시 분할 계획 : level2
 * https://www.acmicpc.net/problem/1647
 * 마을을 2개로 분할할 예정
 * 분리된 마을 안에 있는 임의의 두 집 사이에 경로가 항상 존재해야 한다
 * 마을에는 집이 하나 이상 있어야 한다
 * <p>
 * 분리된 두 마을 사이에 있는 길은 없앤다
 * 길의 유지비의 합을 최소로 마을 분할
 * <p>
 * [입력 조건]
 * 2 <= node <= 100,000
 * 1 <= edge <= 1,000,000
 * graph[i] = a집, b집, 두 집 사이 길 유지비
 * 1 <= 유지비 <= 1,000
 * <p>
 * [풀이시간] 40분
 * [시간제한] 2초
 */
@Component
public class Graph3 {

    public int solution(int node, int edge, int[][] graph) {
        List<RoadCost> roads = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            roads.add(new RoadCost(graph[i][0], graph[i][1], graph[i][2]));
        }
        Collections.sort(roads);

        int[] roots = new int[node + 1];
        for (int i = 1; i <= node; i++) {
            roots[i] = i;
        }

        int total = 0;
        List<RoadCost> resultRoads = new ArrayList<>();
        for (RoadCost road : roads) {
            if (find(roots, road.getStart()) != find(roots, road.getEnd())) {
                union(roots, road.getStart(), road.getEnd());
                resultRoads.add(road);
                total += road.getCost();
            }
        }
        Collections.sort(resultRoads);
        total -= resultRoads.get(resultRoads.size() - 1).getCost();

        System.out.println("total = " + total);
        return total;
    }

    public int find(int[] roots, int a) {
        if (roots[a] != a) {
            roots[a] = find(roots, roots[a]);
        }
        return roots[a];
    }

    public void union(int[] roots, int a, int b) {
        a = roots[a];
        b = roots[b];

        if (a < b) {
            roots[b] = a;
        } else {
            roots[a] = b;
        }
    }

    @Getter
    @AllArgsConstructor
    @ToString
    class RoadCost implements Comparable<RoadCost> {
        private int start;
        private int end;
        private int cost;

        @Override
        public int compareTo(RoadCost o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        Graph3 sol = ac.getBean(Graph3.class);

        //8
        sol.solution(7, 12, new int[][]{{1, 2, 3}, {1, 3, 2}, {3, 2, 1}, {2, 5, 2}, {3, 4, 4}, {7, 3, 6}, {5, 1, 5}, {1, 6, 2}, {6, 4, 1}, {6, 5, 3}, {4, 5, 3}, {6, 7, 4}});
    }
}
