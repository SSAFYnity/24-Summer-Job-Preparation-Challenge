from sys import maxsize

n, s = map(int, input().split())
nums = list(map(int, input().split()))

i = j = 0
min_len = maxsize
sub_sum = nums[i]
while i <= j and j < n:
  if sub_sum < s:
    j += 1
    if j == n:
      break
    sub_sum += nums[j]
  else:
    min_len = min(min_len, j-i+1)
    sub_sum -= nums[i]
    i += 1

if min_len == maxsize:
  print(0)
else:
  print(min_len)
