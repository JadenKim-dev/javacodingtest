package com.company;

class 괄호변환 {
    public String solution(String p) {
        if (p.equals("")) return "";
        int splitPoint = getSplitPoint(p);
        String u = p.substring(0, splitPoint + 1);
        String v = p.substring(splitPoint + 1);

        return isRightString(u)
                ? u + solution(v)
                : "(" + solution(v) + ")" + reverseString(u.substring(1, u.length() - 1));
    }


    private int getSplitPoint(String p) {
        int leftBracketCnt = p.charAt(0) == '(' ? 1 : -1;
        int currIndex = 1;
        while (true) {
            leftBracketCnt += p.charAt(currIndex) == '(' ? 1 : -1;
            if (leftBracketCnt == 0) break;
            currIndex += 1;
        }
        return currIndex;
    }

    private boolean isRightString(String p) {
        int leftBracketCnt = 0;
        for (int i = 0; i < p.length(); i++) {
            leftBracketCnt += p.charAt(i) == '(' ? 1 : -1;
            if (leftBracketCnt < 0) return false;
        }
        return true;
    }

    private String reverseString(String p) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            char bracketToAppend = p.charAt(i) == '(' ?  ')' : '(';
            sb.append(bracketToAppend);
        }
        return sb.toString();
    }
}
