package com.company;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class 미사일 {
    public static void main(String[] args) {

    }

    int dateStrToTime(String dateStr) {
        String[] splitedDateStr = dateStr.split(":");
        int hour = Integer.parseInt(splitedDateStr[0]);
        int minute = Integer.parseInt(splitedDateStr[1]);
        return hour*60 + minute;
    }

    public int solution(String[][] bookTimes) {
        int[][] intBookTimes = Arrays.stream(bookTimes).map(bookTime -> {
            String start = bookTime[0];
            int startTime = dateStrToTime(start);
            String end = bookTime[1];
            int endTime = dateStrToTime(end) + 10;
            return new int[] {startTime, endTime};
        }).toArray(int[][]::new);

        Arrays.sort(intBookTimes, (bookTime1, bookTime2) -> bookTime1[0] - bookTime2[0]);

        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        rooms.offer(intBookTimes[0][1]);
        for (int i = 1; i < intBookTimes.length; i++) {
            int[] bookTimeArr = intBookTimes[i];
            int startTime = bookTimeArr[0];
            int endTime = bookTimeArr[1];

            int earliestOutRoom = rooms.peek();
            if(earliestOutRoom <= startTime) {
                rooms.poll();
                rooms.offer(endTime);
            } else {
                rooms.offer(endTime);
            }
        }
        return rooms.size();
    }
}
