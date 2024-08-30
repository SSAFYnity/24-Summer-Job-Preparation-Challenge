import sys

input = sys.stdin.readline

dp = {0: 1, 1: 1, 2: 2, 3: 4}

for _ in range(4, 1000001):
    dp[_] = (dp[_ - 3] + dp[_ - 2] + dp[_ - 1]) % 1000000009

for _ in range(int(input())):
    print(dp[int(input())])
