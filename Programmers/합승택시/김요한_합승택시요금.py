import heapq


def solution(n, s, a, b, fares):
    answer = float('inf')

    graph = {v + 1: {} for v in range(n)}

    for fare in fares:
        start, end, charge = fare
        graph[start][end] = charge
        graph[end][start] = charge

    # dijkstra
    distances = dict()
    nodes = (s, a, b)
    for i, node in enumerate(nodes):
        queue = []
        heapq.heappush(queue, (0, node))
        dists = {v: float('inf') for v in graph}
        dists[node] = 0
        while queue:
            cur_d, cur_v = heapq.heappop(queue)
            if cur_d <= dists[cur_v]:
                for neighbor, weight in graph[cur_v].items():
                    dist = cur_d + weight
                    if dists[neighbor] > dist:
                        dists[neighbor] = dist
                        heapq.heappush(queue, (dist, neighbor))
        distances[i] = dists

    # 같이 타고 가는 거리 -> distances[0][i + 1]
    # A만 따로 가는 거리 -> distances[1][i + 1]
    # B만 따로 가는 거리 -> distances[2][i + 1]
    for i in range(n):
        answer = min(answer,
                     distances[0][i + 1] + distances[1][i + 1] + distances[2][i + 1]
                     )

    return answer


# 예제 입력
print(solution(6, 4, 6, 2,
               [[4, 1, 10], [3, 5, 24], [5, 6, 2], [3, 1, 41], [5, 1, 24], [4, 6, 50], [2, 4, 66], [2, 3, 22],
                [1, 6, 25]]))

