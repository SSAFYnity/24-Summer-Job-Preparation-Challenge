import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int str1Count=0;
        int str2Count=0;
        
        int intersectionCount=0;
        int unionCount=0;

        List<String> keyArrayMaster=new ArrayList<>();
        List<String> keyArray1=new ArrayList<>();
        List<String> keyArray2=new ArrayList<>();

        String keyTemp="";
        
        for(int i=0;i<str1.length();i++){
            if(!Character.isAlphabetic(str1.charAt(i))){
                if(keyTemp.length()==1){
                    keyTemp="";
                }
                continue;
            }else if(Character.isUpperCase(str1.charAt(i))){
                keyTemp+=Character.toLowerCase(str1.charAt(i));
            }else{
                keyTemp+=Character.toString(str1.charAt(i));
            }
            
            if(keyTemp.length()==2){
                keyArray1.add(keyTemp);

                if(!keyArrayMaster.contains(keyTemp)){
                    keyArrayMaster.add(keyTemp);
                }

                keyTemp=Character.toString(Character.toLowerCase(str1.charAt(i)));
            }
        }
        
        keyTemp="";
        
        for(int j=0;j<str2.length();j++){
            if(!Character.isAlphabetic(str2.charAt(j))){
                if(keyTemp.length()==1){
                    keyTemp="";
                }
                continue;
            }else if(Character.isUpperCase(str2.charAt(j))){
                keyTemp+=Character.toLowerCase(str2.charAt(j));
            }else{
                keyTemp+=Character.toString(str2.charAt(j));
            }
            
            if(keyTemp.length()==2){
                keyArray2.add(keyTemp);

                if(!keyArrayMaster.contains(keyTemp)){
                    keyArrayMaster.add(keyTemp);
                }

                keyTemp=Character.toString(Character.toLowerCase(str2.charAt(j)));
            }
        }
        
        if(keyArrayMaster.size()==0){
            return 65536;
        }
        
        for(String key:keyArrayMaster){
            str1Count=Collections.frequency(keyArray1, key);
            str2Count=Collections.frequency(keyArray2, key);

            intersectionCount+=Math.min(str1Count, str2Count);
            unionCount+=Math.max(str1Count, str2Count);
        }

        int answer=(int)(((double)intersectionCount/unionCount)*65536);
        
        return answer;
    }
}