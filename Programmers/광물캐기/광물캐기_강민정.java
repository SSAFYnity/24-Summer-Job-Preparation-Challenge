import java.util.*;

class Solution {

    static class Mineral {
        private int diamond;
        private int iron;
        private int stone;
        
        public Mineral(int diamond, int iron, int stone) {
            this.diamond = diamond;
            this.iron = iron;
            this.stone = stone;
        }
    }
    
    static final int[][] scoreBoard;      // 곡괭이별 광물을 캘 때의 피로도
    static List<Mineral> list;      // 미네랄 5개를 캘때, 곡괭이별 피로도의 합
    
    /*
        picks: [dia, iron, stone] 0 <= 각 곡괭이별 갯수 <=5
        minerals: 3개의 문자열("diamond" / "iron" / "stone")로 이루어진 배열
    */
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        scoreBoard = new int[][]{{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};     // 곡괭이별 광물을 캘 때의 피로도
        
        int totalPicks = Arrays.stream(picks).sum();        // 총 곡괭이의 수
        list = new ArrayList<>();
        for(int i = 0; i < minerals.length; i+=5) {     // 미네랄을 5개 연속으로 캐기
            if(totalPicks == 0) break;      // 곡괭이를 모두 다 사용
            
            int dia = 0, iron = 0, stone = 0;
            for(int j = i; j < i + 5; j++) {
                if(j == minerals.length) break;     // 광물을 다 캤다면
                
                String mineral = minerals[j];
                int val = mineral.equals("iron") ? 1 : 
                    mineral.equals("stone") ? 2 : 0;
                
                // 광물별 피로도 계산
                dia += scoreBoard[0][val];
                iron += scoreBoard[1][val];
                stone += scoreBoard[2][val];
            }
            
            list.add(new Mineral(dia, iron, stone));        // 미네랄을 캘때 사용한 곡괭이별 피로도의 합을 추가
            totalPicks--;       // 곡괭이 사용 횟수 차감
        }
        
        Collections.sort(list, ((o1, o2) -> (o2.stone - o1.stone)));    // 돌 곡괭이를 사용했을 때 피로도가 높은 순으로 정렬
        for(Mineral m : list) {     // 피로도가 높은 광물을 다이아몬드 -> 철 -> 돌순으로 사용해서 캐야 이득이다.
            int dia = m.diamond;
            int iron = m.iron;
            int stone = m.stone;
            
            if(picks[0] > 0) {      // 다이아몬드 곡괭이가 있으면
                answer += dia;
                picks[0]--;
                continue;
            }
            if(picks[1] > 0) {     // 철 곡괭이가 있으면
                answer += iron;
                picks[1]--;
                continue;
            }
            if(picks[2] > 0) {      // 돌 곡괭이가 있으면
                answer += stone;
                picks[2]--;
                continue;
            }
        }
        
        return answer;      // 최소한의 피로도
    }
}
