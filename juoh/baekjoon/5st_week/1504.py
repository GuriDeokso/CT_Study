import sys


def solution(X: int, Y: int) -> int:
    global V

    def floyd() -> None:
        for k in range(1, V + 1):
            for i in range(1, V + 1):
                for j in range(1, V + 1):
                    graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])

    floyd()
    # print(graph)
    candidate = [
        [graph[1][X], graph[X][Y], graph[Y][V]],
        [graph[1][Y], graph[Y][X], graph[X][V]]
    ]
    # print(candidate)
    if INF in candidate[0] and INF in candidate[1]:
        return -1
    elif INF in candidate[0]:
        return sum(candidate[1])
    elif INF in candidate[1]:
        return sum(candidate[0])
    else:
        sum1 = sum(candidate[0])
        sum2 = sum(candidate[1])
        return sum2 if sum2 < sum1 else sum1


input = sys.stdin.readline
INF = int(1e9)

V, E = map(int, input().split())
graph = [[INF] * (V + 1) for _ in range(V + 1)]
for i in range(1, V + 1):
    graph[i][i] = 0

for i in range(E):
    start, end, cost = map(int, input().split())
    graph[start][end] = cost
    graph[end][start] = cost
X, Y = map(int, input().split())
print(solution(X, Y))
