#solved.ac
#골드5
#그리디
#강의실 배정
#11000

# 아이디어 
# infos 를 Si기준으로 오름차순 시킨다음에 
# 들어오는 강의에 대해서 lastT를 강의의 T시간으로 갱신시킴.
# -> 강의실이 1개가아니라 여러개일때 t값도 여러개임

# 아이디어 2
# info를 Si기준으로 오름차순 정렬 
# 우선순위 큐를 사용해서 
# 강의실이 추가될 때마다 가장 작은 t값을 반환 

import sys 
import heapq

input = sys.stdin.readline

N = int(input())

infos = []
for i in range(N):
    infos.append(list(map(int,input().split())))

infos.sort(key=lambda x:x[0])

cnt = 0
lastT = [int(1e9)]
heapq.heapify(lastT)
for info in infos:
    t = lastT[0]
    if t>info[0]:
        cnt+=1
    else:
        heapq.heappop(lastT)
    heapq.heappush(lastT,info[1])

print(cnt)