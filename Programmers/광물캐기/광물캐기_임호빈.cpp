#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// picks_len은 배열 picks의 길이입니다.
// minerals_len은 배열 minerals의 길이입니다.
// 파라미터로 주어지는 문자열은 const로 주어집니다. 변경하려면 문자열을 복사해서 사용하세요.
int dp[6][6][6] = { 9999, };

void kikiya(int kiki, int p, int q, int r, int now, int picks[], const char* minerals[], size_t minerals_len)
{
    int cnt = 0;
    int temp = 0;
    while (now < minerals_len && cnt < 5)
    {
        if (kiki == 1)
        {
            if (minerals[now][0] == 'd')
                temp += 1;
            else if (minerals[now][0] == 'i')
                temp += 1;
            else
                temp += 1;
        }

        if (kiki == 2)
        {
            if (minerals[now][0] == 'd')
                temp += 5;
            else if (minerals[now][0] == 'i')
                temp += 1;
            else
                temp += 1;
        }

        if (kiki == 3)
        {
            if (minerals[now][0] == 'd')
                temp += 25;
            else if (minerals[now][0] == 'i')
                temp += 5;
            else
                temp += 1;
        }
        ++cnt;
        ++now;
    }

    if ((kiki ==1) && (dp[p][q][r] > dp[p-1][q][r] + temp))
    {
        dp[p][q][r] = dp[p-1][q][r] + temp;
    }
    else if ((kiki == 2) && (dp[p][q][r] > dp[p][q -1][r] + temp))
    {
        dp[p][q][r] = dp[p][q-1][r] + temp;
    }
    else if ((kiki == 3) && (dp[p][q][r] > dp[p][q][r-1] + temp))
    {
        dp[p][q][r] = dp[p][q][r-1] + temp;
    }

    if (p + 1 <= picks[0])
        kikiya(1, p + 1, q, r, now, picks, minerals, minerals_len);
    if (q + 1 <= picks[1])
        kikiya(2, p, q + 1, r, now, picks, minerals, minerals_len);
    if (r + 1 <= picks[2])
        kikiya(3, p, q, r + 1, now, picks, minerals, minerals_len);

    return;
}

int solution(int picks[], size_t picks_len, const char* minerals[], size_t minerals_len) {
    int answer = 0;
    for (int i = 0; i < 6; i++)
        for (int j = 0; j < 6; j++)
            for (int k = 0; k < 6; k++)
                dp[i][j][k] = 999;

    dp[0][0][0] = 0;

    if (picks[0] > 0)
        kikiya(1, 1, 0, 0, 0, picks, minerals, minerals_len);
    if (picks[1] > 0)
        kikiya(2, 0, 1, 0, 0, picks, minerals, minerals_len);
    if (picks[2] > 0)
        kikiya(3, 0, 0, 1, 0, picks, minerals, minerals_len);


    printf("%d\n", dp[1][0][0]);
    printf("%d\n", dp[0][1][0]);
    printf("%d\n", dp[0][0][1]);

    answer = dp[picks[0]][picks[1]][picks[2]];
    return answer;
}
