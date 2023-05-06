package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class 거리두기확인하기 {
    class Pair {
        public int r;
        public int c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    class Node {
        Pair pair;
        int distance;

        public Node(Pair pair, int distance) {
            this.pair = pair;
            this.distance = distance;
        }
    }

    public static final int WIDTH = 5;

    public static final int PEOPLE = 1;
    public static final int TABLE = 0;
    public static final int PARTITION = -1;

    public static final int[] dr = {-1, 1, 0, 0};
    public static final int[] dc = {0, 0, -1, 1};

    public int[] solution(String[][] places) {
        int[] answer = new int[WIDTH];
        for (int placeIndex = 0; placeIndex < places.length; placeIndex++) {
            String[] place = places[placeIndex];
            ArrayList<Pair> people = new ArrayList<>();

            int[][] board = new int[WIDTH][WIDTH];
            for (int i = 0; i < WIDTH; i++) {
                String row = place[i];
                for (int j = 0; j < WIDTH; j++) {
                    char cell = row.charAt(j);
                    if (cell == 'P') {
                        people.add(new Pair(i, j));
                        board[i][j] = PEOPLE;
                    } else if (cell == 'O') {
                        board[i][j] = TABLE;
                    } else if (cell == 'X') {
                        board[i][j] = PARTITION;
                    }
                }
            }

            answer[placeIndex] = 1;
            for (Pair person : people) {
                boolean isViolated = checkViolated(board, person);
                if (isViolated) {
                    answer[placeIndex] = 0;
                    break;
                }
            }
        }

        return answer;
    }

    private boolean checkViolated(int[][] board, Pair targetPerson) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(targetPerson, 0));
        boolean[][] visited = new boolean[WIDTH][WIDTH];

        while (!q.isEmpty()) {
            Node node = q.poll();
            Pair currPair = node.pair;
            int currDistance = node.distance;
            if (currDistance >= 2) continue;
            visited[currPair.r][currPair.c] = true;
            for (int j = 0; j < 4; j++) {
                int nr = currPair.r + dr[j];
                int nc = currPair.c + dc[j];
                if (nr < 0 || nr >= board.length || nc < 0 || nc >= board.length) continue;
                if (visited[nr][nc]) continue;
                if (board[nr][nc] == PARTITION) continue;
                if (board[nr][nc] == PEOPLE) return true;
                if (board[nr][nc] == TABLE) q.offer(new Node(new Pair(nr, nc), currDistance+1));
            }
        }
        return false;
    }
}
