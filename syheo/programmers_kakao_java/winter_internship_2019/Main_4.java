package programmers_kakao_java.winter_internship_2019;

import java.util.Arrays;

/**
 * 2019 카카오 개발자 겨울 인턴십
 * 징검다리 건너기
 * <p> 12:00 - 2:30
 * 아이디어 :
 * 건널 수 있는 인원(mid)를 구하기 위해 이분탐색을 한다.
 * mid 미만의 최대 징검다리 길이(maxWidth)와 k값을 비교한다.
 * 1) maxWidth 가 k보다 크거나 같은 경우 -> 못 건넘.
 * 2) maxWidth 가 k보다 작은 경우 -> 건널 수 있음.
 */

public class Main_4 {

    static int[] stoneArr;
    static int stK;

    public static int binary_search(int start,int end) {
        int answer = 0;
        // 2, 4, 5, 3, 2, 1, 4, 2, 5, 1
        int mid = (start+end)/2;
        while(start<=end){
            mid = (start+end)/2;
            System.out.println(start+" "+mid+" "+end);
            int maxWidth = 0;
            int cnt = 0;
            for (int i = 0; i < stoneArr.length; i++) {
                if(stoneArr[i]<mid){
                    cnt++;
                }
                else{
                    maxWidth = Math.max(maxWidth,cnt);
                    cnt = 0;
                }
            }
            maxWidth = Math.max(maxWidth,cnt);

            // 1) maxWidth 가 k보다 크거나 같은 경우 -> 못 건넘.
            if(maxWidth>=stK){
                end = mid - 1;
            }
            // 2) maxWidth 가 k보다 작은 경우 -> 건널 수 있음.
            else if(maxWidth<stK){
                start = mid +1;
                answer = mid;
            }
        }

        return answer;
    }

    public static int solution(int[] stones, int k) {
        int answer = 0;
        // 최대값 초기값 세팅
        int max = stones[0];
        int min = stones[0];
        // 최대값 구하기
        for (int num : stones) {
            if (num > max) {
                max = num;
            }
            if( num < min){
                min = num;
            }
        }
        stoneArr = stones;
        stK = k;
        answer = binary_search(min,max);

        return answer;
    }

    public static int solution2(int[] stones, int k) {
        int answer = 0;
        boolean isPossible = true;
        while (true) {
            int now = 0;
            while (now < stones.length - k) {
                if (stones[now] <= answer) {
                    int gameStart = 1;
                    while (now + gameStart < stones.length && stones[now + gameStart] <= answer) {
                        gameStart++;
                        if (gameStart >= k) {
                            return answer;
                        }
                    }
                    now += gameStart;
                } else {
                    now++;
                }

            }
            answer += 1;
        }

    }


    public static void main(String[] args) {
        System.out.println(solution(new int[]{3,3,3,40000 ,3,3,3,3,5,3}, 5));
    }
}
