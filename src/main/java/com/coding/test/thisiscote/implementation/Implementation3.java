package com.coding.test.thisiscote.implementation;

import com.coding.test.thisiscote.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 왕실의 나이트
 * 나이트가 이동할 수 있는 좌표값의 경우의 수
 *  1.수평으로 2칸이동 후 수직으로 1칸 이동
 *  2.수직으로 2칸이동 후 수평으로 1칸 이동
 *
 * [입력 조건]
 *  1 <= row <= 8
 *  a <= col <= h
 * [풀이시간] 20분
 * [시간제한] 1초
 */
@Component
public class Implementation3 {

    public int solution(String point) {
        int x = Character.getNumericValue(point.charAt(1));
        //97 ~ 104
        int y = point.charAt(0);
        int[] dx = {-2, -2, 2, 2, -1, 1, -1, 1};
        int[] dy = {-1, 1, -1, 1, -2, -2, 2, 2};

        int count = 0;
        int nx, ny;
        for (int i = 0; i < dx.length; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if (nx < 1 || ny < 97 || nx > 8 || ny > 104) {
                continue;
            }

//            System.out.println(nx + "," + (char)ny);
            count++;
        }

        System.out.println("count = " + count);
        return 0;
    }

    public int solution2(String point) {
        int x = Character.getNumericValue(point.charAt(1));
        int y = point.charAt(0) - 96;
        int[][] dp = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {1, -2}, {-1, 2}, {1, 2}};

        int count = 0;
        int nx, ny;
        for (int i = 0; i < dp.length; i++) {
            nx = x + dp[i][0];
            ny = y + dp[i][1];
            if (nx < 1 || ny < 1 || nx > 8 || ny > 8) {
                continue;
            }

//            System.out.println(nx + "," + ny);
            count++;
        }

        System.out.println("count = " + count);
        return 0;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        Implementation3 sol = ac.getBean(Implementation3.class);

        sol.solution("a1");
        sol.solution("a1");
        sol.solution2("a1");
    }
}
