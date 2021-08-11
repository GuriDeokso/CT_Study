#solved.ac
#골드4
#이진탐색 
#카누 선수
#9007

# 아이디어
#  

import sys 
input = sys.stdin.readline

T = int(input())

#binary Search
def binary_search(l,r):
    pass

answers = []

for test in range(T):
    classes = []
    min_value = int(1e9)
    answer = int(1e9)
    k,n = map(int,input().split()) # 보트 특정 값, 반 학생 수 
    for i in range(4):
        classes.append(sorted(list(map(int,input().split())))) # 정렬하여 삽입
    #print(classes)
    indexes = [[0,n-1] for _ in range(4)] #각 반의 start, end 인덱스

    # 각 반의 무게에서 순차적으로 이진탐색 진행쓰 
    for i in range(4):
        weight_sum = sum([classes[(indexes[i][0]+indexes[i][1])//2] for i in range(4)])


    
    #정답 저장 
    answers.append(k-answer)

for i in range(T):
    print(answers[i])

