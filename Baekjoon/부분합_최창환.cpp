#include <iostream>
using namespace std;

int n, target, ans = 21e8, arr[100002];

void init()
{
  cin >> n >> target;
  for(int i = 0; i < n; ++i)
    cin >> arr[i];
}

void run()
{
  int left = 0, right = 1, sum = arr[0];
  while(right <= n) {
    if(sum >= target) {
      ans = min(ans, right - left);
      sum -= arr[left];
      ++left;
    } else {
      sum += arr[right];
      ++right;
    }
  }
}

int main()
{
  init();
  run();
  if(ans != 21e8)
    cout << ans;
  else
    cout << 0;
}
