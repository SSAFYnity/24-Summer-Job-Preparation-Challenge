import java.util.*;
class Solution {
    static int[][] data;
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;

        int size = friends.length;
        HashMap<String, Integer> friendsIdx = new HashMap<>();
        for(int i = 0; i < size; i++) {
            friendsIdx.put(friends[i], i);
        }

        data = new int[size][size];
        for(int i = 0; i < gifts.length; i++) {
            String[] tmp = gifts[i].split(" ");

            int giveIdx = friendsIdx.get(tmp[0]);
            int takeIdx = friendsIdx.get(tmp[1]);

            data[giveIdx][takeIdx] += 1;
        }

        int[] result = new int[size];
        for(int i = 0; i < size; i++) {
            for(int j = i+1; j < size; j++) {
                if (i == j) continue;

                if (data[i][j] != data[j][i]) {
                    int giftIdx = data[i][j] < data[j][i] ? j : i;
                    result[giftIdx] += 1;

                } else {
                    int giveValue1 = 0;
                    int takeValue1 = 0;
                    int giftValue1 = 0;
                    int giveValue2 = 0;
                    int takeValue2 = 0;
                    int giftValue2 = 0;
                    for(int m = 0; m < size; m++) {
                        giveValue1 += data[i][m];
                        takeValue1 += data[m][i];
                        giveValue2 += data[j][m];
                        takeValue2 += data[m][j];
                    }

                    giftValue1 = giveValue1 - takeValue1;
                    giftValue2 = giveValue2 - takeValue2;

                    if (giftValue1 != giftValue2) {
                        int giftIdx = giftValue1 < giftValue2 ? j : i;
                        result[giftIdx] += 1;

                    }
                }
            }
        }

        for(int i = 0; i < size; i++) {
            answer = Math.max(result[i], answer);
        }

        return answer;
    }
}