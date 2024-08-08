import java.io.*;
import java.util.*;

class Solution {
	static int solution(int n, int k) {
		String str = findN(n, k);

		String num = "0";
		int cnt = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c != '0') {
				num += c;
			} else {
				if(isPrime(Long.parseLong(num))) {
					cnt++;
				}
				num = "0";
			}
		}

		if(isPrime(Long.parseLong(num))) {
			cnt++;
		}
		return cnt;
	}

	static String findN(int n, int k) {
		String s = "";

		while (k <= n) {
			s = (n % k) + s;
			n /= k;
		}
		return n + s;
	}

	static boolean isPrime(long num) {
		if (num <=1) {
			return false;
		}
		if (num == 2) {
			return true;
		}
		if (num % 2 == 0) {
			return false;
		}
		for (int i = 3; i <= Math.sqrt(num); i += 2) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}
