import sys
from typing import List

# 양수, 양수 = 곱셈
# 음수, 음수 = 곱셈
# 양수, 음수 = 덧셈

# 0, 양수 = 덧셈
# 0, 음수 = 곱셈

# 1, 양수 = 덧셈
# 1, 음수 = 덧셈

def solution(size: int, nums: List[int]) -> int:
    nums.sort()
    max_sum = 0
    left, right = 0, size - 1
    is_left_stop, is_right_stop = False, False
    while left < right:
        if nums[left] > 0 or nums[left+1] > 0:
            is_left_stop = True
        else:
            max_sum += nums[left] * nums[left+1]
            left += 2

        if nums[right] <= 1 or nums[right-1] <= 1:
            is_right_stop = True
        else:
            max_sum += nums[right] * nums[right-1]
            right -= 2

        if left == right or (is_left_stop and is_right_stop):
            max_sum += sum(nums[left:right+1])
            break

    return max_sum


input = sys.stdin.readline
size = int(input().rstrip())
nums = [int(input().rstrip()) for _ in range(size)]
print(solution(size, nums))
