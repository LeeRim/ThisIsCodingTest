package com.coding.test.thisiscote.greedy.question;

import com.coding.test.thisiscote.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.plaf.IconUIResource;

/**
 * 문자열 뒤집기 : level1
 * https://www.acmicpc.net/problem/1439
 * 0 또는 1로만 이루어진 문자열 S
 * 연속된 1개 이상의 숫자를 뒤집을 수 있다
 * 모두 같은 숫자 만들고자 할 때 뒤집는 행동의 최소 횟수
 * <p>
 * [입력 조건]
 * 1 <= s.length() <= 1,000,000
 * <p>
 * [풀이시간] 20분
 * [시간제한] 2초
 */
@Component
public class GreedyQ3 {

    public int solution(String s) {
        int count0 = 0;
        int count1 = 0;

        int before = s.charAt(0) - '0';
        if (before == 0) {
            count0++;
        } else {
            count1++;
        }

        String newStr = String.valueOf(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int now = s.charAt(i) - '0';
            if (now != before) {
                if (now == 0) {
                    count0++;
                    before = 0;
                } else {
                    count1++;
                    before = 1;
                }
            }
        }

        int min = Math.min(count0, count1);
        System.out.println("min = " + min);
        return min;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        GreedyQ3 sol = ac.getBean(GreedyQ3.class);

        sol.solution("0001100"); //1
        sol.solution("11111"); //0
        sol.solution("11001100110011000001"); //4
        sol.solution("11101101"); //2
    }
}
