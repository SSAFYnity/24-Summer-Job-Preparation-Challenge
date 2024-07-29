import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] data = new int[N];
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(data);
		for(int i=1;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp>data[0]) {
					data[0] = temp;
					for(int t=1;t<N;t++) {
						if(data[t]>temp) {
							break;
						}
						data[t-1] = data[t];
						data[t] = temp;
					}
				}
			}
		}
		System.out.println(data[0]);
	}
}