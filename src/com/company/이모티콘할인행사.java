package com.company;

public class 이모티콘할인행사 {
    public static final int[] RATES = {10, 20, 30, 40};
    static int[][] users;
    static int[] emoticons;

    static int answerEmoticonPlusCnt = 0;
    static int answerPriceSum = 0;

    public static void main(String[] args) {

    }

    public int[] solution(int[][] usersInput, int[] emoticonsInput) {
        users = usersInput;
        emoticons = emoticonsInput;

        dfs(0, new int[emoticons.length]);

        return new int[]{answerEmoticonPlusCnt, answerPriceSum};
    }

    private void dfs(int currIndex, int[] currRates) {
        if (currIndex == emoticons.length) {
            updateAnswer(currRates);
            return;
        }
        for (int rate : RATES) {
            currRates[currIndex] = rate;
            dfs(currIndex + 1, currRates);
        }
    }

    private void updateAnswer(int[] rates) {
        int emoticonPlusCnt = 0;
        int totalPriceSum = 0;
        for (int[] user : users) {
            int limitRate = user[0];
            int limitPrice = user[1];

            int cost = 0;
            for (int i = 0; i < emoticons.length; i++) {
                if (rates[i] < limitRate) continue;
                int emoticonPrice = emoticons[i];
                cost += emoticonPrice * (100 - rates[i]) * 0.01;
            }

            if (cost >= limitPrice) {
                emoticonPlusCnt += 1;
            } else {
                totalPriceSum += cost;
            }
        }
        if (answerEmoticonPlusCnt < emoticonPlusCnt ||
                (answerEmoticonPlusCnt == emoticonPlusCnt && answerPriceSum < totalPriceSum)) {
            answerEmoticonPlusCnt = emoticonPlusCnt;
            answerPriceSum = totalPriceSum;
        }
    }
}
