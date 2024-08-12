#include <iostream>
#include <math.h>
using namespace std;

int arr[26] = {};

void counting(string s) {
	for (int i = 0; i < s.length(); i++) {
		arr[s[i] - 'a']++;
	}
}

int main() {

	int N = 0;
	bool flag = true;
	string str;

	cin >> N;
	cin >> str;

	counting(str);

	// N�� Ȧ���� �� (���⼭ �Ǽ�����. ó���� arr[middle]-- �ع���
	if (N % 2 ==1) {
		int middle = floor(N / 2);
		arr[str[middle]-'a']--;
	}

	for (int i = 0; i < 26; i++) {
		if (arr[i] % 2 == 1) {
			flag = false;
			break;
		}
	}

	if (flag) cout << "Yes";
	else cout << "No";

	return 0;
}