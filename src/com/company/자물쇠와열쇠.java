package com.company;

public class Main {
    public static void main(String[] args) {
        boolean result = solution(new int[][] {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}}, new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}});
        System.out.println(result);
    }

    public static class Point {
        private final int r;
        private final int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public int getR() {
            return r;
        }

        public int getC() {
            return c;
        }
    }

    public static int[][] keyArr;
    public static int[][] lockArr;
    public static int grooveCnt;

    public static boolean solution(int[][] key, int[][] lock) {
        keyArr = key;
        lockArr = lock;
        grooveCnt = 0;
        for (int i = 0; i < lockArr.length; i++) {
            for (int j = 0; j < lockArr.length; j++) {
                if (lockArr[i][j] == 0) {
                    grooveCnt += 1;
                }
            }
        }

        if (grooveCnt == 0) return true;

        for (int dr = -keyArr.length; dr < lockArr.length; dr++) {
            for (int dc = -keyArr.length; dc < lockArr.length; dc++) {
                for (int k = 0; k < 4; k++) {
                    if (isKeyMatchesWith(dr, dc, k)) return true;
                }
            }
        }
        return false;
    }

    private static boolean isKeyMatchesWith(int dr, int dc, int k) {
        int matchCnt = 0;
        for (int i = 0; i < keyArr.length; i++) {
            for (int j = 0; j < keyArr.length; j++) {
                Point currPoint = new Point(i, j);
                for (int l = 0; l < k; l++) {
                    currPoint = rotateRight(currPoint, keyArr.length);
                }
                int nr = currPoint.getR() + dr;
                int nc = currPoint.getC() + dc;

                if (nr < 0 || nr >= lockArr.length || nc < 0 || nc >= lockArr.length) {
                    continue;
                }

                if (keyArr[i][j] == 1) {
                    if (lockArr[nr][nc] == 1) return false;
                    matchCnt += 1;
                } else if (lockArr[nr][nc] == 0) return false;
            }
        }
        return matchCnt == grooveCnt;
    }

    private static Point rotateRight(Point point, int width) {
        int r = point.getR();
        int c = point.getC();
        return new Point(c, width - 1 - r);
    }
}
