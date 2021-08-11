import math
import heapq

def distance(a,b,c,d):
    return math.sqrt((a-c)**2+(b-d)**2)

n = int(input())
stars = [] 
connected = []
answer = 0 

for i in range(n):
    stars.append(tuple(map(float,input().split())))

connected.append(0)
q = [] 
for i in range(1,n):
    heapq.heappush(q,(distance(stars[0][0],stars[0][1],stars[i][0],stars[i][1]),i))

while len(connected)!=n:
    value, node = heapq.heappop(q)
    while node in connected:
        value, node = heapq.heappop(q)
    answer+=value
    connected.append(node)
    for i in range(n):
        if i not in connected:
            heapq.heappush(q,(distance(stars[node][0],stars[node][1],stars[i][0],stars[i][1]),i))
print("%.2f"%(answer))
