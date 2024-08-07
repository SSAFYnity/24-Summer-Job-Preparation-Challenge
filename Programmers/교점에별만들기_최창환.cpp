#include <string>
#include <vector>
#include <climits>
using namespace std;

vector<string> solution(vector<vector<int>> line) {
  vector<string> answer;
  vector<pair<long long, long long>> v;
  string str;
  bool what;
  long long maxX = LLONG_MIN, maxY = LLONG_MIN, minX = LLONG_MAX, minY = LLONG_MAX;

  for (long long i = 0; i < line.size() - 1; ++i) {
    double x, y;
    long long tempX, tempY;
    for (long long j = i + 1; j < line.size(); ++j) {
      if ((double)line[i][0] * (double)line[j][1] - (double)line[i][1] * (double)line[j][0] != 0) {
        x = ((double)line[i][1] * (double)line[j][2] - (double)line[i][2] * (double)line[j][1]) /
            ((double)line[i][0] * (double)line[j][1] - (double)line[i][1] * (double)line[j][0]);
        y = ((double)line[i][2] * (double)line[j][0] - (double)line[i][0] * (double)line[j][2]) /
            ((double)line[i][0] * (double)line[j][1] - (double)line[i][1] * (double)line[j][0]);
      }
      tempX = (long long)x;
      tempY = (long long)y;
      if (x - tempX == 0 && y - tempY == 0) {
        v.emplace_back(tempX, tempY);
        maxX = max(maxX, tempX);
        maxY = max(maxY, tempY);
        minX = min(minX, tempX);
        minY = min(minY, tempY);
      }
    }
  }

  for (long long i = maxY; i >= minY; --i) {
    str = "";
    for (long long j = minX; j <= maxX; ++j) {
      what = false;
      for (pair<long long, long long> &now : v)
        if (now.first == j && now.second == i) {
          what = true;
          break;
        }
      if (what)
        str += '*';
      else
        str += '.';
    }
    answer.emplace_back(str);
  }

  return answer;
}
