import java.io.*;
import java.util.*;

class Solution {

    static int[][] damage = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};

    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        ArrayList<int[]> list = new ArrayList<>();
        int sum = 0;
        for (int p : picks) {
            sum += p;
        }

        for (int i = 0; i < minerals.length; i += 5) {
            int dia = 0;
            int iro = 0;
            int sto = 0;
            if (sum == 0) break;
            for (int j = i; j < i + 5 && j < minerals.length; j++) {
                if (minerals[j].equals("diamond")) {
                    dia += damage[0][0];
                    iro += damage[1][0];
                    sto += damage[2][0];
                } else if (minerals[j].equals("iron")) {
                    dia += damage[0][1];
                    iro += damage[1][1];
                    sto += damage[2][1];
                } else {
                    dia += damage[0][2];
                    iro += damage[1][2];
                    sto += damage[2][2];
                }
            }
            list.add(new int[]{dia, iro, sto});
            sum--;
        }

        list.sort((o1, o2) -> Integer.compare(o2[2], o1[2]));

        for (int i = 0; i < list.size(); i++) {
            int[] v = list.get(i);
            if (picks[0] > 0) {
                answer += v[0];
                picks[0]--;
                continue;
            }

            if (picks[1] > 0) {
                answer += v[1];
                picks[1]--;
                continue;
            }

            if (picks[2] > 0) {
                answer += v[2];
                picks[2]--;
                continue;
            }
        }
        return answer;
    }
}