import sys
from collections import defaultdict

input = sys.stdin.readline

N, K = map(int, input().strip().split())
arr = list(map(int, input().strip().split()))

ans = 0
cnt = defaultdict(int)

l = 0
for r in range(N):
    cnt[arr[r]] += 1

    while cnt[arr[r]] > K:
        cnt[arr[l]] -= 1
        l += 1

    ans = max(ans, r - l + 1)

print(ans)
