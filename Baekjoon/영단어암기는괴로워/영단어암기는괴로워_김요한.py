import sys
from collections import Counter

input = sys.stdin.readline

N, M = map(int, input().strip().split())

data = [input().strip() for _ in range(N)]

words = list(Counter(_ for _ in data if len(_) >= M).items())

words.sort(key=lambda word: (-word[1], -len(word[0]), word[0]))

for word in words:
    print(word[0])
