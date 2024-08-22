import java.io.*;
import java.util.*;

class Solution {

    static String[][] table;
    static ArrayList<String> list;
    static ArrayList<Pos>[][] merge;
    static final int MAX = 51;

    public String[] solution(String[] commands) {
        list = new ArrayList<>();
        table = new String[MAX][MAX];
        merge = new ArrayList[MAX][MAX];
        for (int i = 1; i < MAX; i++) {
            for (int j = 1; j < MAX; j++) {
                table[i][j] = "";
                merge[i][j] = new ArrayList<>();
            }
        }
        for (String command : commands) {
            String[] com = command.split(" ");
            excuteCommand(com);
        }
        String[] answer = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    static void excuteCommand(String[] command) {
        switch (command[0]) {
            case "UPDATE":
                if (command.length == 4) {
                    int r = Integer.parseInt(command[1]);
                    int c = Integer.parseInt(command[2]);
                    String value = command[3];
                    boolean[][] visit = new boolean[MAX][MAX];
                    table[r][c] = value;
                    visit[r][c] = true;
                    update(visit, r, c, value);
                } else {
                    String value1 = command[1];
                    String value2 = command[2];
                    for (int i = 1; i < MAX; i++) {
                        for (int j = 1; j < MAX; j++) {
                            if (table[i][j].equals(value1)) {
                                table[i][j] = value2;
                            }
                        }
                    }
                }
                break;

            case "MERGE":
                int r1 = Integer.parseInt(command[1]);
                int c1 = Integer.parseInt(command[2]);
                int r2 = Integer.parseInt(command[3]);
                int c2 = Integer.parseInt(command[4]);

                merge[r1][c1].add(new Pos(r2, c2));
                merge[r2][c2].add(new Pos(r1, c1));
                boolean[][] visit = new boolean[MAX][MAX];

                if (table[r1][c1].isEmpty()) {
                    table[r1][c1] = table[r2][c2];
                    visit[r1][c1] = true;
                    update(visit, r1, c1, table[r2][c2]);
                } else {
                    table[r2][c2] = table[r1][c1];
                    visit[r2][c2] = true;
                    update(visit, r2, c2, table[r1][c1]);
                }
                break;

            case "UNMERGE":
                int r = Integer.parseInt(command[1]);
                int c = Integer.parseInt(command[2]);
                visit = new boolean[MAX][MAX];
                visit[r][c] = true;
                unmerge(visit, r, c);
                merge[r][c] = new ArrayList<>();
                break;

            case "PRINT":
                r = Integer.parseInt(command[1]);
                c = Integer.parseInt(command[2]);
                if (table[r][c].isEmpty()) {
                    list.add("EMPTY");
                } else {
                    list.add(table[r][c]);
                }
                break;
        }
    }

    static void update(boolean[][] visit, int r, int c, String s) {
        for (int k = 0; k < merge[r][c].size(); k++) {
            Pos next = merge[r][c].get(k);
            if (visit[next.x][next.y]) continue;
            table[next.x][next.y] = s;
            visit[next.x][next.y] = true;
            update(visit, next.x, next.y, s);
        }
    }

    static void unmerge(boolean[][] visit, int r, int c) {
        for (int k = 0; k < merge[r][c].size(); k++) {
            Pos next = merge[r][c].get(k);
            if (visit[next.x][next.y]) continue;
            table[next.x][next.y] = "";
            visit[next.x][next.y] = true;
            unmerge(visit, next.x, next.y);
            merge[next.x][next.y] = new ArrayList<>();
        }
    }

    static class Pos {
        int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}