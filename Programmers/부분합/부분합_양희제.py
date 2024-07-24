# 부분합 - 양희제

import sys
input = sys.stdin.readline

N, S = map(int, input().split())
numbers = list(map(int, input().split()))
left, right = 0, 0
prefix_sum = numbers[0]
answer = 100001

while left <= right:
    if prefix_sum >= S:
        answer = min(answer, right - left + 1)
        prefix_sum -= numbers[left]
        left += 1
    else:
        right += 1
        if right >= len(numbers):
            break
        prefix_sum += numbers[right]

print(0 if answer == 100001 else answer)