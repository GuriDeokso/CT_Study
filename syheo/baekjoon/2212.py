#solved.ac
#골드5
#정렬
#센서
#2212

# 6
# 2
# 1 3 6 6 7 9
# 2 3 0 1 2

# 아이디어 
# 정렬 후 센서들 간의 간격을 구한 뒤 내림차순 정렬함.
# K의 갯수 -1 만큼 간격들을 뺴낼 수 있음.
# 뺀것들의 합을 구함 

N = int(input())
K = int(input())

sensors = list(map(int,input().split()))

sensors.sort()

gaps = []

for i in range(1,N):
    gaps.append(sensors[i]-sensors[i-1])

gaps.sort(reverse=True)

print(sum(gaps[K-1:]))

