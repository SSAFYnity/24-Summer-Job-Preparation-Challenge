import java.io.*;
import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        HashSet<String> set = new HashSet<>();
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        split_str(str1, map1, set); split_str(str2, map2, set);



        // 2. 교집합, 합집합 구하기
        double union = 0;
        double inter = 0;
        for(String temp : set){
            int cnt1 = map1.get(temp) == null ? 0 : map1.get(temp);
            int cnt2 = map2.get(temp) == null ? 0 : map2.get(temp);

            union += Math.max(cnt1, cnt2);
            if(cnt1 == 0 || cnt2 == 0) continue;
            else{
                inter += Math.min(cnt1,cnt2);
            }

        }


        if(map1.size() == 0 && map2.size() == 0) return 65536;

        return (int)((inter/union)*65536) ;
    }
    // 1. 글자 잘라서 map에 넣기
    public void split_str(String str, HashMap<String,Integer> map, HashSet<String> set) {
        for(int i = 2; i <= str.length(); i+=1){
            boolean isValid = true;
            String now = str.substring(i-2, i);
            // 97, 122, 65, 90
            for(int j = 0; j<2; j++){
                if((now.charAt(j)>= 97 && now.charAt(j)<= 122)||
                    (now.charAt(j)>= 65 && now.charAt(j)<= 90)) continue;
                isValid = false;
            }
            if(isValid) {
                set.add(now.toLowerCase());
                map.compute(now.toLowerCase(),(key,oldValue) -> oldValue== null? 1 : oldValue+1);
            }
        }
    }
}