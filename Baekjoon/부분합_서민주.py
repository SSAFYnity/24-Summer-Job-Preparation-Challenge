n, s = map(int, input().split())
arr = list(map(int, input().split()))

# left, right 포인터를 모두 0으로 초기화
left, right = 0, 0
min_length = 21e9
ans = 0

while True:
    if ans>=s:
        min_length = min(min_length, right - left)
        ans -= arr[left]
        left += 1
    elif right==n:
        break
    else:
        ans += arr[right]
        right += 1

if min_length == 21e9:
    print(0)
else:
    print(min_length)