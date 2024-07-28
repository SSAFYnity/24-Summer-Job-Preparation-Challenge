import heapq

n = int(input())
heap = []

# 첫 번째 행을 heap에 넣음
for num in map(int, input().split()):
    heapq.heappush(heap, num)

# 두 번째 행부터 N번째 행까지 반복하면서
# 각 행의 가장 작은 값과 heap의 가장 작은 값을 비교하여 더 큰 값을 heap에 추가
for _ in range(n - 1):
    row = list(map(int, input().split()))
    for num in row:
        if num > heap[0]:
            heapq.heappop(heap)
            heapq.heappush(heap, num)

print(heap[0])
