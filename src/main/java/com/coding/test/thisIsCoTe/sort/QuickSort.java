package com.coding.test.thisIsCoTe.sort;

import com.coding.test.thisIsCoTe.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 퀵소트 구현
 *
 * [입력 조건]
 *  1 <= nums.length <= 500
 *  1 <= nums[i] <= 100,000
 * [풀이시간] 15분
 * [시간제한] 1초
 */
@Component
public class QuickSort {

    public int[] solution(int[] nums) {

        sort(nums, 0, nums.length - 1);

        System.out.println("result = " + Arrays.toString(nums));
        return nums;
    }

    public void sort(int[] nums, int left, int right) {

        //정렬할 범위가 1이면 이미 정렬이 끝난 숫자
        if (right == left) {
            return;
        }

        int pivot = left;   //기준의 index
        int last = right;   //마지막 index
        left ++;
        int temp;

        while (left < right) {

            //제일 왼쪽부터 기준값과 비교
            for (; left <= last; left++) {
                //기준값보다 큰값을 만나면 break
                if (nums[left] > nums[pivot]) {
                    break;
                }
            }

            //제일 오른쪽부터 기준값과 비교
            for (; right > pivot; right--) {
                //기준값보다 작은값을 만나면 break
                if (nums[right] < nums[pivot]) {
                    break;
                }
            }

            //기준값보다 큰값 index > 기준값보다 작은값 index
            if (left > right) {
                break;
            }

            //기준값보다 큰값과 작은값 swap
            temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }

        //기준값보다 큰값 index > 기준값보다 작은값 index
        //기준값 보다 작은값과 기준값 swap
        //-> 기준값 왼쪽으로는 작은값만, 오른쪽에는 큰값만 위치하게 됨
        temp = nums[pivot];
        nums[pivot] = nums[right];
        nums[right] = temp;

        if (pivot < right) {
            //기준값 왼쪽배열만 정렬
            sort(nums, pivot, right - 1);
        }
        if (left <= last) {
            //기준값 오른쪽배열만 정렬
            sort(nums, left, last);
        }
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        QuickSort sol = ac.getBean(QuickSort.class);

        sol.solution(new int[]{5,7,9,0,3,1,6,2,4,8});
    }

}
