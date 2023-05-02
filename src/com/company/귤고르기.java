package com.company;

import java.util.Arrays;

public class 귤고르기 {
    public static void main(String[] args) {

    }
    public int solution(int[][] targets) {
        Arrays.sort(targets, (target1, target2) -> target1[0] - target2[0]);
        int currEnd = targets[0][1];
        int missileCnt = 1;
        for (int i = 1; i < targets.length; i++) {
            int[] target = targets[i];
            if (currEnd <= target[0]) {
                missileCnt += 1;
                currEnd = target[1];
                continue;
            }
            currEnd = Math.min(target[1], currEnd);
        }
        return missileCnt;
    }
}
