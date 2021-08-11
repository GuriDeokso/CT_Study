#solved.ac
#골드4
#이진탐색 
#카누 선수
#9007

# 틀렸습니다 

import sys 
sys.setrecursionlimit(10**9)
input = sys.stdin.readline

T = int(input())

def isPlus(k,cmp_value):
    if k-cmp_value>0:
        return True
    else:
        return False

answers = []

def dfs():
    global answer,cnt,visited
    if not any([indexes[2*i+1]-indexes[2*i]>0 for i in range(4)]):
        return
    weight_sum = sum([classes[i][(indexes[2*i]+indexes[2*i+1])//2] for i in range(4)])
    #print(indexes,weight_sum)
    min_value = k-weight_sum
    answer = min_value if abs(answer)>abs(min_value) else answer
    if isPlus(k,weight_sum): 
        for i in range(4):
            if indexes[2*i+1]-indexes[2*i]>0:
                tmp = indexes[2*i]
                #left = middle+1
                indexes[2*i] = (indexes[2*i]+indexes[2*i+1])//2+1
                if ''.join(list(map(str,indexes))) not in visited:
                    visited.add(''.join(list(map(str,indexes))))
                    dfs()
                #print(indexes[:])
                indexes[2*i] = tmp
                cnt+=1
    else:
        for i in range(4):
            if indexes[2*i+1]-indexes[2*i]>0:
                tmp = indexes[2*i+1]
                #rignt = middle-1
                indexes[2*i+1] = (indexes[2*i]+indexes[2*i+1])//2-1
                if ''.join(list(map(str,indexes))) not in visited:
                    visited.add(''.join(list(map(str,indexes))))
                    dfs()
                indexes[2*i+1] = tmp
                cnt+=1

for test in range(T):
    cnt = 0
    classes = []
    answer = int(1e9)
    k,n = map(int,input().split()) # 보트 특정 값, 반 학생 수 
    for i in range(4):
        classes.append(sorted(list(map(int,input().split())))) # 정렬하여 삽입
    #print(classes)
    indexes = [0 if i%2==0 else n-1 for i in range(8)] #각 반의 start, end 인덱스 
    visited = set()
    dfs()
    print('cnt=',cnt)
    answers.append(k-answer)

for i in range(T):
    print(answers[i])


# 1 3 5 9 10000