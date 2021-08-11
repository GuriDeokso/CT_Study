#solved.ac
#골드4
#dp
#작업
#2056

# 아이디어 
# 문제 조건이 K번쨰 작업은 선행작업을 1~K-1에서만 갖는다.
# 그래서 이전에 진행되어진 선행작업의 끝나는 시간을 리스트에 넣어주고 
# 가장 마지막에 끝난 시간을 기준으로 작업을 진행
# dp[i] => i번 노드가 끝난 시간 

from collections import deque

N = int(input())
dp = [0 for _ in range(N+1)]
answer = 0

for i in range(N):
    tmp = list(map(int,input().split()))
    #작업을 진행 
    end_time = [0]
    #선행 작업 순회
    for j in range(tmp[1]):
        #i+1번의 선행 작업은 tmp[2+j]이다.
        end_time.append(dp[tmp[j+2]])
    dp[i+1]=max(end_time)+tmp[0]

print(max(dp))
