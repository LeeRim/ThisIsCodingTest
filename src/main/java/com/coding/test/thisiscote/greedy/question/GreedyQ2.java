package com.coding.test.thisiscote.greedy.question;

import com.coding.test.thisiscote.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 곱하기 혹은 더하기 : level1
 * 숫자로만 이루어진 문자열 S
 * 왼쪽부터 하나씩 * 혹은 + 연산을 하여 만들 수 있는 가장 큰 수
 * 만들 수 있는 가장 큰 수는 항상 20억 이하의 정수가 되도록 입력이 주어짐
 * <p>
 * [입력 조건]
 * 1 <= s.length() <= 20
 * <p>
 * [풀이시간] 30분
 * [시간제한] 1초
 */
@Component
public class GreedyQ2 {

    public int solution(String s) {
        int max = Character.getNumericValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int n = Character.getNumericValue(s.charAt(i));
            if (n <= 1 || max <= 1) {
                max += n;
            } else {
                max *= n;
            }
        }

        System.out.println("max = " + max);
        return max;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        GreedyQ2 sol = ac.getBean(GreedyQ2.class);

        //576
        sol.solution("02984");
        //210
        sol.solution("567");
    }
}
