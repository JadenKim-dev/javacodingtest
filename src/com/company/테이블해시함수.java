package com.company;

import java.util.Arrays;

public class 테이블해시함수 {
    public static void main(String[] args) {

    }
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (a1, a2) -> {
            if(a1[col-1] - a2[col-1] != 0) return a1[col-1] - a2[col-1];
            return a2[0] - a1[0];
        });

        int[] rests = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            int[] record = data[i];
            rests[i] = 0;
            for (int val : record) {
                rests[i] += (val % (i+1));
            }
        }

        int result = rests[row_begin-1];
        for (int i = row_begin; i < row_end; i++) {
            result ^= rests[i];
        }

        return result;
    }
}
