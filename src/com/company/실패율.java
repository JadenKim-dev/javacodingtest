package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class 실패율 {
    public int[] solution(int N, int[] inputStages) {
        int[] tryCnt = new int[N + 2];
        int[] failCnt = new int[N + 2];
        Integer[] stages = Arrays.stream(inputStages).boxed().toArray(Integer[]::new);
        Arrays.sort(stages, Collections.reverseOrder());

        int prevStage = N + 1;
        for (int stage : stages) {
            if (stage != prevStage) {
                for (int i = N; i >= prevStage; i--) {
                    if (tryCnt[i] == 0) tryCnt[i] = tryCnt[i + 1];
                }
                tryCnt[stage] = tryCnt[prevStage];
                prevStage = stage;
            }
            tryCnt[stage] += 1;
            failCnt[stage] += 1;
        }
        for (int i = prevStage - 1; i >= 1; i--) {
            if (tryCnt[i] == 0) tryCnt[i] = tryCnt[i + 1];
        }

        Integer[] answer = new Integer[N];
        for (int i = 1; i < N + 1; i++) {
            answer[i - 1] = i;
        }
        Arrays.sort(answer, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                double failureRate1 = getFailureRate(failCnt, tryCnt, o1);
                double failureRate2 = getFailureRate(failCnt, tryCnt, o2);
                if (failureRate1 - failureRate2 < 0) return 1;
                if (failureRate1 - failureRate2 > 0) return -1;
                return o1 - o2;
            }
        });
        return Arrays.stream(answer).mapToInt(Integer::intValue).toArray();
    }

    private double getFailureRate(int[] failCnt, int[] tryCnt, int index) {
        if (tryCnt[index] == 0) return 0;
        return (double) failCnt[index] / tryCnt[index];
    }
}
