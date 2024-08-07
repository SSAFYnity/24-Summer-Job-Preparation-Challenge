#include <iostream>
#include <algorithm>
#include <vector>
#define MAX 123456789987654321
using namespace std;

string make_max(int n) {

	string str = "";

	if (n % 2 == 0) {
		for (int i = 0; i < n / 2; i++) {
			str += "1";
		}
	}
	else {
		str += "7";
		for (int i = 0; i < (n-3)/2; i++) {
			str += "1";
		}
	}

	return str;
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int t = 0, n=0;
	int max_value = 0, min_value = 0;

	long long dp[101];
	int num[8] = { 0,0,1,7,4,2,0,8 };

	fill(dp, dp + 101, MAX);

	dp[2] = 1, dp[3] = 7, dp[4] = 4, dp[5] = 2, dp[6] = 6, dp[7] = 8;

	for (int i = 8; i < 101; i++) {
		for (int j = 2; j < 8; j++) {
			dp[i] = min(dp[i], dp[i - j]*10 + num[j]);
		}
	}

	cin >> t;

	while (t--) {
		cin >> n;
		
		// ÃÖ¼Ò°ª
		cout << dp[n] << " " << make_max(n) << "\n";
	}

	return 0;
}