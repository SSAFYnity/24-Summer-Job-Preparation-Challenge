// 연결하는 정점
// - out만 가짐. 단, 여러 그래프를 가지므로 보통 out은 2이상

// 도넛 그래프
// - 사이클 있음
// - 각 노드별 in, out 1개씩
// - 막대, 8자를 제외한 나머지

// 막대 그래프
// - 사이클 없음
// - in, out 중 1개만 가진 경우가 있음.
// - in, out이 아예 없는 경우도 막대로 본다. (1개짜리 막대그래프)

// 8자 그래프
// - 2n+1의 노드 갯수, 2n+2의 간선
// - 사이클 있음
// - in, out이 2개인 경우가 있음.

import java.util.*;
class Solution {
    private static List<Integer> list[];
    private static boolean[] visited;
    public int[] solution(int[][] edges) {                
        int len = (int)1e6+4;
        
        int[] in = new int[len];
        int[] out = new int[len];
        
        int max = 0;
        
        // 각 생성된 노드 번호가 순차적임을 보장하지 않았다.
        // 즉, 식별은 가능하나 랜덤한 값이 제공될 수도 있다.
        // 따라서 set을 이용한다.
        Set<Integer> s = new HashSet<>(); 
        
        // in, out 계산
        for(int[] edge : edges) {
            out[edge[0]]++;
            in[edge[1]]++;
            max = Math.max(max, Math.max(edge[0], edge[1]));
            s.add(edge[0]);
            s.add(edge[1]);
        }
    
        int root = 0; // 정점
        for(int num : s) {
            if(in[num] == 0 && out[num] >=2) {
                root = num;
                break;
            }
        }
          
        int total = 0; // 총 그래프 갯수
        for(int[] edge : edges) {
            int n1 = edge[0];
            int n2 = edge[1];

            if(n1 != root) continue;
            
            out[n1]--;
            in[n2]--;
            total++;
        }
        
        int g2 = 0; // 막대
        int g3 = 0; // 8자
        
        for(int i=1; i<=max; i++) {
            if(!s.contains(i) || i==root) continue;
            
            if(in[i]==2 && out[i]==2) {
                g3++;
            } else if(out[i]==0)  {
                if(in[i]==0 || in[i]==1) {
                    g2++;
                }
            }
        }
        
        return new int[] {root, total-g2-g3, g2, g3};
    }
}