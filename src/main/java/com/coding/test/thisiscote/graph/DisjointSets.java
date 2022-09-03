package com.coding.test.thisiscote.graph;

import com.coding.test.thisiscote.SpringConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 그래프 이론
 * 서로소 집합 알고리즘
 */
@Slf4j
@Component
public class DisjointSets {

    int[] roots;

    public void solution(int node, int edge, int[][] graph) {
        roots = new int[node + 1];
        for (int i = 1; i <= node; i++) {
            roots[i] = i;
        }

        for (int[] edge1 : graph) {
            union(edge1[0], edge1[1]);
        }

        for (int i = 1; i <= node; i++) {
            System.out.print(findRoot(i) + " ");
        }
        System.out.println();

        System.out.println(Arrays.toString(roots));
    }

    public int findRoot(int n) {
        if (roots[n] != n) {
            roots[n] = findRoot(roots[n]);
        }
        return roots[n];
    }

    public void union(int a, int b) {
        int aRoot = findRoot(a);
        int bRoot = findRoot(b);
        if (aRoot < bRoot) {
            roots[b] = aRoot;
        } else {
            roots[a] = bRoot;
        }
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        DisjointSets sol = ac.getBean(DisjointSets.class);

        //[0, 1, 1, 1, 1, 5, 5]
        sol.solution(6, 4, new int[][]{{1, 4}, {2, 3}, {2, 4}, {5, 6}});
    }
}
