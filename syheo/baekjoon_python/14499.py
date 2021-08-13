#solved.ac
#골드5
#구현
#주사위 굴리기
#14499

N,M,x,y,K = map(int,input().split())
#주사위 숫자
dice = [0, 0, 0, 0, 0, 0, 0]
#이동 
dx = [0,0,0,-1,1]
dy = [0,1,-1,0,0]
# 좌우 이동 -> 바닥면에서 우측으로 이동 기준
left_right_move = [1,3,6,4] 
# 상하 이동 -> 바닥면에서 하측으로 이동 기준 
up_down_move = [1,5,6,2]
maps = []

#맵 정보 입력
for i in range(N):
    maps.append(list(map(int,input().split())))

#명령 입력 
cmds = list(map(int,input().split()))

#좌우, 상하 이동 초기 인덱스 
lrIdx = 0
udIdx = 0

#정답 리스트
answers = []

for i in range(K):
    cmd = cmds[i]
    # 이동 가능 할 때만 
    if 0<= x+dx[cmd] <N and 0<= y+dy[cmd] <M:
        x += dx[cmd]
        y += dy[cmd]
        #우측 이동 
        if cmd == 1:
            lrIdx+=1
            up_down_move[udIdx%4] = left_right_move[lrIdx%4]
            up_down_move[(udIdx+2)%4] = 7-up_down_move[udIdx%4]
        #좌측 이동 
        elif cmd == 2:
            lrIdx-=1
            up_down_move[udIdx%4] = left_right_move[lrIdx%4]
            up_down_move[(udIdx+2)%4] = 7-up_down_move[udIdx%4]
        #상측 이동 
        elif cmd == 3:
            udIdx-=1
            left_right_move[lrIdx%4] = up_down_move[udIdx%4]
            left_right_move[(lrIdx+2)%4] = 7-left_right_move[lrIdx%4]
        #하측 이동 
        elif cmd == 4:
            udIdx+=1
            left_right_move[lrIdx%4] = up_down_move[udIdx%4]
            left_right_move[(lrIdx+2)%4] = 7-left_right_move[lrIdx%4]

        if maps[x][y]==0:
            maps[x][y]=dice[left_right_move[lrIdx%4]]
            answers.append(dice[left_right_move[(lrIdx+2)%4]])
        else:
            dice[left_right_move[lrIdx%4]] = maps[x][y]
            maps[x][y]=0
            answers.append(dice[left_right_move[(lrIdx+2)%4]])


for answer in answers:
    print(answer)
                

