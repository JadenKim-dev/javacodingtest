package com.company;

import java.util.*;

public class 튜플 {
    public static void main(String[] args) {
        int[] answer = solution("1234");
        System.out.println(Arrays.toString(answer));
    }

    public static int[] solution(String s) {
        String[][] tuples = Arrays.stream(s.substring(0, s.length() - 2).split("}"))
                .sorted((String s1, String s2) -> s1.length() - s2.length())
                .map((String entityStr) -> entityStr.substring(2))
                .map((String entitySubstr) -> entitySubstr.split(","))
                .toArray(String[][]::new);

        int[] answer = new int[tuples.length];
        Set<String> includedVals = new HashSet<>();

        for (int i=0; i< tuples.length; i++) {
            String[] tuple = tuples[i];
            for (String val : tuple) {
                if(includedVals.add(val)) {
                    answer[i] = Integer.parseInt(val);
                    break;
                }
            }
        }
        return answer;
    }
}
