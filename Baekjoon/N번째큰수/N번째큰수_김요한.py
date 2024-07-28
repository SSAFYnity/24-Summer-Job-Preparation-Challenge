import sys
import heapq

N = int(sys.stdin.readline().strip())

arr = []

for _ in range(N):
    nums = list(map(int, sys.stdin.readline().strip().split()))
    for num in nums:
        if len(arr) < N:
            heapq.heappush(arr, num)
        else:
            heapq.heappushpop(arr, num)

print(heapq.heappop(arr))
