#include <iostream>
#include <algorithm>
using namespace std;

int main() {

    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int N = 0;
    long S = 0;
    cin >> N >> S;

    int arr[100000];
    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }

    long sum = 0;
    int minLength = N + 1;

    int start = 0, end = 0;
    while (end < N) {
        while (sum < S && end < N) {
            sum += arr[end];
            end++;
        }

        while (sum >= S) {
            minLength = min(minLength, end - start);
            sum -= arr[start];
            start++;
        }
    }

    if (minLength == N + 1) {
        cout << 0 << "\n";
    }
    else {
        cout << minLength << "\n";
    }

    return 0;
}