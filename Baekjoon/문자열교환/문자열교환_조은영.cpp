#include <iostream>
#define INF 1e9
using namespace std;

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(0);

	string str;
	int cnt = 0;
	

	cin >> str;

	int len = str.length();
	
	for (int i = 0; i < len; i++) {
		if (str[i] == 'a') {
			cnt++;
		}
	}

	int start = 0; int end = cnt;
	int b_cnt = 0, min_value=INF;

	while (start < len) {
		for (int i = start; i < end; i++) {
			if (str[i%len] == 'b') b_cnt++;
		}
		if (min_value > b_cnt) { 
			min_value = b_cnt; 
		}
		b_cnt = 0;
		++start;
		++end;
	}

	cout << min_value << "\n";

	return 0;
}