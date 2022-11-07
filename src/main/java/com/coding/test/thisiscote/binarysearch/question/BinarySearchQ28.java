package com.coding.test.thisiscote.binarysearch.question;

import com.coding.test.thisiscote.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 고정점 찾기 : level1.5
 * 오름차순 정렬시 index == Array[i]인 i 찾기
 * i는 최대 1개, 없으면 -1
 * <p>
 * [입력 조건]
 * 1 ≤ N ≤ 1,000,000
 * -10^9 ≤ nums[i] ≤ 10^9
 * [풀이시간] 20분 / O(logN)
 * [시간제한] 1초
 */
@Component
public class BinarySearchQ28 {

    public void solution(int n, int[] nums) {
        Arrays.sort(nums);

        int index = n / 2;
        int checkMid = checkPlus(index, nums);
        while (checkMid != 0) {
            int half = index > 1 ? index / 2 : 1;
            if (checkMid > 0) {
                index -= half;
            } else {
                index += half;
            }
            checkMid = checkPlus(index, nums);
        }
//        System.out.println("index = " + index);

        int point = -1;
        for (int i = index; i < nums.length; i++) {
            if (i < nums[i]) {
                break;
            }
            if (i == nums[i]) {
                point = i;
                break;
            }
        }
        System.out.println("point = " + point);
    }

    //-에서 +로 변환되는 index 찾기
    public int checkPlus(int index, int[] nums) {
        //둘 다 음수
        if (nums[index] < 0) {
            return -1;
        }
        if (index == 0) {
            return 0;
        }
        if (nums[index - 1] < 0) {
            return 0;
        }
        //둘 다 양수
        return 1;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        BinarySearchQ28 sol = ac.getBean(BinarySearchQ28.class);

        sol.solution(5, new int[]{-15, -6, 1, 3, 7}); //3
        sol.solution(7, new int[]{-15, -4, 2, 8, 9, 13, 15}); //2
        sol.solution(7, new int[]{-15, -4, 3, 8, 9, 13, 15}); //-1
    }
}
