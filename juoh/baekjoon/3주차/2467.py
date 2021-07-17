import sys
from typing import List


def solution(n: int, arary: List[int]) -> None:
    left, right = 0, len(array) - 1
    min_value = sys.maxsize
    result = [0] * 2
    while left < right:
        sum_value = arary[left] + arary[right]
        if abs(sum_value) < min_value:
            result[0] = arary[left]
            result[1] = arary[right]
            min_value = abs(sum_value)
            if sum_value == 0:
                break
        if sum_value > 0:
            right -= 1
        else:
            left += 1
    print(result[0], result[1])


input = sys.stdin.readline
N = int(input().rstrip())
array = list(map(int, input().split()))
solution(N, array)
