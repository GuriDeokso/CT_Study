import sys


def solution(length: int, value: int) -> str:
    if(value > 26 * length or value < length):
        return "!"

    base = ord("A")
    result = []
    while(value > 26 + length - 1):
        length -= 1
        value -= 26
        result.append("Z")
    target = value - length
    result.append(chr(base + target))
    for _ in range(length-1):
        result.append("A")
    return ''.join(result[::-1])


input = sys.stdin.readline
LENGTH, VALUE = map(int, input().split())
print(solution(LENGTH, VALUE))
