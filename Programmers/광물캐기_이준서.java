class Solution {
    
    static int[][] fatigue = {{1,1,1}, {5,1,1},{25,5,1}};
    static int end;
    static int[] combPicks;
    static int minFatigue = 1000000;
    static int[] sPicks;
    static String[] sMinerals;
    public int solution(int[] picks, String[] minerals) {
        
        sPicks = picks;
        sMinerals = minerals;
        int picksCnt = 0;
        for(int a : picks) picksCnt += a;
        int mineralsLength = minerals.length;
        double mineralsLengthToDouble = mineralsLength/5.0;
        int necessaryPicks = mineralsLength/5;
        if(mineralsLengthToDouble > necessaryPicks) necessaryPicks++; 
        
        end = Math.min(necessaryPicks, picksCnt);
        combPicks = new int[end];
        dfs(0);
    
        int answer = minFatigue;
        return answer;
    }
    
    public void dfs(int depth){
        if(depth == end){
            calculation(combPicks);
        }
        else{
            for(int i = 0; i<3; i++){
                if(sPicks[i] > 0){
                    combPicks[depth] = i;
                    sPicks[i]--;
                    dfs(depth+1);
                    sPicks[i]++;
                }
            }
        }
    }
    public void calculation(int[] combPicks){
        int idx = 0;
        int cntFatigue = 0;
        for(int pick : combPicks){
            for(int i = 0; i<5; i++){
                
                if (idx >= sMinerals.length) break;    
                
                String str = sMinerals[idx];
                int val = -1;
                if(str.equals("diamond")) val = 0;
                if(str.equals("iron")) val = 1;
                if(str.equals("stone")) val = 2;

                cntFatigue += fatigue[pick][val];
                idx++;
            }
        }
        minFatigue = Math.min(minFatigue,cntFatigue);
    }
}
