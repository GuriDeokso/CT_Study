#solved.ac
#골드4
#정렬 + 이분탐색
#로봇 프로젝트
#3649

# 첫 아이디어: 
# 레고들을 오름차순 정렬한 후에 작은 값부터 x-a를 만족하는게 있는지 찾음 nlogn + n^2
# 두번째 아이디어 : 
# 정렬 없이 모든 원소에 대해 x-a를 만족하는 값이 있는지 찾음 -> min 값 비교를 통해 정답 도출  n^2
# 세번째 아이디어 :
# set 자료형에 넣어두어 x-a를 만족하는 값이 있는지 찾음 -> min 값이 있는지 찾음  (nlogn)^2
# 네번째 아이디어 : 
# 정렬된 리스트에서 제일 작은 값 부터 시작해서 만족하는 값을 이분탐색으로 값을 찾음. -> nlogn + nlogn
# 다섯번째 아이디어 : 
# 투포인터 개념을 사용하여 정렬된 리스트에서 시작과 끝 인덱스를 설정하여 합이 x가 만족하는지 큰지 작은지를 통해 인덱스를 조절함.

import sys
from collections import deque
input = sys.stdin.readline

def is_possible(find_value,legos):
    left = 0
    right = len(legos)-1
    while left<=right:
        middle = (left+right)//2
        if legos[middle]<find_value:
            left = middle+1
        elif legos[middle]>find_value:
            right = middle-1
        else: # 찾았을 경우 
            return True
    return False

def search(legos,x):
    answer = 10000001
    legos = deque(legos)
    while legos:
        a = legos.popleft()
        if a>x//2+1:
            break
        find_value = x-a
        if is_possible(find_value,legos):
            return a
    return answer 
        

while sys.stdin:
    x = input().rstrip()
    if not x:
        break
    x = int(x)*10**7
    n = int(input().rstrip())
    legos = []
    for i in range(n):
        legos.append(int(input().rstrip()))
    legos.sort()
    answer = search(legos,x)
    if answer == 10000001:
        print('danger')
    else:
        print("yes %d %d"%(answer,x-answer))

