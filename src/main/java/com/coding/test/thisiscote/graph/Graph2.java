package com.coding.test.thisiscote.graph;

import com.coding.test.thisiscote.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 팀 결성 : level2
 * 0 ~ n번 학생으로 팀 만들기
 * count개의 연산
 * 0 a b 이면 a, b 팀 합치기
 * 1 a b 이면 a, b 같은 팀인지 확인
 * 같은 팀 여부 확인 연산 결과를 순서대로 출력
 * YES 또는 NO
 *
 * [입력 조건]
 * 1 <= n,count <= 100,000
 * a와 b는 n 이하의 양의 정수
 *
 * [풀이시간] 20분
 * [시간제한] 2초
 */
@Component
public class Graph2 {

    public void solution(int node, int count, int[][] calculations) {
        int[] roots = new int[node + 1];
        for (int i = 0; i <= node; i++) {
            roots[i] = i;
        }

        for (int[] calculation : calculations) {
            if (calculation[0] == 0) {
                union(roots, calculation[1], calculation[2]);
            } else {
                if (find(roots, calculation[1]) == find(roots, calculation[2])) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
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

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        Graph2 sol = ac.getBean(Graph2.class);

        //NO NO YES
        sol.solution(7, 8, new int[][]{{0, 1, 3}, {1, 1, 7}, {0, 7, 6}, {1, 7, 1}, {0, 3, 7}, {0, 4, 2}, {0, 1, 1}, {1, 1, 1}});
    }
}
