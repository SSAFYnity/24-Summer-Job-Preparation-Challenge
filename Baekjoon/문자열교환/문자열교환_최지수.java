import java.util.*;
import java.io.*;

public class Main {

//    static int n, s, answer, inf = Integer.MAX_VALUE;
//    static int[] sequence, partialSum;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        StringTokenizer st = new StringTokenizer(br.readLine());

//        n = Integer.parseInt(st.nextToken());

        String str = br.readLine();

        int answer = Integer.MAX_VALUE;
        int acnt = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a') acnt++;
        }

        for (int i = 0; i < str.length(); i++) {
            int bcnt = 0;
            for (int j = 0; j < acnt; j++) {
                if (str.charAt((i+j)%str.length()) == 'b') bcnt++;
            }
            answer = Math.min(answer, bcnt);
        }

        System.out.println(answer);
    }
}
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        int answer = Integer.MAX_VALUE;
        int acnt = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a') acnt++;
        }

        for (int i = 0; i < str.length(); i++) {
            int bcnt = 0;
            for (int j = 0; j < acnt; j++) {
                if (str.charAt((i+j)%str.length()) == 'b') bcnt++;
            }
            answer = Math.min(answer, bcnt);
        }

        System.out.println(answer);
    }
}
