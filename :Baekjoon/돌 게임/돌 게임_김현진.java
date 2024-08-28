import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 돌 게임_김현진 {
	// 9655 돌 게임
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(sc.readLine());

		if (N % 2 == 0)
			System.out.println("CY");
		else
			System.out.println("SK");
	}

}
