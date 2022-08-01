package com.coding.test.thisiscote.implementation;

import com.coding.test.thisiscote.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 상하좌우
 * n*n 지도에서 상하좌우로 1칸씩 이동해 도작 좌표 구하기
 * 시작은 1,1
 *
 * [입력 조건]
 *  1 <= n <= 100
 *  1 <= movw.len <= 100
 * [풀이시간] 15분
 * [시간제한] 1초
 */
@Component
public class Implementation1 {

    public String solution(int n, String move) {
        String[] moves = move.split(" ");

        int x = 1;
        int y = 1;
        for (String s : moves) {
//            System.out.println("x : " + x + " / y : " + y);
            if (s.equals("U")) {
                if (x != 1) {
                    x--;
                }
                continue;
            }
            if (s.equals("D")) {
                if (x != n) {
                    x++;
                }
                continue;
            }
            if (s.equals("L")) {
                if (y != 1) {
                    y--;
                }
                continue;
            }
            if (s.equals("R")) {
                if (y != n) {
                    y++;
                }
                continue;
            }
        }

//        String result = String.valueOf(x) + " " + String.valueOf(y);
        String result = x + " " + y;
        System.out.println(result);
        return result;
    }

    public String solution2(int n, String move) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        String[] move_type = {"U", "D", "L", "R"};

        int x = 1, y = 1, nx, ny;
        String[] moves = move.split(" ");
        for (String m : moves) {
            for (int i = 0; i < move_type.length; i++) {
                if (m.equals(move_type[i])) {
                    nx = x + dx[i];
                    ny = y + dy[i];
                    if (nx < 1 || ny < 1 || nx > n || ny > n) {
                        break;
                    }

                    x = nx;
                    y = ny;
//                    System.out.println("x : " + x + " / y : " + y);
                    break;
                }
            }
        }

//        String result = String.valueOf(x) + " " + String.valueOf(y);
        String result = x + " " + y;
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        Implementation1 sol = ac.getBean(Implementation1.class);

        sol.solution(5, "R R R U D D");
//        sol.solution(5, "R R R U D D");
//        sol.solution2(5, "R R R U D D");
    }
}
