import sys

# 경로를 구하고 최대 간선의 값을 빼는 방법으로 생각
# 맨처음에는 result 리스트에 간선의 값들을 다 넣어주는 방식이었는데 이방식은 끝내기가 어려웠다.
# 또 python으로 하기가 까다로웠다. 재귀, 메모리, 재귀 초과 등등..


def dfs(node: int, dist: int, max_value: int) -> None:
    global result, end
    visited[node] = True
    if node == end:
        result = dist - max_value
        return
    for next_node, cost in graph[node]:
        if not visited[next_node]:
            dfs(next_node, dist + cost, max(max_value, cost))


sys.setrecursionlimit(10**6)
input = sys.stdin.readline
NODE_COUNT, start, end = map(int, input().split())
visited = [False] * (NODE_COUNT + 1)
graph = [[] for _ in range(NODE_COUNT + 1)]
for _ in range(NODE_COUNT - 1):
    start_node, end_node, cost = map(int, input().split())
    graph[start_node].append((end_node, cost))
    graph[end_node].append((start_node, cost))
result = 0
dfs(start, 0, 0)
print(result)
