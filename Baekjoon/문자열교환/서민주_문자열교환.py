arr = list(input())
a = arr.count('a')

arr += arr[0:a-1]
min_val = float('inf')
for i in range(len(arr) - (a-1)):
    min_val = min(min_val, arr[i:i+a].count('b'))
print(min_val)