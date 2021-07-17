import sys


# 코드가 매우 깔끔
def dfs(v, i):
    visited[v] = True
    for adj_v in graph[v]:
        if not visited[adj_v]:
            dfs(adj_v, i)
        elif visited[adj_v] and adj_v == i:
            result.append(adj_v)


input = sys.stdin.readline
N = int(input().rstrip())
graph = [[] for _ in range(N + 1)]
for i in range(N):
    graph[i+1].append(int(input().rstrip()))

result = []
for i in range(1, N + 1):
    visited = [False] * (N + 1)
    dfs(i, i)

print(len(result))
for x in result:
    print(x)
