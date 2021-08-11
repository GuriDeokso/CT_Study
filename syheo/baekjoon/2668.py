#solved.ac
#골드5
#dfs/bfs
#숫자 고르기
#2668

#dfs 풀이

N = int(input())

nums = [0 for _ in range(N+1)]
visited = [False for _ in range(N+1)]

answer = set()

def dfs(i,first,second):
    #방문했다면 return 
    if visited[i]:
        return (first,second)
    first.add(i)
    second.add(nums[i])
    visited[i]=True
    first,second = dfs(nums[i],first,second)
    return (first,second)
    
#입력 받으면서 first,second이 같으면 answer에 더해주고, 방문 처리
for i in range(1,N+1):
    nums[i]=int(input())
    if nums[i]==i:
        answer.add(i)
        visited[i]=True

for i in range(1,N+1):
    if nums[i]!=i:
        first, second = dfs(i,set(),set())
        #둘이 같다면?
        if first == second: 
            answer = answer.union(first)
        #다르면 방문처리 다시 돌려놓음 
        else:
            for i in first:
                visited[i]=False
#답 출력
print(len(answer))
for num in sorted(list(answer)):
    print(num)

