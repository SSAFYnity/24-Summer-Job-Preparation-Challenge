#include <string>
#include <vector>

using namespace std;

int discount[] = { 10,20,30,40 };
vector<pair<int, int>>discount_price(7, { 0,0 }); // ������, ���� ����
vector<int> answer(2, 0);

void dfs(int depth, vector<vector<int>>users, vector<int>emoticons) {

    int emoticon_cnt = emoticons.size();

    if (depth < emoticon_cnt) {
        // ���ȣ��� ������ ���� ���ϱ�
        for (int i = 0; i < 4; i++) {
            discount_price[depth].first = discount[i];
            discount_price[depth].second = emoticons[depth] - (emoticons[depth] * discount[i] / 100);

            dfs(depth + 1, users, emoticons);
        }
    }
    else {
        // �� ������ ���� ���
        int subscribe = 0;
        int total_price = 0;

        for (int i = 0; i < users.size(); i++) {
            int standard = users[i][0];
            int max_price = users[i][1];
            int sum_price = 0;

            // ���� ���غ��� ���� �������̸� ����
            for (int j = 0; j < emoticon_cnt; j++) {
                if (discount_price[j].first >= standard) {
                    sum_price += discount_price[j].second;
                }
            }
            // ������ ������ �ʰ��ϸ� �̸�Ƽ�� �÷��� ����
            if (sum_price >= max_price) {
                sum_price = 0;
                subscribe++;
            }
            // �׷��� ������ ��ü �տ� ���ϱ�
            else {
                total_price += sum_price;
            }
        }

        // �������� �����ڰ� ������ ����(1�� ����)
        if (subscribe > answer[0]) {
            answer[0] = subscribe;
            answer[1] = total_price;
        }
        else if (subscribe == answer[0] && total_price > answer[1]) {
            answer[1] = total_price;
        }
    }
}

vector<int> solution(vector<vector<int>> users, vector<int> emoticons) {

    dfs(0, users, emoticons);

    return answer;
}