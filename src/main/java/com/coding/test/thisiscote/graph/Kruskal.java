package com.coding.test.thisiscote.graph;

import com.coding.test.thisiscote.SpringConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 그래프 이론
 * 크루스칼 알고리즘
 */
@Slf4j
@Component
public class Kruskal {

    public int solution(int node, int edge, int[][] graph) {
        int[] roots = new int[node + 1];
        DisjointSets.init(roots);

        List<EdgeCost> graphs = new ArrayList<>();
        for (int[] g : graph) {
            graphs.add(new EdgeCost(g[0], g[1], g[2]));
        }
        Collections.sort(graphs);

        int totalCost = 0;
        for (EdgeCost edgeCost : graphs) {
            if (DisjointSets.findRoot(roots, edgeCost.getStart()) != DisjointSets.findRoot(roots, edgeCost.getEnd())) {
                DisjointSets.union(roots, edgeCost.getStart(), edgeCost.getEnd());
                totalCost += edgeCost.getCost();
            }
        }

        System.out.println(totalCost);
        return totalCost;
    }

    @Getter
    @AllArgsConstructor
    @ToString
    class EdgeCost implements Comparable<EdgeCost> {
        private int start;
        private int end;
        private int cost;

        @Override
        public int compareTo(EdgeCost o) {
            return this.cost - o.cost;
        }
    }


    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        Kruskal sol = ac.getBean(Kruskal.class);

        //159
        sol.solution(7, 9, new int[][]{{1, 2, 29}, {1, 5, 75}, {2, 3, 35}, {2, 6, 34}, {3, 4, 7}, {4, 6, 23}, {4, 7, 13}, {5, 6, 53}, {6, 7, 25}});
    }
}
