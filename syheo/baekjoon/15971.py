#solved.ac
#골드5
#dfs/bfs
#두 로봇
#15971

# 아이디어 
# dfs,bfs로 지나가는 경로 누적 값과 max 값을 계산해서 누적값 - max 값을 함.

# 이유가 뭘 까 ? bfs할때 큐에 누적되는 요소들이 많아서 시간초과나 메모리초과가 발생한듯..?
# dfs -> 100점
# bfs -> 36점
# dijkstra -> 36점

from collections import deque
import sys

sys.setrecursionlimit(10**9)

input = sys.stdin.readline

INF = int(1e9)
N,robot_one,robot_two = map(int,input().split())
visited = [False for _ in range(N+1)]
#visited = [-1 for _ in range(N+1)]
maps = [[] for _ in range(N+1)]
maxValue,sumValue = 0,0
answer = 0

for i in range(N-1):
    a,b,c = map(int,input().split())
    maps[a].append((b,c))
    maps[b].append((a,c))

#시작 노드 , 도착 노드 , sum 값 , max 값
def dfs(robot_one,robot_two,c,maxV):
    global sumValue,maxValue
    #방문 처리
    visited[robot_one]= True
    #탈출 조건 
    if robot_one == robot_two:
        sumValue = c
        maxValue = maxV
        return 
    #연결된 통로 조회 
    for info in maps[robot_one]:
        if not visited[info[0]]:
            dfs(info[0],robot_two,c+info[1],max(maxV,info[1]))

def bfs(robot_one,robot_two):
    global sumValue,maxValue
    q = deque([(robot_one,0)]) #vertex,maxValue
    visited[robot_one] = 0
    while q:
        node,maxV = q.popleft()
        #연결된 통로 조회
        for info in maps[node]:
            #방문 안했다면 
            if visited[info[0]]==-1:
                #합 계산, max 계산
                c = visited[node]+info[1]
                visited[info[0]]=c
                maxV = max(maxV,info[1])
                if info[0]==robot_two:
                    sumValue = c
                    maxValue = maxV
                    break
                q.append((info[0],maxV)) 

#bfs(robot_one,robot_two)
dfs(robot_one,robot_two,0,0)
answer = sumValue-maxValue
print(answer)

