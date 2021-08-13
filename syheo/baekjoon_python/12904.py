#solved.ac
#골드5
#그리디
#a와b
#12904

#문자열의 뒤에 A를 추가한다.
#문자열을 뒤집고 뒤에 B를 추가한다.

#아이디어 
# T에서 A,B를 빼서 S를 만들 수 있는지 확인 
# reverseCnt를 두어 reverse한 숫자를 기억하고
# reverseCnt에 맞게 조회와 삭제 위치(앞,뒤)를 설정함.

S = list(input())
T = list(input())

reverseCnt = 0 
idxs = [-1,0]
while len(S)<len(T):
    if T[idxs[reverseCnt%2]]=='B':
        T.pop(idxs[reverseCnt%2])
        reverseCnt +=1
    elif T[idxs[reverseCnt%2]]=='A':
        T.pop(idxs[reverseCnt%2])
    else:
        break

if reverseCnt%2!=0:
    T.reverse()
if S==T:
    print(1)
else:
    print(0)

