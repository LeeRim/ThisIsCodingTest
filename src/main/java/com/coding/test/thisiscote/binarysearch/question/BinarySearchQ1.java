package com.coding.test.thisiscote.binarysearch.question;

import com.coding.test.thisiscote.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 정렬된 배열에서 특정 수의 개수 구하기 : level2
 * 크기 n의 정렬된 배열에서 x의 개수
 * <p>
 * [입력 조건]
 * 1 ≤ N ≤ 1,000,000
 * -10^9 ≤ X, nums[i] ≤ 10^9
 * [풀이시간] 30분
 * [시간제한] 1초 / O(logN)
 */
@Component
public class BinarySearchQ1 {

    public void solution(int n, int x, List<Integer> nums) {

        int isIn = Collections.binarySearch(nums, x);
        if (isIn < 0) {
            System.out.println(-1);
            return;
        }
        int count = 0;
        while (isIn >= 0) {
            isIn = Collections.binarySearch(nums, x);
            if (isIn < 0) {
                break;
            }
            count++;
            nums.remove(isIn);
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        BinarySearchQ1 sol = ac.getBean(BinarySearchQ1.class);

        sol.solution(7, 2, new ArrayList<>(List.of(1, 1, 2, 2, 2, 2, 3)));
        sol.solution(7, 4, new ArrayList<>(List.of(1, 1, 2, 2, 2, 2, 3)));
    }
}
