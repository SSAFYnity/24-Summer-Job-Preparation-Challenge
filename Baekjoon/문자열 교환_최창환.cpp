#include <iostream>
using namespace std;

int ans = 21e8, aCnt;
string str;

void init() {
  cin >> str;
  string temp = str;
  for (char ch : temp) {
    if (ch == 'a')
      ++aCnt;
    str += ch;
  }
}

void run() {
  int left = 0, right = 0, bCnt;
  bCnt = str[right] == 'b' ? 1 : 0;
  while (right < str.size()) {
    if (right - left + 1 < aCnt) {
      if (str[++right] == 'b')
        ++bCnt;
    } else {
      ans = min(ans, bCnt);
      if (str[left++] == 'b')
        --bCnt;
    }
  }
}

int main() {
  init();
  run();
  cout << ans;
}
