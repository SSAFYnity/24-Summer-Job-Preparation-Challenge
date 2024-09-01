import java.util.*;

class 가장
많이 받은 선물_김현진
{

	public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int len = friends.length;
        HashMap<String, Integer> map = new HashMap<>();
        int[] degree = new int[len];
        int[][] graph = new int[len][len];
        
        for (int i = 0; i < len; i++){
            map.put(friends[i], i);
        }
        
        
        for(String gift : gifts) {
            String[] split = gift.split(" ");
            degree[ map.get(split[0]) ]++;
            degree[ map.get(split[1]) ]--;
            graph[ map.get(split[0]) ][ map.get(split[1]) ]++;
        }
                                                
        for (int i = 0; i < len; i ++) {
            int num = 0;
            
            for (int j = 0; j < len; j++) {
                if ( i == j ) {
                    continue;
                }
                
                if ( graph[i][j] > graph[j][i] ||
                    ( graph[i][j] == graph[j][i] && degree[i] > degree[j] )
                    
                }
            }
            if (answer < num){
                answer = num;
            }
        }return answer;
}}