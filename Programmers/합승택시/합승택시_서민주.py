import heapq
import math

def solution(n, s, a, b, fares):
    
    def dijkstra(start):
        result = [math.inf]*(n+1)
        heap = []
        heapq.heappush(heap, (0, start))
        result[start] = 0

        while heap:
            sk, k = heapq.heappop(heap)
            if sk>result[k]:
                continue
            for i in arr[k]:
                cost = sk+i[1]
                if cost<result[i[0]]:
                    result[i[0]] = cost
                    heapq.heappush(heap, (cost, i[0]))
                    
        return result
                
    arr = [[] for _ in range(n+1)]
    
    for fare in fares:
        arr[fare[0]].append((fare[1], fare[2]))
        arr[fare[1]].append((fare[0], fare[2]))
    
    # 최단거리 구하기
    dist = [0] + [dijkstra(i) for i in range(1, n+1)]
    
    path = math.inf
    for i in range(1, n+1):
        path = min(path, dist[s][i] + dist[i][a] + dist[i][b])
    
    return path