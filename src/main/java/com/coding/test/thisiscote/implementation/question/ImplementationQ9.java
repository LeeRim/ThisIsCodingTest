package com.coding.test.thisiscote.implementation.question;

import com.coding.test.thisiscote.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 문자열 압축 : level1.5
 * https://school.programmers.co.kr/learn/courses/30/lessons/60057
 *
 * [입력 조건]
 * 1 <= s.length() <= 1,000
 * s는 알파벳 소문자로만 이루어져 있다
 * [풀이시간] 30분
 * [시간제한] 1초
 */
@Component
public class ImplementationQ9 {

    public int solution(String s) {

        int answer = s.length();
        for (int i = 1; i <= s.length() / 2; i++) {
            answer = Math.min(compressFromFirst(s, i), answer);
        }

        System.out.println(answer);
        return answer;
    }

    public int compressFromFirst(String s, int len) {
        String standard = s.substring(0, len);
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (int i = len; i <= s.length(); i += len) {
            String substring = s.substring(i, i + len > s.length() ? s.length() : i + len);
            if (standard.equals(substring)) {
                count++;
                continue;
            }
            if (count > 1) {
                result.append(count);
                count = 1;
            }
            result.append(standard);
            standard = substring;
        }
        result.append(standard);

        return result.length();
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        ImplementationQ9 sol = ac.getBean(ImplementationQ9.class);

        sol.solution("aabbaccc");
        sol.solution("ababcdcdababcdcd");
        sol.solution("abcabcdede");
        sol.solution("abcabcabcabcdededededede");
        sol.solution("xababcdcdababcdcd");
        sol.solution("aaaaaaaaaab"); //4
    }
}
