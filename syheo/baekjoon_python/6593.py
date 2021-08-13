#solved.ac
#골드5
#dfs/bfs
#상범 빌딩
#6593

# 아이디어 
# 여기서는 상하좌우 뿐만 아니라 남북 이동 케이스도 있다는게 특이 케이스!
# 나는 3차원 배열로 층을 구분하지 않고 2차원 배열로 하여 
# 1차원 인덱스(row)의 값의 (R+1)로 나눈 몫 +1 을 층수로 계산했다!
# 그런 다음 기존의 bfs 방식대로 진행하여 답을 도출함!

import sys 

from collections import deque

input = sys.stdin.readline

#북,남,하,상,우,좌


def bfs(start,end,L,R,C,visited):
    dx = [(R+1),-(R+1),1,-1,0,0]
    dy = [0,0,0,0,1,-1]
    q = deque([(start[0],start[1],0)]) # row,col,cnt
    visited[start[0]][start[1]] = True
    while q:
        row,col,cnt = q.popleft()
        if row==end[0] and col==end[1]:
            return cnt
        for i in range(6):
            r = row + dx[i]
            c = col + dy[i]
            if 0<=r<R*L+L and 0<=c<C and r%(R+1)!=R and not visited[r][c] and maps[r][c]!='#':
                q.append((r,c,cnt+1))
                visited[r][c]=True

    return -1

while True:
    L,R,C = map(int,input().split())
    if L==0 and R==0 and C==0:
        break
    # 맵 입력
    maps = []
    start = (0,0)
    end = (0,0)
    for i in range(R*L+L):
        tmp = list(input().rstrip())
        #start, end 위치 저장 
        if i%(R+1)!=R:
            for j in range(C):
                if tmp[j] == 'S':
                    start = (i,j)
                if tmp[j] == 'E':
                    end = (i,j)
        maps.append(tmp)
    
    visited = [[False]*C for _ in range(R*L+L)]
    # bfs 
    rst = bfs(start,end,L,R,C,visited)
    if rst == -1:
        print('Trapped!')
    else:
        print('Escaped in %d minute(s).'%(rst))
