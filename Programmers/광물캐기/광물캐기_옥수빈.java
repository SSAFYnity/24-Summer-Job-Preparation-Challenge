import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        
        int answer = 0;
        int totalPick = picks[0] + picks[1] + picks[2];
                
        int[][] graph = {{1,1,1},{5,1,1},{25,5,1}};
        int[][] exhaustion = new int[totalPick][3];
        
        for(int i = 0; i < minerals.length; i++){
            int mineral;
            if(minerals[i].equals("diamond"))
                mineral = 0;
            else if(minerals[i].equals("iron"))
                mineral = 1;
            else
                mineral = 2;
            
            for(int j = 0; j < 3; j++){
                exhaustion[i/5][j] += graph[j][mineral];
            }
            
            if(i == totalPick * 5 - 1)
                break;
        }
        
        Arrays.sort(exhaustion, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o2[2] - o1[2];
            }
        });
        
        int pickNo = 0;
        for(int i = 0; i < exhaustion.length; i++){
            if(picks[pickNo] == 0){
                if(pickNo == 2)
                    break;
                pickNo++;
                i--;
            }else{
                answer += exhaustion[i][pickNo];
                picks[pickNo]--;
            }
        }
        
        return answer;
    }
}