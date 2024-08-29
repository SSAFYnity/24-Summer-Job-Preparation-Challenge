import java.io.*;
import java.util.*;

class Solution {

    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        Map<String, Integer> friend = new HashMap<>();
        int[] point = new int[friends.length];
        int[] nextMonth = new int[friends.length];
        int[][] map = new int[friends.length][friends.length];

        for(int i = 0; i < friends.length; i++){
            friend.put(friends[i], i);
        }

        for(int i = 0; i < gifts.length; i++){
            String[] str = gifts[i].split(" ");
            int A = friend.get(str[0]);
            int B = friend.get(str[1]);

            point[A]++;
            point[B]--;
            map[A][B]++;
        }

        for(int i = 0; i < map.length; i++){
            for(int j = i + 1; j < map[0].length; j++){
                if(map[i][j] > map[j][i]){
                    nextMonth[i]++;
                }else if(map[i][j] < map[j][i]){
                    nextMonth[j]++;
                }else{
                    if(point[i] > point[j]){
                        nextMonth[i]++;
                    }else if(point[i] < point[j]){
                        nextMonth[j]++;
                    }
                }
            }
        }

        for(int i = 0; i < nextMonth.length; i++){
            answer = Math.max(nextMonth[i], answer);
        }
        return answer;
    }
}