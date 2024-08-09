class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {

        // 플로이드 워셜 배열 초기화
        int[][] dp = new int[n + 1][n + 1]; // dp[출발][도착] = {최단거리}

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i != j){
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // 간선 정보 입력
        for(int[] arr : fares) {
            dp[arr[0]][arr[1]] = arr[2];
            dp[arr[1]][arr[0]] = arr[2];
        }

        // 플로이드 워셜
        for(int i = 1; i <= n; i++) { // 경유지
            for(int j = 1; j <= n; j++) { // 시작위치
                for(int k = 1; k <=n; k++) { // 도착위치
                    if(dp[j][i] < Integer.MAX_VALUE && dp[i][k] < Integer.MAX_VALUE)
                        dp[j][k] = Math.min(dp[j][k], dp[j][i] + dp[i][k]);
                }
            }
        }

        // 최소 비용 찾기
        int min = Integer.MAX_VALUE;
        for(int i = 1; i <=n; i++) { // 합승 종료 위치
            if(dp[s][i] < Integer.MAX_VALUE && dp[i][a] < Integer.MAX_VALUE && dp[i][b] < Integer.MAX_VALUE)
                min = Math.min(min, dp[s][i] + dp[i][a] + dp[i][b]);
            // 시작->합승종료위치 + 합승종료위치->A + 합승종료위치->B 의 최소값 구하기
        }

        return min;
    }
}

/*
< 입력 >
- N 노드 갯수 : 3 ~ 200
- S 시작위치 : 1 ~ 200
- A, B의 목적지 : 1 ~ 200
- fares : 경로와 비용(가중치)

< 목적 >
- A와 B가 각자의 목적지로 가는데 드는 비용의 총합 최솟값
- 어디까지 합승해서 비용을 아낄 것인지가 중요

< 풀이 계획 >
- 최단거리 : BFS, Dijkstra, 벨만포드, 플로이드-워셜
1) 플로이드 워셜
    - 최단 거리를 위한 중간점을 찾는 플로이드-워셜이 맞을듯
    - DP 배열도 다르게 만들어야 할듯
        > 같이 온 경우와 A와 B 따로 간 경우 나누기
        > 아니다 이상해지는 것 같아서 취소
    - DP 배열 모두 구한 뒤, 합승 위치를 하나씩 넣어보고 최단거리 찾기
        ex) 1번이 합승 종료 위치라면, 2번이? 3번이....등
2) BFS
    - 효율성 테스트가 있으므로 터질듯?
*/