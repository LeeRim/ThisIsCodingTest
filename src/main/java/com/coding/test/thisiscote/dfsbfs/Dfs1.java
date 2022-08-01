package com.coding.test.thisiscote.dfsbfs;

import com.coding.test.thisiscote.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 음료수 얼려 먹기 : level1.5
 * 한 번에 만들 수 있는 얼음 수
 *
 * [입력 조건]
 *  1 <= n,m <= 1000
 *  0:얼음칸, 1:칸막이
 * [풀이시간] 30분
 * [시간제한] 1초
 */
@Component
public class Dfs1 {

    private int[][] visited;
    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};

    public int solution(int n, int m, int[][] graph) {
        visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            visited[i] = graph[i].clone();
        }

        int count = 0;
        Node node;
        while (findNext() != null) {
            count++;
            //방문하지 않은 좌표
            node = findNext();
            visit(node.getX(), node.getY());
        }

        System.out.println("count = " + count);
        return count;
    }

    public Node findNext() {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                if (visited[i][j] == 0) {
                    return new Node(i, j);
                }
            }
        }
        return null;
    }

    public void visit(int x, int y) {
        if (visited[x][y] != 0) {
            return;
        }

        //방문 처리
        visited[x][y] = 2;

        int nx, ny;
        for (int i = 0; i < dx.length; i++) {
            //상하좌우 좌표
            nx = x + dx[i];
            ny = y + dy[i];

            //좌표가 그래프 범위 외 이면 다음 좌표 확인
            if (nx < 0 || ny < 0 || nx >= visited.length || ny >= visited[0].length) {
                continue;
            }
            //상하좌우 좌표 방문
            visit(nx, ny);
        }

    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        Dfs1 sol = ac.getBean(Dfs1.class);

        //return 8
        sol.solution(4, 5, new int[][] {{0,0,1,1,0},{0,0,0,1,1},{1,1,1,1,1},{0,0,0,0,0}});
    }
}
