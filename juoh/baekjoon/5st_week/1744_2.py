
import sys


def solution() -> int:
    input = sys.stdin.readline
    positive = []
    negative = []
    N = int(input().rstrip())
    result = 0
    for _ in range(N):
        n = int(input().rstrip())
        if n == 1:
            result += 1
        elif n > 1:
            positive.append(n)
        else:
            negative.append(n)

    positive.sort(reverse=True)
    negative.sort()

    # 양수 리스트 더해주기
    if len(positive) % 2 == 0: # 양수가 짝수개 일경우 두개씩 곱해준다.
        for i in range(0, len(positive), 2):
            result += positive[i] * positive[i+1]
    else:
        for i in range(0, len(positive)-1, 2):
            result += positive[i] * positive[i+1]
        result += positive[len(positive)-1] # 마지막 수는 더해준다.

    # 음수 더해주기
    if len(negative) % 2 == 0: # 음수가 짝수개 일경우 두개씩 곱해준다.
        for i in range(0, len(negative), 2):
            result += negative[i] * negative[i+1]
    else:
        for i in range(0, len(negative)-1, 2):
            result += negative[i] * negative[i+1]
        result += negative[len(negative)-1] # 마지막 수는 더해준다.
    return result


print(solution())