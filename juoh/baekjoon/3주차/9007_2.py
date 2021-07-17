# 찾아봄
import sys
from typing import List


def solution(target: int, class_size: int, studnets: List[List[int]]) -> int:
    global CLASS_COUNT

    list1 = []
    list2 = []
    for i in range(class_size):
        for j in range(class_size):
            list1.append(students[0][i] + students[1][j])
            list2.append(students[2][i] + students[3][j])
    list1.sort()
    list2.sort()

    # 절대값
    min_value = sys.maxsize
    # 정답값
    answer = -1
    for i in range(class_size**2):
        # list2에서 찾을꺼
        binary_target = target - list1[i]
        left, right = 0, class_size**2 - 1
        while left <= right:
            mid = (left + right) // 2

            # 정답 if
            if (abs(binary_target - list2[mid]) < min_value) or \
               (abs(binary_target - list2[mid]) == min_value and list1[i] + list2[mid] < answer):
                min_value = abs(binary_target - list2[mid])
                answer = list1[i] + list2[mid]

            if binary_target < list2[mid]:
                right = mid - 1
            elif binary_target > list2[mid]:
                left = mid + 1
            else:
                return target
    return answer


input = sys.stdin.readline
T = int(input().rstrip())
CLASS_COUNT = 4
result = []
for _ in range(T):
    TARGET, CLASS_SIZE = map(int, input().split())
    students = [list(map(int, input().split())) for _ in range(CLASS_COUNT)]
    # print(students)
    result.append(solution(TARGET, CLASS_SIZE, students))

for x in result:
    print(x)
