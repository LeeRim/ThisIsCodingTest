package com.coding.test.thisiscote.graph;

import com.coding.test.thisiscote.SpringConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 그래프 이론
 * 위상 정렬
 */
@Slf4j
@Component
public class TopologySort {

    public List<Integer> solution(int node, int edge, int[][] graph) {

        List<List<Integer>> toNodes = new ArrayList<>();
        for (int i = 0; i <= node; i++) {
            toNodes.add(new ArrayList<>());
        }
        for (int[] g : graph) {
            toNodes.get(g[0]).add(g[1]);
        }

        int[] indegree = new int[node + 1];
        for (int[] g : graph) {
            indegree[g[1]]++;
        }


        Queue<Integer> zeroIndegree = new LinkedList<>();
        for (int i = 1; i <= node; i++) {
            if (indegree[i] == 0) {
                zeroIndegree.offer(i);
            }
        }

        List<Integer> topologyList = new ArrayList<>();
        while (!zeroIndegree.isEmpty()) {
            int now = zeroIndegree.poll();
            topologyList.add(now);

            for (int next : toNodes.get(now)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    zeroIndegree.offer(next);
                }
            }
        }

        System.out.println(topologyList);
        return topologyList;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        TopologySort sol = ac.getBean(TopologySort.class);

        //1, 2, 5, 3, 6, 4, 7
        sol.solution(7, 8, new int[][]{{1, 2}, {1, 5}, {2, 3}, {2, 6}, {3, 4}, {4, 7}, {5, 6}, {6, 4}});
    }
}
