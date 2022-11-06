package com.coding.test.thisiscote.sort.question;

import com.coding.test.thisiscote.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 안테나 : level1
 * https://www.acmicpc.net/problem/18310
 * <p>
 * [입력 조건]
 * 1 <= n <= 200,000
 * [풀이시간] 20분
 * [시간제한] 1초
 */
@Component
public class SortQ24 {

    public void solution(int n, int[] houses) {

        Arrays.sort(houses);

        System.out.println(houses[(n - 1) / 2]);
    }

    public static void main(String[] args) throws IOException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        SortQ24 sol = ac.getBean(SortQ24.class);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] houses = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        sol.solution(n, houses);
    }
}
