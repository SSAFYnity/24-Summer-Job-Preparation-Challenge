import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[] time = new int[N];
		int[] price = new int[N];
		int sum = 0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			price[i] = Integer.parseInt(st.nextToken());
			if(i+time[i]<=N) {
				sum += price[i];
			}
		}
		System.out.println(work(time, price, 0, 0, sum, 0));
	}
	public static int work(int[] time, int[] price, int currentIndex, int currentPrice, int remain, int max) {
		int size = time.length;
		if(currentPrice+remain<=max) {
			return max;
		}
		for(int i=currentIndex;i<size;i++) {
			if(i+time[i]>size) {
				continue;
			}
			int remainMinus = 0;
			for(int j=currentIndex;j<currentIndex+time[i];j++) {
				if(j+time[j]<=size) {
					remainMinus += price[j];
				}
			}
			int temp = work(time, price, i+time[i], currentPrice+price[i], remain-remainMinus, max);
			max = Math.max(max, temp);
		}
		return Math.max(max, currentPrice);
	}
}