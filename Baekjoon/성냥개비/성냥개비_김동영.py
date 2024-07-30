def find_max(num):
    max_num = []
    if num % 2 == 0:
        for _ in range(num // 2):
            max_num.append("1")
        return int("".join(max_num))
    else:
        max_num.append("7")
        for _ in range((num - 3) // 2):
            max_num.append("1")
        return int("".join(max_num))

def find_min(num):
    dp = [float('inf')] * 101
    init_list = ['', '', '1', '7', '4', '2', '6', '8']
    for i in range(2, 8):
        dp[i] = int(init_list[i])

    for i in range(8, num + 1):
        for j in range(2, i - 1):
            dp[i] = min(dp[i], int(str(dp[j]) + str(dp[i - j])))
            if j == 6:
                dp[i] = min(dp[i], int(str(dp[i - j]) + '0'))

    return dp[num]

# 테스트 케이스 수 입력
T = int(input())
for _ in range(T):
    N = int(input())
    print(find_min(N), find_max(N))
