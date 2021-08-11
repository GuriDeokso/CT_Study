#solved.ac
#골드4
#이진탐색 
#카누 선수
#9007

# 아이디어
#  
from collections import deque
import sys 
input = sys.stdin.readline

T = int(input())

def isPossible(k,cmp_value,min_value):
    if abs(k-(cmp_value))<abs(min_value):
        return True
    else:
        return False

def isPlus(k,cmp_value):
    if k-cmp_value>0:
        return True
    else:
        return False

answers = []

for test in range(T):
    classes = []
    cnt = 0
    min_value = int(1e9)
    answer = int(1e9)
    k,n = map(int,input().split()) # 보트 특정 값, 반 학생 수 
    for i in range(4):
        classes.append(sorted(list(map(int,input().split())))) # 정렬하여 삽입
    #print(classes)
    indexes = [0 if i%2==0 else n-1 for i in range(8)] #각 반의 start, end 인덱스 
    q = deque([(tuple(indexes),min_value)])
    visited = set()
    while q:
        #print(q)
        indexes,min_value = q.popleft()
        indexes = list(indexes)

        if not any([indexes[2*i+1]-indexes[2*i]>0 for i in range(4)]):
            continue
        weight_sum = sum([classes[i][(indexes[2*i]+indexes[2*i+1])//2] for i in range(4)])
        if isPossible(k,weight_sum,min_value):
            min_value = k-weight_sum
            answer = min_value if abs(answer)>abs(min_value) else answer
            if isPlus(k,weight_sum): 
                for i in range(4):
                    tmp = indexes[2*i]
                    indexes[2*i] = (indexes[2*i]+indexes[2*i+1])//2+1
                    if ''.join(list(map(str,indexes))) not in visited:
                        q.append((tuple(indexes),min_value))
                        visited.add(''.join(list(map(str,indexes))))
                    #print(indexes[:])
                    indexes[2*i] = tmp
                    cnt+=1
            else:
                for i in range(4):
                    tmp = indexes[2*i+1]
                    indexes[2*i+1] = (indexes[2*i]+indexes[2*i+1])//2+1
                    if ''.join(list(map(str,indexes))) not in visited:
                        q.append((tuple(indexes),min_value))
                        visited.add(''.join(list(map(str,indexes))))
                    indexes[2*i+1] = tmp
                    cnt+=1
                
    answers.append(k-answer)
    print('cnt=',cnt)
for i in range(T):
    print(answers[i])
