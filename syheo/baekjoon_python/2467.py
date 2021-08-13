#solved.ac
#골드5
#이진탐색 
#용액
#2467

# 반례
# 5
# -100 -50 35 71 120

# 아이디어 
# 투포인터를 사용하여 알칼리, 산성일 경우 start++ ,end-- 하여 포인터 이동 

def cal_gap(start,end):
    return waters[start]+waters[end]

N = int(input())

waters = list(map(int,input().split()))

start = 0
end = N-1

min_value = int(1e12)
min_start = 0
min_end = N-1

while start<end:
    gap = cal_gap(start,end)
    #중성이면?
    if gap == 0:
        min_start = start
        min_end = end
        break
    #min_value 비교
    if abs(gap) < min_value:
        min_value = abs(gap)
        min_start,min_end = start,end
    #산성, 알칼리에 따라 포인터 이동 
    if gap < 0:
        start+=1
    elif gap > 0:
        end-=1

    
print(waters[min_start],waters[min_end])
