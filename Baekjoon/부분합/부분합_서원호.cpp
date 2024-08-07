#include <iostream>
using namespace std;

int n, s;
int numbers[100001];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> s;

    for (int i = 0; i < n; i++) {
        cin >> numbers[i];
    }

    int left = 0;
    int right = 0;
    int sum = numbers[0];
    int len = 100001;
    while (left <= right && right < n) {
        if (sum >= s) {
            len = min(len, right - left + 1);
            sum -= numbers[left];
            left++;
            continue;
        }

        right++;
        sum += numbers[right];
    }

    cout << (len == 100001 ? 0 : len);


    return 0;
}