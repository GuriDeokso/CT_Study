import sys
import heapq
from typing import List, Tuple


def solution(x: int, y: int, v: int) -> int:
    def dijkstra(start: int) -> List[Tuple[int, int]]:
        distance = [INF] * (v + 1)
        q = []
        heapq.heappush(q, (0, start))
        distance[start] = 0
        while q:
            dist, now = heapq.heappop(q)
            for adj_cost, adj_node in graph[now]:
                new_cost = dist + adj_cost
                if new_cost < distance[adj_node]:
                    distance[adj_node] = new_cost
                    heapq.heappush(q, (new_cost, adj_node))
        return distance

    dist_by_start = dijkstra(1)
    dist_by_x = dijkstra(x)
    dist_by_y = dijkstra(y)
    candidates = [
        [dist_by_start[x], dist_by_x[y], dist_by_y[v]],
        [dist_by_start[y], dist_by_y[x], dist_by_x[v]]
    ]
    if INF in candidates[0] and INF in candidates[1]:
        return -1
    elif INF in candidates[0]:
        return sum(candidates[1])
    elif INF in candidates[1]:
        return sum(candidates[0])
    else:
        sum1 = sum(candidates[0])
        sum2 = sum(candidates[1])
        return min(sum1, sum2)


input = sys.stdin.readline
INF = int(1e9)

V, E = map(int, input().split())
graph = [[] for _ in range(V + 1)]

for i in range(E):
    start, end, cost = map(int, input().split())
    graph[start].append((cost, end))
    graph[end].append((cost, start))
X, Y = map(int, input().split())
print(solution(X, Y, V))
