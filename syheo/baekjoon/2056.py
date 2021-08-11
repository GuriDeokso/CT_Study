#solved.ac
#골드4
#dp
#작업
#2056

# 아이디어 
# 위상정렬을 사용하여 진입차수가 0인애들을 뽑아 
# 그 애들의 선행작업이 끝난 마지막 시간을 기준으로 작업을 진행한다.
# dp엔 선행작업이 끝난 시간을 업데이트해주고, 진입차수가 0 이 되면 작업 진행시간을 더한다.

from collections import deque

N = int(input())

times = [0 for _ in range(N+1)]
indegree = [0 for _ in range(N+1)]
relations = [[] for _ in range(N+1)]
dp = [0 for _ in range(N+1)]
answer = 0

for i in range(N):
    tmp = list(map(int,input().split()))
    times[i+1]=tmp[0]
    #관계 삽입 및 진입차수 갱신
    for j in range(tmp[1]):
        #i+1번의 선행 작업은 tmp[2+j]이다.
        relations[tmp[2+j]].append(i+1)
        indegree[i+1]+=1
q = deque()
for i in range(1,N+1):
    if indegree[i]==0:
        q.append(i) # 노드 번호,이전 노드가 끝난 시간

while q:
    node = q.popleft()
    #node가 끝나는 시간 
    dp[node]+=times[node]
    for i in relations[node]:
        indegree[i]-=1
        #i의 시작시간
        dp[i]=max(dp[i],dp[node])
        if indegree[i]==0:
            q.append(i)

print(max(dp))
