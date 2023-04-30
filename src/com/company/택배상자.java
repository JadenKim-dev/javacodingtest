package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 택배상자 {
    public static void main(String[] args) {

    }
    public int solution(int[] order) {
        Queue<Integer> belt = new LinkedList<>();
        for (int i = 1; i <= order.length; i++) {
            belt.offer(i);
        }
        Stack<Integer> subBelt = new Stack<>();

        int currOrderIndex = 0;
        while (!(belt.isEmpty() && subBelt.isEmpty())) {
            if(!belt.isEmpty() && belt.peek() == order[currOrderIndex]) {
                belt.poll();
                currOrderIndex += 1;
                continue;
            }
            if(!subBelt.isEmpty() && subBelt.peek() == order[currOrderIndex]) {
                subBelt.pop();
                currOrderIndex += 1;
                continue;
            }
            if(!belt.isEmpty()) {
                subBelt.push(belt.poll());
                continue;
            }
            break;
        }
        return currOrderIndex+1;
    }
}
