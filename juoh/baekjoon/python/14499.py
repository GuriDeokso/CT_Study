# 주사위의 움직임을 어떻게 구현해야 할지 생각이 안났음
# 실패자
def move(command, dice):
    '''
    동쪽 이동    서쪽 이동   북쪽 이동   남쪽 이동
    1 -> 4     1 -> 3    1 -> 5    1 -> 2
    2 -> 2     2 -> 2    2 -> 1    2 -> 6
    3 -> 1     3 -> 6    3 -> 3    3 -> 3
    4 -> 6     4 -> 1    4 -> 4    4 -> 4
    5 -> 5     5 -> 5    5 -> 6    5 -> 1
    6 -> 3     6 -> 4    6 -> 2    6 -> 5
    '''
    if command == 1:
        return [0, dice[4], dice[2], dice[1], dice[6], dice[5], dice[3]]
    elif command == 2:
        return [0, dice[3], dice[2], dice[6], dice[1], dice[5], dice[4]]
    elif command == 3:
        return [0, dice[5], dice[1], dice[3], dice[4], dice[6], dice[2]]
    elif command == 4:
        return [0, dice[2], dice[6], dice[3], dice[4], dice[1], dice[5]]
    else:
        pass


n, m, x, y, k = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]
commands = list(map(int, input().split()))

dice = [0] * 7
dx = [0, 0, 0, -1, 1]
dy = [0, 1, -1, 0, 0]

for command in commands:
    nx = x + dx[command]
    ny = y + dy[command]

    if nx < 0 or ny < 0 or nx >= n or ny >= m:
        continue
    else:
        x = nx
        y = ny
        dice = move(command, dice)
        if board[nx][ny] == 0:
            board[nx][ny] = dice[6]
        else:
            dice[6] = board[nx][ny]
            board[nx][ny] = 0
        print(dice[1])
