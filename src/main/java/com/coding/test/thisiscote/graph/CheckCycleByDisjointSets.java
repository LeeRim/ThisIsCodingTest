package com.coding.test.thisiscote.graph;

import com.coding.test.thisiscote.SpringConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 그래프 이론
 * 서로소 집합을 활용한 사이클 판별
 */
@Slf4j
@Component
public class CheckCycleByDisjointSets {

    public boolean solution(int node, int edge, int[][] graph) {
        int[] roots = new int[node + 1];
        DisjointSets.init(roots);

        boolean cycle = false;
        for (int[] edge1 : graph) {
            if (roots[edge1[0]] == roots[edge1[1]]) {
                cycle = true;
                break;
            }
            DisjointSets.union(roots, edge1[0], edge1[1]);
        }

        System.out.println(Arrays.toString(roots));
        return cycle;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        CheckCycleByDisjointSets sol = ac.getBean(CheckCycleByDisjointSets.class);

        //사이클 발생
        sol.solution(3, 3, new int[][]{{1, 2}, {1, 3}, {2, 3}});
    }
}
