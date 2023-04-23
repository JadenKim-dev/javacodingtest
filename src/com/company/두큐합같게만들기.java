package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 두큐합같게만들기 {
    public static void main(String[] args) {

    }
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        long sum1 = 0;
        for (int val: queue1) {
            sum1 += val;
            q1.offer(val);
        }

        Queue<Integer> q2 = new LinkedList<>();
        long sum2 = 0;
        for (int val: queue2) {
            sum2 += val;
            q2.offer(val);
        }

        if((sum1 + sum2) % 2 == 1) {
            return -1;
        }
        long targetSum = (sum1 + sum2) / 2;

        int cnt = 0;
        while(cnt < 2*queue1.length) {
            while (sum1 > targetSum) {
                sum1 -= q1.peek();
                q2.offer(q1.poll());
                cnt += 1;
            }
            if(sum1 == targetSum) {
                return cnt;
            }
            while (sum1 < targetSum) {
                sum1 += q2.peek();
                q1.offer(q2.poll());
                cnt += 1;
            }
            if(sum1 == targetSum) {
                return cnt;
            }
        }
        return -1;
    }
}
