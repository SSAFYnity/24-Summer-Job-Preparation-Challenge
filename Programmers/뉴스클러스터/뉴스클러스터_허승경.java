import java.util.LinkedList;
import java.util.List;

class Solution {
    List<String> list1 = new LinkedList<>();       // str1의 다중집합
    List<String> list2 = new LinkedList<>();       // str2의 다중집합
    
    public int solution(String str1, String str2) {
        double answer = 0;
        double num = 65536;

        // 1. 소문자로 바꾸기
        String [] lowStr1 = str1.split("");
        String [] lowStr2 = str2.split("");

        // 2. 2개씩 묶기
        list1 = divideStr(lowStr1);
        list2 = divideStr(lowStr2);

        // 3. 교집합, 합집합 구하기
        double intersection = 0;     // 교집합

        for(String str : list1){        // 교집합 구하기
            if(list2.remove(str)) intersection++;
        }

        double union = list1.size() + list2.size();     // 합집합 구하기

        // 4. 유사도 검사하기
        if(union != 0 && intersection != 0){
            answer = (intersection/union) * num;
        }else if(union == 0 && intersection == 0){
            answer = num;
        }

        return (int)answer;
    }
    
    boolean isAlpha(String s)
    {
        if (s == null) {
            return false;
        }

        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z')) {
                return false;
            }
        }
        return true;
    }

    List<String> divideStr(String [] arr){
        List<String> tmp = new LinkedList<>();

        int next = 0;
        String str = "";
        for(int i = 0; i < arr.length-1; i++){
            str = "";
            next = i+1;
            str = arr[i]+arr[next];
            if(str.length() < 2) continue;      // 공백처리하기
            if(isAlpha(str)){
                tmp.add(str.toLowerCase());
            }
        }

        return tmp;
    }
}