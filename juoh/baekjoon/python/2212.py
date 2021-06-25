import sys
import heapq
from typing import List


# 총 센서 길이(max - min)에서 두 센서간 길이의 차이가 가장 큰 것들을 k-1개 빼면 된다고 생각
def solution(N: int, K: int, sensors: List[int]) -> int:
    sensors.sort()
    difference = []
    for i in range(N - 1):
        difference.append((sensors[i+1] - sensors[i]))
    difference.sort(reverse=True)

    total_length = sensors[-1] - sensors[0]
    return total_length - sum(difference[:K-1])


# 우선순위 큐
def solution2(N: int, K: int, sensors: List[int]) -> int:
    sensors.sort()
    q = []
    for i in range(N - 1):
        diff = sensors[i+1] - sensors[i]
        heapq.heappush(q, (-diff, diff))

    total_length = sensors[-1] - sensors[0]
    for i in range(K-1):
        if not q:
            break
        total_length -= heapq.heappop(q)[1]
    return total_length


input = sys.stdin.readline
N = int(input().rstrip())
K = int(input().rstrip())
sensors = list(map(int, input().split()))
print(solution(N, K, sensors))
