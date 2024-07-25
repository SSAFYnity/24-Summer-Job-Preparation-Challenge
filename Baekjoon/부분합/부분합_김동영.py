N, M = map(int, input().split())
lst = list(map(int, input().split()))

left, right = 0, 0
cnt = 0
answer = float("inf")

while True:
    if cnt >= M:
        answer = min(answer, right - left)
        cnt -= lst[left]
        left += 1
    elif right == N:
        break
    else:
        cnt += lst[right]
        right += 1

if answer == float("inf"):
    print(0)
else:
    print(answer)
