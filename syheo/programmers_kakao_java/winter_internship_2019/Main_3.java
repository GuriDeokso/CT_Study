package programmers_kakao_java.winter_internship_2019;

import java.util.*;

/**
 * 2019 카카오 개발자 겨울 인턴십
 * 불량 사용자
 * <p> 10 : 12 - 11 : 35
 * 아이디어 :
 * hashSets[i]에 banned_id[i]에 해당하는 user_id의 인덱스 번호를 담고
 * dfs 를 돌려 정답이 될 수 있는 경우를 구함.
 */


public class Main_3 {

    public static Set[] hashSets = new HashSet[9]; // hashSets[i]엔 banned_id[i]에 해당하는 user_id의 인덱스 번호를 담음
    static HashSet<Set<Integer>> answers = new HashSet<>(); //정답이 될 수 있는 집합을 담음.
    static Set<Integer> set = new HashSet<>(); //dfs 용 hashSet


    public static void check_and_insert(String[] user_id, String banned_id, int idx) {
        for (int i = 0; i < user_id.length; i++) {
            boolean isPossible = true;
            if (banned_id.length() == user_id[i].length()) {
                for (int j = 0; j < user_id[i].length(); j++) {
                    if (!(banned_id.charAt(j) == user_id[i].charAt(j) || banned_id.charAt(j) == '*')) {
                        isPossible = false;
                        break;
                    }
                }
                if (isPossible) {
                    hashSets[idx].add(i); // user_id[]의 인덱스 번호를 저장함.
                }
            }
        }
    }

    public static int solution2(String[] user_id, String[] banned_id) {
        int answer = 0;

        // 1. check and setting hashset
        for (int i = 0; i < banned_id.length; i++) {
            hashSets[i] = new HashSet<Integer>();
            check_and_insert(user_id, banned_id[i], i);
        }

        // 2. calculate answer by dfs
        dfs(0, banned_id.length);

        answer = answers.size();

        return answer;
    }

    private static void dfs(int start, int length) {
        if (start == length) {
            if (!answers.contains(set) && set.size()==length) {
                Set<Integer> answer = new HashSet<>(set);
                answers.add(answer);
            }
            return;
        }
        for (Object id : hashSets[start]) {
            boolean isRemove = set.contains((Integer)id);
            set.add((Integer) id); // 중복이라고 안넣으면 계산 못하는 경우가 발생
            dfs(start + 1, length);
            if(!isRemove) // 중복 돼서 안넣었는데 삭제되는 거 방지
                set.remove((Integer) id);
        }
    }


    public static void main(String[] args) {
        System.out.println(solution2(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "*rodo", "******", "******"}));
        for (Set<Integer> ans : answers) {
            System.out.println(ans.toString());
        }
    }
}
