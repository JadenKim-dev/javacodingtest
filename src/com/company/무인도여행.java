package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 무인도여행 {
    public static void main(String[] args) {

    }
    public int[] solution(String[] maps) {
        int height = maps.length;
        int width = maps[0].length();

        int[][] board = new int[height][width];
        for (int i = 0; i < height; i++) {
            String map = maps[i];
            for (int j = 0; j < map.length(); j++) {
                char cell = map.charAt(j);
                if(cell == 'X') {
                    board[i][j] = -1;
                } else {
                    board[i][j] = cell - '0';
                }
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int days = bfs(board, i, j);
                if(days > 0) result.add(days);
            }
        }

        return result.size() == 0
                ? new int[]{-1}
                : result.stream().sorted().mapToInt(Integer::intValue).toArray();
    }

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};

    public int bfs(int[][] board, int i, int j) {
        if(board[i][j] == -1) return -1;

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(i, j));
        int sum = board[i][j];
        board[i][j] = -1;
        int height = board.length;
        int width = board[0].length;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int x = pair.x;
            int y = pair.y;
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx<0 || nx >= height || ny<0 || ny>=width) continue;
                if(board[nx][ny] == -1) continue;
                sum += board[nx][ny];
                board[nx][ny] = -1;
                queue.offer(new Pair(nx, ny));
            }
        }
        return sum;
    }
}
