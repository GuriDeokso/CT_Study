#solved.ac
#골드5
#bfs,dfs
#말이 되고픈 원숭이
#1600

import sys 
from collections import deque
input = sys.stdin.readline

horseMove = [(-1,-2),(-2,-1),(-1,2),(-2,1),(1,2),(2,1),(1,-2),(2,-1)]
kingkongMove = [(-1,0),(1,0),(0,1),(0,-1)]

K = int(input())
W,H = map(int,input().split())
visited = [[[False for _ in range(31)] for j in range(W)] for i in range(H)] 

maps = []
#맵 입력 
for i in range(H):
    maps.append(list(map(int,input().split())))

#정답 체크 함수
def check_ans(r,c):
    if r==H-1 and c==W-1:
        return True 
    else: 
        return False   

#bfs 시작 
q = [(0,0,K,0)] #시작 지점 좌표,K의 갯수(남은 갯수),이동거리수
q = deque(q)

def bfs():
    while q:
        row,col,k,cnt = q.popleft()
        #도착가능 확인
        if check_ans(row,col):
            return cnt
        #원숭이의 이동으로 갈 때 
        for move in kingkongMove:
            r=row+move[0]
            c=col+move[1]
            if 0<=r<=H-1 and 0<=c<=W-1 and not visited[r][c][k] and maps[r][c]==0:
                visited[r][c][k]=True
                q.append((r,c,k,cnt+1))
                
        #말의 이동으로 갈 수 있을 떄 
        if k>0:
            for move in horseMove:
                r=row+move[0]
                c=col+move[1]
                if 0<=r<=H-1 and 0<=c<=W-1 and not visited[r][c][k-1] and maps[r][c]==0:
                    visited[r][c][k-1]=True
                    q.append((r,c,k-1,cnt+1))
                    
    return -1

print(bfs())