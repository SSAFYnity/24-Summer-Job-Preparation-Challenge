from collections import Counter

N, arr = int(input()), list(input())

left = Counter(arr[: N // 2])
right = Counter(arr[-N // 2 + 1 if N % 2 else -N // 2:])

multiset = (left | right) - (left & right)

new_arr = [(x, 1) for x, y in multiset.items() if y % 2 == 1]

print('Yes' if len(new_arr) in (0, 1) else 'No')
