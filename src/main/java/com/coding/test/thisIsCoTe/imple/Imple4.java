package com.coding.test.thisIsCoTe.imple;

import com.coding.test.thisIsCoTe.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 게임 개발 : level2
 * n*m map일 때 x,y에서 시작
 * 1.현재 방향에서 왼쪽으로 회전후 한칸 전진
 * 2.이미 방문한 좌표거나 바다일 경우 왼쪽으로 회전 반복
 * 3.4방향 모두 갈 수 없는 경우 방향은 유지한채 뒤로 이동후 1번 진행
 * 4.뒤로 이동할 좌표가 바다일 경우 종료, 이동한 횟수 반환
 *
 * [입력 조건]
 *  n >= 3
 *  m <= 50
 *  map 0:육지, 1:바다
 *  x,y는 육지
 *  to 0:북 1:동 2:남 3:서
 * [풀이시간] 40분
 * [시간제한] 1초
 */
@Component
public class Imple4 {

    public int solution(int n, int m, int x, int y, int to, int[][] map) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        int[][] visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            visited[i] = map[i].clone();
        }
        visited[x][y] = 2;

        int count = 0;
        int nx, ny, i;
        while (true) {
            i = 0;
            for (;i < dx.length; i++) {
                to = (to + 1) % 4;
                nx = x + dx[to];
                ny = y + dy[to];

                //map 외곽
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                //방문하지 않은 경우 방문
                if (visited[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                    visited[nx][ny] = 2;
                    count++;
                    break;
                }
            }

            //4방향 모두 방문할 수 없는 경우 뒤로 이동
            if (i == 4) {
                nx = x + dx[(to + 2) % 4];
                ny = y + dy[(to + 2) % 4];

                //map 외곽
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                //뒤가 바다인 경우 종료
                if (visited[nx][ny] == 1) {
                    break;
                } else {
                    //뒤가 바다가 아니라면 이동
                    x = nx;
                    y = ny;
                    count++;
                }
            }
        }

        System.out.println("count = " + count);
        return count;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        Imple4 sol = ac.getBean(Imple4.class);

        sol.solution(4, 4, 1, 1, 0, new int[][] {{1,1,1,1},{1,0,0,1},{1,1,0,1},{1,1,1,1}});
        sol.solution(4, 4, 1, 1, 0, new int[][] {{1,1,1,1},{1,0,0,1},{1,1,0,1},{1,1,1,1}});
    }
}
