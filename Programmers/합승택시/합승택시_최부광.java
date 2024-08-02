import java.util.*;

// [프로그래머스] 합승 택시 요금
class Solution {
    
    final int MAX_DISTANCE = 9999999;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 그래프 초기화
        int[][] graph = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            Arrays.fill(graph[i], MAX_DISTANCE);
            graph[i][i]  = 0;
        }
        
        for(int[] fare : fares){
            graph[fare[0]][fare[1]] = fare[2];
            graph[fare[1]][fare[0]] = fare[2];
        }
        
        // 플로이드 워셜 알고리즘을 이용해 지점 간 최단 비용 계산
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                if(i==k) continue;
                for(int j=1; j<=n; j++){
                    if(i==k || j==k) continue;
                    graph[i][j] = Math.min(graph[i][j], graph[i][k]+graph[k][j]);
                }
            }
        }
        
        // A와 B가 택시를 함께 탈 때의 최저로 예상되는 택시 요금 계산
        int result = graph[s][a] + graph[s][b]; 
        for(int i=1; i<=n; i++){
            int distance = graph[s][i] + graph[i][a] + graph[i][b];
            result = (distance < result) ? distance : result; 
        }
        
        return result;
    }
}
