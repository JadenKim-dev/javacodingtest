package com.company;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class 무지의먹방라이브 {
    public static void main(String[] args) {

    }

    private class Food implements Comparable<Food> {
        private final int time;
        private final int index;

        public Food(int time, int index) {
            this.time = time;
            this.index = index;
        }

        public int getTime() {
            return time;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public int compareTo(Food other) {
            return Integer.compare(this.time, other.time);
        }
    }

    public int solution(int[] foodTimes, long endTime) {
        PriorityQueue<Food> pq = new PriorityQueue<>();
        for (int i = 0; i < foodTimes.length; i++) {
            pq.offer(new Food(foodTimes[i], i + 1));
        }

        int lastFoodTime = 0;
        while (!pq.isEmpty()) {
            long timeToSpend = (long) (pq.peek().getTime() - lastFoodTime) * pq.size();
            if (endTime < timeToSpend) break;
            lastFoodTime = pq.poll().getTime();
            endTime -= timeToSpend;
        }

        if(pq.isEmpty()) return -1;

        ArrayList<Food> foodList = new ArrayList<>();
        while (!pq.isEmpty()) {
            foodList.add(pq.poll());
        }
        foodList.sort((food1, food2) -> Integer.compare(food1.getIndex(), food2.getIndex()));
        return foodList.get((int) (endTime % foodList.size())).getIndex();
    }

}
