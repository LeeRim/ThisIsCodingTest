package com.coding.test.thisIsCoTe.sort;

import com.coding.test.thisIsCoTe.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 성적 내림차순, 이름 오름차순
 *
 * [입력 조건]
 *  1 <= students.size() <= 100,000
 *  0 <= 점수 <= 100
 * [풀이시간] 20분
 * [시간제한] 1초
 */
@Component
public class Sort2 {

    public List<String> solution(Map<String, Integer> students) {

        List<String> names = new ArrayList<>(students.keySet());

        //name2의 성적 - name1의 성적 > 0 이면 swap(내림차순)
        //names.sort((name1, name2) -> students.get(name2).compareTo(students.get(name1)));

        names.sort(new Comparator<String>() {
            @Override
            public int compare(String name1, String name2) {
                //성적이 같으면 이름 오름차순 / Wrapper 비교 equals()
                if (students.get(name1).equals(students.get(name2))) {
                    return name1.compareTo(name2);
                }
                //성적이 다르면 성적 내림차순
                return students.get(name2).compareTo(students.get(name1));
            }
        });

        System.out.println("names = " + names);
        return names;
    }


    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        Sort2 sol = ac.getBean(Sort2.class);

        //names = [세종, 홍길동, 이순신]
        sol.solution(new HashMap<>() {
            {
                put("홍길동", 95);
                put("이순신", 88);
                put("세종", 95);
            }
        });
    }

}
