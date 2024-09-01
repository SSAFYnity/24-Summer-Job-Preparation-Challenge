import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        while (n > 0) {
            if (n > 4) {
                n -= 3;
            } else if (n == 4) {
                System.out.println("CY");
                break;
            } else if (n == 3) {
                System.out.println("SK");
                break;
            } else if (n == 2) {
                System.out.println("CY");
                break;
            } else {
                System.out.println("SK");
                break;
            }

            if (n > 4) {
                n -= 3;
            } else if (n == 4) {
                System.out.println("SK");
                break;
            } else if (n == 3) {
                System.out.println("CY");
                break;
            } else if (n == 2) {
                System.out.println("SK");
                break;
            } else {
                System.out.println("CY");
                break;
            }
        }
    }
}

