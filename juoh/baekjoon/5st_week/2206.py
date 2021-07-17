import sys
from collections import deque


def solution(row: int, col: int) -> int:

    def bfs() -> None:
        q = deque()
        q.append((0, 0, 0))
        visited[0][0][0] = 1
        while q:
            x, y, count = q.popleft()
            if x == row - 1 and y == col - 1:
                return visited[x][y][count]

            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]
                if not is_valid(nx, ny):
                    continue
                if graph[nx][ny] == 1 and count == 0:
                    q.append((nx, ny, 1))
                    visited[nx][ny][1] = visited[x][y][count] + 1
                elif graph[nx][ny] == 0 and visited[nx][ny][count] == 0:
                    q.append((nx, ny, count))
                    visited[nx][ny][count] = visited[x][y][count] + 1
        return -1

    def is_valid(x: int, y: int) -> bool:
        return 0 <= x and x < row and 0 <= y and y < col

    # 상 하 좌 우
    graph = [list(map(int, input().rstrip())) for _ in range(row)]
    visited = [[[0] * 2 for i in range(col)] for i in range(row)]
    return bfs()


input = sys.stdin.readline
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

row, col = map(int, input().split())
print(solution(row, col))
