package com.company;

import java.util.*;

public class 롤케이크자르기 {
    public static void main(String[] args) {
        int answer = solution(new int[] {1, 2, 3, 1, 4});
        System.out.println(answer);
    }

    public static int solution(int[] toppings) {
        HashMap<Integer, Integer> toppingToCntMap1 = new HashMap<>();
        int toppingTypeNum1 = 0;

        HashMap<Integer, Integer> toppingToCntMap2 = new HashMap<>();
        int toppingTypeNum2 = 0;

        for(int topping : toppings) {
            if(toppingToCntMap1.containsKey(topping)) {
                toppingToCntMap1.put(topping, toppingToCntMap1.get(topping)+1);
            } else {
                toppingToCntMap1.put(topping, 1);
                toppingTypeNum1 += 1;
            }
            toppingToCntMap2.put(topping, 0);
        }

        int cnt = 0;
        int answer = 0;
        for (int i = 0; i < toppings.length-1; i++) {
            int topping = toppings[i];
            toppingToCntMap1.put(topping, toppingToCntMap1.get(topping)-1);
            if(toppingToCntMap1.get(topping) == 0) {
                toppingTypeNum1 -= 1;
            }
            toppingToCntMap2.put(topping, toppingToCntMap2.get(topping)+1);
            if(toppingToCntMap2.get(topping) == 1) {
                toppingTypeNum2 += 1;
            }
            if(toppingTypeNum1 == toppingTypeNum2) {
                answer += 1;
            }
        }
        return answer;
    }
}
