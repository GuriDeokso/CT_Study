package programmers_kakao_java.winter_internship_2019;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 2019 카카오 개발자 겨울 인턴십
 * 튜플
 * 4:50 - 5:05 , 5:40 - 6:18 -> 15+38 = 53분 소요
 * 아이디어 : Set[] sets 변수를 만들어 sets[i] 는 집합의 길이가 i 인 Set 객체를 담는다.
 * 그리고 sets[집합 중 가장 큰 길이] 부터 바로 이전 Set 객체와 차집합 추출을 하여 남은 하나를
 * 정답 배열에 역순으로 담는다.

 */

public class Main_2 {

    static Set[] sets = new Set[501];
    static int tupleLength = 0; // 튜플의 길이

    public static void extractDigits(String s) {
        StringBuilder builder = new StringBuilder();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) { // 숫자 추출
                builder.append(c);
            }
            else if(c=='}'){ // sets에 Set 객체 넣기
                if(builder.length()>0){
                    set.add(Integer.parseInt(builder.toString()));
                }
                builder = new StringBuilder();
                tupleLength = Math.max(tupleLength,set.size()); // 튜플의 길이 업데이트
                sets[set.size()] = set;
                set = new HashSet<>();
            }
            else{ // 숫자를 set에 저장
                if(builder.length()>0){
                    set.add(Integer.parseInt(builder.toString()));
                }
                builder = new StringBuilder();
                tupleLength = Math.max(tupleLength,set.size());
            }
        }
    }

    public static int[] solution(String s) {
        int[] answer;

        // 1. extract digit and init sets
        extractDigits(s);

        sets[0] = new HashSet<>();
        answer = new int[tupleLength];

        for (int i = tupleLength; i >= 1; i--) { // 역순으로
            sets[i].removeAll(sets[i-1]); // 차집합 계산
            answer[i-1] =(Integer)sets[i].iterator().next(); // 하나 남은것을 역순으로 정답 배열에 담는다.
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution("{{2},{2,1},{2,1,3},{2,1,3,4}}")));
    }
}
