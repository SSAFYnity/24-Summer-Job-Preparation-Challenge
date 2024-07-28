import java.util.*;

class Solution {
    public static int answer = 750; // minerals 길이 * (최대 피로도 25)
    public static int[][] fatigueTable = { {1, 1, 1}, {5, 1, 1}, {25, 5, 1} };
    // HashMap -> 파이썬의 Dictionary 느낌인 듯
    public static HashMap<String, Integer> idxMinerals = new HashMap<String, Integer>();
    public int solution(int[] picks, String[] minerals) {
        // 이렇게 쓰는 거 맞나..?
        idxMinerals.put("diamond", 0);
        idxMinerals.put("iron", 1);
        idxMinerals.put("stone", 2);
        
        dfs(picks, minerals, 0, 0);
        return answer;
    }
    
    public void dfs(int[] picks, String[] minerals, int idx, int fatigue) {
        if (answer <= fatigue) return;
        if (Arrays.stream(picks).sum() == 0 || idx >= minerals.length) {
            answer = Math.min(fatigue, answer);
            return;
        }
        
        for (int pick = 0; pick < picks.length; pick++) {
            if (picks[pick] != 0) {
                picks[pick] -= 1;
                
                int sumFatigue = 0;
                int sumIdx = 0;
                // 변수를 새로 생성하지 않고 원하는 횟수만큼 for문을 돌릴 수 있는 방법?
                for (int i = 0; i < 5; i++) {
                    if (idx + i < minerals.length) {
                        // 더 쉬운 방법 있을 것 같은데..
                        sumFatigue += fatigueTable[pick][idxMinerals.get(minerals[idx + i])];
                        sumIdx += 1;
                    } else break;
                }
                
                dfs(picks, minerals, idx + sumIdx, fatigue + sumFatigue);
                picks[pick] += 1;
            }
        }
    }
}