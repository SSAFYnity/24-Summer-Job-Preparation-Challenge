#include <string>
#include <vector>

using namespace std;

int solution(vector<int> bandage, int health, vector<vector<int>> attacks) {
    int time = 0;  // ���� �ð�
    int answer = health;  // �����
    int continueCnt = 0;  // ���� ��� Ƚ��
    int attackCnt = 0;  // ���� Ƚ��

    int skillTime = bandage[0];
    int healthUp = bandage[1];
    int bonus = bandage[2];

    while (1) {

        time++;

        // ����
            if (attackCnt < attacks.size() && attacks[attackCnt][0] == time) {
                answer -= attacks[attackCnt][1];
                continueCnt = 0;
                attackCnt++;
            }
        // ����X
            else {
                answer += healthUp;

                continueCnt++;

                if (continueCnt == skillTime) {
                    continueCnt = 0;
                    answer += bonus;
                }

            }
        if (answer > health) answer = health;
        if (answer <= 0) return -1;
        if (attackCnt == attacks.size()) break;
    }



    return answer;
}