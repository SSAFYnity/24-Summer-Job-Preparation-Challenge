import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main{
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder("");
        for(int t=0;t<testCase;t++){
            int n = Integer.parseInt(br.readLine());
            int count = n/7-1;
            if(n>=7){
                switch(n%7){
                    case 0:
                        count++;
                        break;
                    case 1:
                        sb.append("10");
                        break;
                    case 2:
                        sb.append("18");
                        break;
                    case 3:
                        if(n>14){
                            sb.append("200");
                            count--;
                        }
                        else{
                            sb.append("22");
                        }
                        break;
                    case 4:
                        sb.append("20");
                        break;
                    case 5:
                        sb.append("28");
                        break;
                    case 6:
                        sb.append("68");
                        break;
                }
            }
            else{
                switch(n){
                    case 2:
                        sb.append("1");
                        break;
                    case 3:
                        sb.append("7");
                        break;
                    case 4:
                        sb.append("4");
                        break;
                    case 5:
                        sb.append("2");
                        break;
                    case 6:
                        sb.append("6");
                        break;
                }
            }
            for(int i=0;i<count;i++){
                sb.append("8");
            }
            sb.append(" ");
            count = n/2;
            switch(n%2){
                case 0:
                    break;
                case 1:
                    sb.append("7");
                    count--;
                    break;
            }
            for(int i=0;i<count;i++){
                sb.append("1");
            }
            sb.append("\\n");
        }
        System.out.println(sb);
    }
}