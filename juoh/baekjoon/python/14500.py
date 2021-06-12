def dfs(x, y, cnt, sum_value):
    global result, MAX_COUNT
    if cnt == MAX_COUNT:
        result = max(result, sum_value)
        return
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx < n and 0 <= ny < m:
            if not visit[nx][ny]:
                visit[nx][ny] = True
                dfs(nx, ny, cnt + 1, sum_value + board[nx][ny])
                visit[nx][ny] = False


def middle_finger(x, y):
    global result, MAX_COUNT

    sum_value = 0
    adj_points = []
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if nx < 0 or nx >= n or ny < 0 or ny >= m:
            continue
        else:
            adj_points.append(board[nx][ny])

    if len(adj_points) == 2:
        return
    elif len(adj_points) == 3:
        sum_value += board[x][y] + sum(adj_points)
    elif len(adj_points) == 4:
        sum_value += board[x][y] + sum(adj_points) - min(adj_points)

    result = max(result, sum_value)


n, m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]
visit = [[False] * m for i in range(n)]
# 동 서 북 남
dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]
MAX_COUNT = 4
result = 0
for i in range(n):
    for j in range(m):
        visit[i][j] = True
        dfs(i, j, 1, board[i][j])
        visit[i][j] = False
        middle_finger(i, j)
print(result)




# n, m = map(int, input().split())
# s = []
# tetromino = [[[0, 1], [0, 2], [0, 3]], [[1, 0], [2, 0], [3, 0]], 
# [[0, 1], [1, 0], [1, 1]], [[1, 0], [2, 0], [2, 1]], 
# [[1, 0], [2, 0], [2, -1]], [[0, 1], [0, 2], [1, 0]], 
# [[0, 1], [0, 2], [1, 2]], [[0, 1], [1, 1], [2, 1]], 
# [[0, 1], [1, 0], [2, 0]], [[0, 1], [0, 2], [-1, 2]], 
# [[1, 0], [1, 1], [1, 2]], [[1, 0], [1, 1], [2, 1]], 
# [[1, 0], [1, -1], [2, -1]], [[0, 1], [-1, 1], [-1, 2]], 
# [[0, 1], [1, 1], [1, 2]], [[0, 1], [0, 2], [1, 1]], 
# [[1, 0], [1, 1], [2, 0]], [[1, 0], [1, -1], [2, 0]], 
# [[0, 1], [0, 2], [-1, 1]]]
# for i in range(n):
#     s.append(list(map(int, input().split())))
# result = 0
# for i in range(n):
#     for j in range(m):
#         for k in tetromino:
#             try:
#                 sum_n = s[i][j] + s[i + k[0][0]][j + k[0][1]] + s[i + k[1][0]][j + k[1][1]] + s[i + k[2][0]][j + k[2][1]]
#             except:
#                 sum_n = 0
#             result = max(result, sum_n)
# print(result)