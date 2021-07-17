import sys
from collections import deque


# 전형적인 bfs
# 말이 되고픈 원숭이때부터 느낀건데 끝나는 타이밍에 대해서 조금 의문
def solution(h: int, x: int, y: int) -> int:
    global L, R, C
    # print('s', staart)

    def is_valid_board(h: int, x: int, y: int) -> bool:
        if h < 0 or x < 0 or y < 0 or h >= L or x >= R or y >= C:
            return False
        return True

    q = deque()
    q.append((h, x, y, 0))
    visited[h][x][y] = True
    while q:
        h, x, y, time = q.popleft()
        for i in range(6):
            nh = h + dh[i]
            nx = x + dx[i]
            ny = y + dy[i]
            if is_valid_board(nh, nx, ny):
                if board[nh][nx][ny] == 'E':
                    return time + 1
                if not visited[nh][nx][ny] and board[nh][nx][ny] == '.':
                    q.append((nh, nx, ny, time + 1))
                    visited[nh][nx][ny] = True
    return -1


input = sys.stdin.readline
# 동 서 남 북 상 하
dh = [0, 0, 0, 0, -1, 1]
dx = [0, 0, 1, -1, 0, 0]
dy = [1, -1, 0, 0, 0, 0]

results = []
while True:
    L, R, C = map(int, input().split())
    if L == 0:
        break
    board = [[[] for _ in range(R)] for _ in range(L)]
    visited = [[[False] * C for _ in range(R)] for _ in range(L)]
    start = (0, 0, 0)
    for i in range(L):
        for j in range(R):
            board[i][j] = list(input().rstrip())
            for k in range(C):
                if board[i][j][k] == 'S':
                    start = (i, j, k)
        input()
    time = solution(start[0], start[1], start[2])
    results.append(f'Escaped in {time} minute(s).' if time != -1 else 'Trapped!')

for result in results:
    print(result)
