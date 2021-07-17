import sys
from collections import deque
from typing import Deque, List, Tuple


# 시간 초과 -> 이분탐색 적용
def solution(start: int, end: int) -> int:
    global N

    def bfs(start: int, end: int, target: int) -> bool:
        visited = [False] * (N + 1)
        visited[start] = True
        q: Deque = deque([start])
        while q:
            now = q.popleft()
            if now == end:
                return True
            for weight, node in graph[now]:
                if not visited[node] and weight >= target:
                    visited[node] = True
                    q.append(node)
        return False

    min_value, max_value = 1, 1000000000
    result = min_value
    while min_value <= max_value:
        mid = (min_value + max_value) // 2
        if bfs(start, end, mid):
            result = mid
            min_value = mid + 1
        else:
            max_value = mid - 1
    return result


input = sys.stdin.readline
N, M = map(int, input().split())
graph: List[List[Tuple[int, int]]] = [[] for _ in range(N + 1)]
for _ in range(M):
    start, end, weight = map(int, input().split())
    graph[start].append((weight, end))
    graph[end].append((weight, start))
start_node, end_node = map(int, input().split())
print(solution(start_node, end_node))
