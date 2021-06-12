def my_solution(s, t):
    s_size = len(s)
    t_size = len(t)
    t = list(t)

    for _ in range(t_size - s_size):
        ch = t.pop()
        if ch == 'B':
            t.reverse()
    print(1) if ''.join(t) == s else print(0)


def my_solution_2(s, t):
    s = list(s)
    t = list(t)

    while(len(s) != len(t)):
        ch = t.pop()
        if ch == 'B':
            t.reverse()
    print(1 if s == t else 0)


s = input()
t = input()
my_solution_2(s, t)
