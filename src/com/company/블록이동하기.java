package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

class 블록이동하기 {
    class Pair {
        int r;
        int c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return r == pair.r && c == pair.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    class Robot {
        Pair first;
        Pair second;
        int distance;

        public Robot(Pair first, Pair second, int distance) {
            this.first = first;
            this.second = second;
            this.distance = distance;
        }
    }

    public static int[][] board;

    public int solution(int[][] boardInput) {
        board = boardInput;
        int width = boardInput.length;

        Queue<Robot> q = new LinkedList<>();
        Robot firstRobot = new Robot(new Pair(0, 0), new Pair(0, 1), 0);
        q.offer(firstRobot);
        ArrayList<Robot> visited = new ArrayList<>();
        visited.add(firstRobot);
        while (!q.isEmpty()) {
            Robot currRobot = q.poll();
            if (currRobot.first.equals(new Pair(width - 1, width - 1))
                    || currRobot.second.equals(new Pair(width - 1, width - 1))) {
                return currRobot.distance;
            }
            ArrayList<Robot> nextRobots = getNextRobots(currRobot);
            for (Robot robot : nextRobots) {
                boolean isVisited = false;
                for (Robot visitedRobot : visited) {
                    if ((visitedRobot.first.equals(robot.first) && visitedRobot.second.equals(robot.second))
                            || (visitedRobot.first.equals(robot.second) && visitedRobot.second.equals(robot.first))) {
                        isVisited = true;
                        break;
                    }
                }
                if (isVisited) continue;
                q.offer(robot);
                visited.add(robot);
            }
        }
        int answer = 0;
        return answer;
    }

    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
    public static int[] dd = {-1, 1};

    private ArrayList<Robot> getNextRobots(Robot robot) {
        Pair firstPair = robot.first;
        Pair secondPair = robot.second;
        int distance = robot.distance;

        ArrayList<Robot> nextRobots = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int nr1 = firstPair.r + dr[i];
            int nc1 = firstPair.c + dc[i];
            int nr2 = secondPair.r + dr[i];
            int nc2 = secondPair.c + dc[i];
            if (isOutOfBoard(nr1, nc1, nr2, nc2)) continue;
            if (isWall(nr1, nc1) || isWall(nr2, nc2)) continue;
            nextRobots.add(new Robot(new Pair(nr1, nc1), new Pair(nr2, nc2), distance + 1));
        }

        // 로봇이 가로 방향일 경우
        for (int i = 0; i < 2; i++) {
            int nr1 = firstPair.r + dd[i];
            int nc1 = firstPair.c;
            int nr2 = secondPair.r + dd[i];
            int nc2 = secondPair.c;
            if (isOutOfBoard(nr1, nc1, nr2, nc2)) continue;
            if (isWall(nr1, nc1) || isWall(nr2, nc2)) continue;
            nextRobots.add(new Robot(new Pair(nr1, nc1), new Pair(firstPair.r, firstPair.c), distance + 1));
            nextRobots.add(new Robot(new Pair(nr2, nc2), new Pair(secondPair.r, secondPair.c), distance + 1));
        }

        // 로봇이 세로 방향일 경우
        for (int i = 0; i < 2; i++) {
            int nr1 = firstPair.r;
            int nc1 = firstPair.c + dd[i];
            int nr2 = secondPair.r;
            int nc2 = secondPair.c + dd[i];
            if (isOutOfBoard(nr1, nc1, nr2, nc2)) continue;
            if (isWall(nr1, nc1) || isWall(nr2, nc2)) continue;
            nextRobots.add(new Robot(new Pair(nr1, nc1), new Pair(firstPair.r, firstPair.c), distance + 1));
            nextRobots.add(new Robot(new Pair(nr2, nc2), new Pair(secondPair.r, secondPair.c), distance + 1));
        }

        return nextRobots;
    }

    private boolean isOutOfBoard(int r1, int c1, int r2, int c2) {
        return r1 < 0 || r1 >= board.length
                || c1 < 0 || c1 >= board.length
                || r2 < 0 || r2 >= board.length
                || c2 < 0 || c2 >= board.length;
    }

    private boolean isWall(int r, int c) {
        return board[r][c] == 1;
    }
}
