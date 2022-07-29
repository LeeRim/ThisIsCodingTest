package com.coding.test.thisIsCoTe.sort;

import com.coding.test.thisIsCoTe.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * a배열과 b배열의 원소를 k개 교환하여
 * a배열 합 최댓값
 *
 * [입력 조건]
 *  1 <= 배열 길이(n) <= 100,000
 *  0 <= k <= n
 *  0 <= 배열 원소 <= 10,000,000
 * [풀이시간] 20분
 * [시간제한] 2초
 */
@Component
public class Sort3 {

    public int solution(int k, int[] a, int[] b) {

        Arrays.sort(a);
        Integer[] wrapperB = Arrays.stream(b).boxed().toArray(Integer[]::new);
        Arrays.sort(wrapperB, Comparator.reverseOrder());

        for (int i = 0; i < k; i++) {
            a[i] = wrapperB[i];
        }

        System.out.println("Arrays.stream(a).sum() = " + Arrays.stream(a).sum());
        return Arrays.stream(a).sum();
    }


    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        Sort3 sol = ac.getBean(Sort3.class);

        //return 26
        sol.solution(3, new int[]{1,2,5,4,3}, new int[]{5,5,6,6,5}); //n = 5
    }

}
