#solved.ac
#골드4
#dp
#내리막길
#1520

# 아이디어 
# bfs 를 돌면서 범위 체크, 내리막길 체크를 제외하고 두가지 조건을 확인하여 접근한다.
# 같은 방향에서 온 경우가 없는지 => visited[r][c][i]==0 
# 같은 방향에서 온 경우가 있는데 늦게 업데이트 
# 궁금점
# 완전탐색 -> 시간 초과 , 조건  (visited[r][c][i]==0 or visited[r][c][i]<sum(visited[row][col])) 넣었을 땐 정답
# 정확히 어느 경우에서 시간을 단축시키는걸까.

from collections import deque
import sys 
input = sys.stdin.readline

dx = [0,0,1,-1]
dy = [1,-1,0,0]

def bfs(N,M):
    q = deque([(0,0)])
    while q:
        row, col = q.popleft()
        for i in range(4):
            r = row+dx[i]
            c = col+dy[i]
            if 0<=r<N and 0<=c<M and (visited[r][c][i]==0 or visited[r][c][i]<sum(visited[row][col])) and maps[row][col]>maps[r][c]:
                q.append((r,c))
                if visited[r][c][i]==0:
                    visited[r][c][i]+=1
                else:
                    visited[r][c][i]=sum(visited[row][col])
                

N,M = map(int,input().split())

maps = [list(map(int,input().split())) for _ in range(N)]
visited = [[[0]*4 for i in range(M)] for _ in range(N)] #dp로 사용 

bfs(N,M)

print(sum(visited[N-1][M-1]))
