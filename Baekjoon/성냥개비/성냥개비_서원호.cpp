#include <iostream>
#include <string>
#define MX 9000000000000000000
using namespace std;

int n;
long long matches[10] = {0, 0, 1, 7, 4, 2, 0, 8};
long long dp[101];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    dp[1] = 9;
    dp[2] = 1;
    dp[3] = 7;
    dp[4] = 4;
    dp[5] = 2;
    dp[6] = 6;
    dp[7] = 8;
    for (int i = 8; i <= 100; i++) {
        dp[i] = MX;
        for (int j = 2; j <= 7; j++) {
            dp[i] = min(dp[i], matches[j] + dp[i - j] * 10);
        }
    }

    cin >> n;

    for (int i = 0; i < n; i++) {
        int cnt;
        cin >> cnt;

        string result = "";
        if (cnt % 2 == 0) {
            for (int j = 0; j < cnt / 2; j++) {
                result += '1';
            }
        } else {
            result += '7';
            for (int j = 0; j < (cnt - 3) / 2; j++) {
                result += '1';
            }
        }

        cout << dp[cnt] << ' ' << result << '\n';
    }

    return 0;
}