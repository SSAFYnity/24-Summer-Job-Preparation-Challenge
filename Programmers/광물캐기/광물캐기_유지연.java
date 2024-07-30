import java.util.*;

class Solution {
    static int min = Integer.MAX_VALUE;
    static int priority[];
    static int fatigue,index;
    static ArrayList<Integer> pickList= new ArrayList<Integer>();
    public int solution(int[] picks, String[] minerals) {
        int answer =0;
        
        
        for(int i=0; i<3; i++)
        {
            int n = picks[i];
            for(int j=0; j<n;j++)
            {
                pickList.add(i);
            }   
        }
        
        priority = new int[pickList.size()];

        
        backtracking(0,pickList.size(),new boolean[pickList.size()],minerals.length,minerals);
  
        
        return min;
    }
    
    static void backtracking(int cnt,int n,boolean [] visited,int mn,String[] minerals)
    {
        if(fatigue>=min)
            return;
        
        
        if(cnt==n)
        {

            min= Math.min(min,fatigue);
            return ;
        }
        
        for(int i=0; i<n;i++)
        {
         
            if(!visited[i])
            {
                
               
                int sum =caculateFatigue(pickList.get(i),cnt,minerals);
              
                visited[i]=true;
                fatigue +=sum;
                
                
                backtracking(cnt+1,n,visited,mn,minerals);
                visited[i]=false;
                fatigue-=sum;
            }
        }
        
        
        
    }
    
    
    static int caculateFatigue(int pick,int cnt, String [] mineral)
    {
        
        cnt *=5;
        
        int range =Math.min(cnt+5,mineral.length);
        int sum=0;
        
        for(int i=cnt;i<range;i++)
        {
            if(pick==0)
                sum+=1;

            else if(pick==1)
            {
                if(mineral[i].equals("diamond"))
                    sum+=5;
                else
                    sum+=1;
            }
            else
            {
                if(mineral[i].equals("diamond"))
                    sum+= 25;
                else if(mineral[i].equals("iron"))
                    sum+= 5;
                else 
                    sum+= 1;
            }
        }
        
        
        return sum;
    
    }
    
    
}