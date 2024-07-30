#include <iostream>
using namespace std;

int n, arr[10] = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
string minV, maxV;

void init() {
  cin >> n;
  minV = maxV = "";
}

void minValue() {
  int tempN = n;
  for (int i = 1; i < 10; ++i)
    if (tempN == arr[i]) {
      minV = to_string(i);
      tempN = 0;
      break;
    }
  if (tempN) {
    int first = tempN % 7;
    if (first == 0) {
      minV = '8';
      tempN -= 7;
    } else if (first <= 2) {
      minV = '1';
      tempN -= 2;
    } else if (first <= 5) {
      minV = '2';
      tempN -= 5;
    } else {
      minV = '6';
      tempN -= 6;
    }
    if (tempN == 5) {
      minV += '2';
      tempN = 0;
    }
    while (tempN) {
      if (tempN % 7 == 0) {
        minV += '8';
        tempN -= 7;
      } else {
        minV += '0';
        tempN -= 6;
      }
    }
  }
}

void maxValue() {
  int tempN = n;
  if (tempN & 1) {
    maxV = '7';
    tempN -= 3;
  }
  while (tempN) {
    maxV += '1';
    tempN -= 2;
  }
}

int main() {
  int T;
  cin >> T;
  while (T--) {
    init();
    minValue();
    maxValue();
    cout << minV << ' ' << maxV << endl;
  }
}
