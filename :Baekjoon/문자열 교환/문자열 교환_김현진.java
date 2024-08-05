import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();

        int aSize = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == 'a'){
                aSize++;
            }
        }

        int start = 0, bCnt = 0, end = aSize - 1;
        for(int i = 0; i < aSize; i++){
            if(str.charAt(i) == 'b') bCnt++;
        }

        int min = bCnt;
        while(start < str.length()){
            if(str.charAt(++end % str.length()) == 'b') bCnt++;
            if(str.charAt(start++) == 'b') bCnt--;

            min = Math.min(min, bCnt);
        }

        System.out.println(min);
    }
}