#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int getScore(string mineral) {
    if (mineral == "diamond") return 25;
    else if (mineral == "iron") return 5;
    else return 1;
}

int solution(vector<int> picks, vector<string> minerals) {
    int answer = 0;
    int pickCnt = 0;
    int mineralSize = minerals.size();

    vector<pair<int, int>>group;

    for (int pick : picks) pickCnt += pick;

    int maxMineralSize = pickCnt * 5;

    if (maxMineralSize < mineralSize) {
        minerals.erase(minerals.begin() + maxMineralSize, minerals.end());
        mineralSize = minerals.size();
    }

    // ±¤¼® ¹è¿­ 5°³¾¿ Àß¶ó¼­ ¾î¶² ±¤¼®ÀÌ ¸¹ÀºÁö ÆÄ¾Ç
    for (int i = 0; i < mineralSize; i += 5) {
        int score = 0;
        for (int j = i; j < min(mineralSize, i + 5); j++) {
            score += getScore(minerals[j]);
        }
        group.push_back({ score, i });
    }

    sort(group.rbegin(), group.rend());

    // °î±ªÀÌ ¹èÁ¤
    for (int i = 0; i < group.size(); i++) {
        int startIdx = group[i].second;
        if (picks[0] > 0) {
            for (int j = startIdx; j < min(mineralSize, startIdx + 5); j++) {
                answer += 1;
            }
            picks[0]--;
        }
        else if (picks[1] > 0) {
            for (int j = startIdx; j < min(mineralSize, startIdx + 5); j++) {
                if (minerals[j] == "diamond") answer += 5;
                else answer += 1;
            }
            picks[1]--;
        }
        else if (picks[2] > 0) {
            for (int j = startIdx; j < min(mineralSize, startIdx + 5); j++) {
                if (minerals[j] == "diamond") answer += 25;
                else if (minerals[j] == "iron") answer += 5;
                else answer += 1;
            }
            picks[2]--;
        }
        else break;
    }

    return answer;
}