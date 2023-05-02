package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class 미로탈출 {
    public static void main(String[] args) {

    }

    int WALL = -1;
    int INF = 100000;
    int[] dx = new int[] {-1, 1, 0, 0};
    int[] dy = new int[] {0, 0, -1, 1};

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(String[] maps) {
        int height = maps.length;
        int width = maps[0].length();
        int[][] board = new int[height][width];

        int startx = -1;
        int starty = -1;
        int leverx = -1;
        int levery = -1;
        int exitx = -1;
        int exity = -1;

        for (int i = 0; i < height; i++) {
            String row = maps[i];
            for (int j = 0; j < width; j++) {
                char cell = row.charAt(j);
                if(cell == 'S') {
                    startx = i;
                    starty = j;
                    board[i][j] = INF;
                } else if(cell == 'E') {
                    exitx = i;
                    exity = j;
                    board[i][j] = INF;
                } else if(cell == 'L') {
                    leverx = i;
                    levery = j;
                    board[i][j] = INF;
                } else if(cell == 'X') {
                    board[i][j] = WALL;
                } else {
                    board[i][j] = INF;
                }
            }
        }

        int distanceToLever = getDistance(board, startx, starty, leverx, levery);
        clearBoard(board);
        int distanceToExit = getDistance(board, leverx, levery, exitx, exity);

        int answer = distanceToLever + distanceToExit;

        return answer < INF ? answer : -1;
    }

    private int getDistance(int[][] board, int startx, int starty, int targetx, int targety) {
        int height = board.length;
        int width = board[0].length;

        Queue<Pair> q = new LinkedList<>();
        board[startx][starty] = 0;
        q.offer(new Pair(startx, starty));

        while(!q.isEmpty()) {
            Pair pair = q.poll();
            int x = pair.x;
            int y = pair.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || nx >= height || ny < 0 || ny >= width) continue;
                if(board[nx][ny] == WALL) continue;
                if(board[x][y]+1 < board[nx][ny]) {
                    board[nx][ny] = board[x][y]+1;
                    q.offer(new Pair(nx, ny));
                }
            }
        }
        return board[targetx][targety];
    }

    private void clearBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] >= 0) {
                    board[i][j] = INF;
                }
            }
        }
    }
}
