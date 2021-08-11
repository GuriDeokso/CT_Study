import sys
from typing import List


# 투포인트를 이용해서 해결
# 테스트 케이스가 몇개인지 안알려주는 문제
# 이렇게 처리하는게 맞는지 의문
def solution(X: int, legos: List[int]) -> str:
    X = X * pow(10, 7)
    legos.sort()
    left, right = 0, len(legos) - 1
    sum_value = 0
    while left < right:
        sum_value = legos[left] + legos[right]

        # 중복제거
        if left > 0 and legos[left] == legos[left - 1]:
            left += 1
            continue
        elif right < len(legos) - 1 and legos[right] == legos[right + 1]:
            right -= 1
            continue

        if sum_value < X:
            left += 1
        elif sum_value > X:
            right -= 1
        else:
            return f'yes {legos[left]} {legos[right]}'
    return 'danger'


input = sys.stdin.readline
while True:
    X = input().rstrip()
    if not X:
        break
    X = int(X)
    N = int(input().rstrip())
    legos = []
    for _ in range(N):
        legos.append(int(input().rstrip()))
    print(solution(X, legos))
