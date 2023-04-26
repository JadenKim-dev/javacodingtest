package com.company;

public class 택배배달과수거하기 {
    public static void 택배배달과수거하기(String[] args) {
        long answer = solution(2, 2, new int[]{0, 6}, new int[]{0, 0});

        System.out.println(answer);
    }

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int currFarDelivery = n;
        int currFarPickup = n;

        long answer = 0;
        while(currFarDelivery > 0 || currFarPickup > 0) {
            while(currFarDelivery > 0 && deliveries[currFarDelivery -1] == 0) {
                currFarDelivery -= 1;
            }
            while(currFarPickup > 0 && pickups[currFarPickup -1] == 0) {
                currFarPickup -= 1;
            }
            answer += 2 * Math.max(currFarDelivery, currFarPickup);
            currFarDelivery = getCurrLocation(currFarDelivery, cap, deliveries);
            currFarPickup = getCurrLocation(currFarPickup, cap, pickups);
        }

        return answer;
    }

    private static int getCurrLocation(int far, int cap, int[] boxes) {
        int location = far;
        int currCap = cap;

        while(location > 0) {
            if(currCap > boxes[location -1]) {
                currCap -= boxes[location -1];
                boxes[location -1] = 0;
                location -= 1;
            } else {
                boxes[location -1] -= currCap;
                break;
            }
        }
        return location;
    }
}
