package com.coding.test.thisIsCoTe.dfsbfs;

import com.coding.test.thisIsCoTe.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 미로 탈출 : level1.5
 * 미로를 탈출하기 위한 이동 거리
 * <p>
 * [입력 조건]
 * 4 <= n,m <= 200
 * 0:벽, 1:길
 * 시작은 1,1 도착은 n,m
 * [풀이시간] 30분
 * [시간제한] 1초
 */
@Component
public class Bfs1 {

    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};

    public int solution(int n, int m, int[][] map) {
        Node node = new Node(0, 0);
        Deque<Node> visit = new LinkedList<>(); //Queue
        visit.add(node);

        Node now;
        int x, y;
        int nx, ny;
        while (!visit.isEmpty()) {
            //현재 좌표
            now = visit.poll();
            x = now.getX();
            y = now.getY();

            //목적지 n-1, m-1에 도착했을 경우
            if (x == n - 1 && y == m - 1) {
                break;
            }

            //상하좌우 확인
            for (int i = 0; i < dx.length; i++) {
                nx = x + dx[i];
                ny = y + dy[i];

                //상하좌우 좌표가 지도 범위인지 확인
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                //방문한적 없는 길(1) or 현재 루트로 방문한 거리 < 다른 루트로 방문한 거리 값
                if (map[nx][ny] == 1 || map[x][y] + 1 < map[nx][ny]) {
                    map[nx][ny] = map[x][y] + 1;
                    visit.add(new Node(nx, ny));
                }
            }
        }

        System.out.println(map[n - 1][m - 1]);
        return map[n - 1][m - 1];
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        Bfs1 sol = ac.getBean(Bfs1.class);

        //return 10
        sol.solution(5, 6, new int[][]{{1, 0, 1, 0, 1, 0}, {1, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}});
    }
}
