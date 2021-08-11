#solved.ac
#골드5
#구현
#테트로미노
#14500

# 아이디어 
# 테트리스 모든 경우를 케이스 리스트에 저장하고 모든 칸 브루트포스 탐색 

from collections import deque

#상 좌 하 우
dx = [-1,0,1,0]
dy = [0,-1,0,1]
#케이스 dx dy 인덱스 변화를 따름 
cases = [(0,0,0),(0,3,3),(0,0,1),(0,0,3),(0,3,1),(0,1,3),(0,0,2,1)]

N,M = map(int,input().split())

maps = []

for i in range(N):
    maps.append(list(map(int,input().split())))

sumList = []

#모든 칸 탐색 
for i in range(N):
    for j in range(M):
        #네가지 이동 방향대로 탐색 
        for k in range(4):
            #케이스마다 탐색 
            for case in cases:
                moveIdx = k
                row = i
                col = j
                sum = maps[row][col]
                #케이스 가능 여부 확인하며 sum 구함 
                for c in range(len(case)):
                    moveIdx = (moveIdx+case[c])%4
                    row += dx[moveIdx]
                    col += dy[moveIdx]
                    #범위 체크 
                    if 0<=row<N and 0<=col<M:
                        if len(case)==4 and c==2:
                            pass
                        else:
                            sum += maps[row][col]
                    else:
                        sum = -1
                        break
                if sum!=-1:
                    sumList.append(sum)
                
print(max(sumList))
                    


