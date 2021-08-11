#solved.ac
#골드5
#dfs/bfs
#두 로봇
#15971

import heapq
import sys 

input = sys.stdin.readline

INF = int(1e9)
N,robot_one,robot_two = map(int,input().split())
maps = [[] for _ in range(N+1)]
maxValue,sumValue = 0,0
answer = 0

for i in range(N-1):
    a,b,c = map(int,input().split())
    maps[a].append((b,c))
    maps[b].append((a,c))

def dijkstra(robot_one,robot_two):
    global maxValue, sumValue
    q = []
    heapq.heappush(q,(0,robot_one,0)) #cost,vertex,maxValue
    distance[robot_one]=0
    while q:
        cost,node,maxV = heapq.heappop(q)
        if cost>distance[node]:
            continue
        for info in maps[node]:
            c = cost+info[1]
            maxV = max(maxV,info[1])
            if c<distance[info[0]]:
                if info[0]==robot_two:
                    sumValue = c
                    maxValue = maxV
                distance[info[0]]=c
                q.append((c,info[0],maxV)) # 1 - 2 - 3 - 4 - 5

distance = [INF for _ in range(N+1)]
dijkstra(robot_one,robot_two)
#print(distance,route)
answer = sumValue-maxValue
print(answer)