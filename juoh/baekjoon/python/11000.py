import heapq
import sys


# 시간 초과 >> n^2 = 4백억
# 우선순위 큐(힙기반) 복잡도: logN
# 솔루션 시간 복잡도: NlogN
# 바로 풀지못함
def my_solution(N, class_times):
    q = []
    sorted_class_times = sorted(class_times, key=lambda x: x[0])
    heapq.heappush(q, sorted_class_times[0][1])

    for i in range(1, N):
        start_time, end_time = sorted_class_times[i]
        if q[0] <= start_time:
            heapq.heappop(q)
            heapq.heappush(q, end_time)
        else:
            heapq.heappush(q, end_time)
    print(len(q))


N = int(input())
class_times = [tuple(map(int, sys.stdin.readline().split())) for _ in range(N)]
my_solution(N, class_times)
