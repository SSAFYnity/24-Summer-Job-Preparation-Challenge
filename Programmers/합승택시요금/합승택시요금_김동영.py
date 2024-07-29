import heapq

def solution(n, s, a, b, fares):
    graph = [[] for _ in range(n + 1)]
    for x, y, z in fares:
        graph[x].append((y, z))
        graph[y].append((x, z))
    
    def dijkstra(start):
        distance = [float("inf")] * (n + 1)
        q = []
        heapq.heappush(q, (0, start))
        distance[start] = 0
        
        while q:
            dist, now = heapq.heappop(q)
            
            if distance[now] < dist:
                continue
            
            for i in graph[now]:
                cost = dist + i[1]
                if cost < distance[i[0]]:
                    distance[i[0]] = cost
                    heapq.heappush(q, (cost, i[0]))
    
        return distance
    
    dist_from_s = dijkstra(s)
    dist_from_a = dijkstra(a)
    dist_from_b = dijkstra(b)
    
    answer = float("inf")
    for k in range(1, n+1):
        answer = min(answer, dist_from_s[k] + dist_from_a[k] + dist_from_b[k])
    
    return answer