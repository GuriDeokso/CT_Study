package baekjoon_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * solved.ac
 * 백준
 * 2143
 * 두 배열의 합
 * 아이디어 :
 * A,B의 부분합을 key, 갯수를 value로 갖는 각각의 hashmap
 * 을 만들어서 조건을 만족하는 경우 A의 갯수 * B의 갯수 를 answer에 더해주어 풀이함.
 * 처음에 hashMap.merge에서 BiFunction에 들어가는 value를 모르고 사용하여
 * 많이 틀림 ㅠ
 * 이분탐색을 써도 충분히 풀 수 있을 듯 . -> 처음에 시도했던 방법
 **/

public class Main_2143 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static long T;
    static int n, m;
    static int[] A;
    static int[] B;
    static List<Long> sumOfA;
    static List<Long> sumOfB;
    static Map<Long, Integer> counterA;
    static Map<Long, Integer> counterB;
    static long answer;

    public static void main(String[] args) throws IOException {
        // 1. input
        T = Long.parseLong(br.readLine());
        n = Integer.parseInt(br.readLine());
        A = new int[n];
        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(tokens.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        B = new int[m];
        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(tokens.nextToken());
        }
        System.out.println(T+" "+n+" "+m+" ");
        // 2. sum
        sumOfA = new ArrayList<>();
        sumOfB = new ArrayList<>();
        counterA = new HashMap<>();
        counterB = new HashMap<>();
        long tmp;
        for (int i = 0; i < n; i++) {
            tmp = 0;
            for (int j = i; j < n; j++) {
                tmp += A[j];
                sumOfA.add(tmp);
                counterA.put(tmp,counterA.getOrDefault(tmp,0)+1);
                // counterA.merge(tmp, 1, (val1, val2) -> val1 + 1);
            }
        }
        for (int i = 0; i < m; i++) {
            tmp = 0;
            for (int j = i; j < m; j++) {
                tmp += B[j];
                sumOfB.add(tmp);
                counterB.put(tmp,counterA.getOrDefault(tmp,0)+1);
                // counterB.merge(tmp, 1, (val1, val2) -> val1 + 1);
            }
        }
        System.out.println(sumOfA.toString()+" "+sumOfB.toString());
        System.out.println(counterA.toString()+" "+counterB.toString());
//        //3. binary search

//        Collections.sort(sumOfA);
//        Collections.sort(sumOfB);
//        for (Long aLong : sumOfA) {
//            answer += binary_search(aLong);
//        }

        // 3. search
        answer = 0;

        for (Map.Entry<Long, Integer> entry : counterA.entrySet()) {
            if (counterB.containsKey(T - entry.getKey()))
                answer += (long) entry.getValue() * counterB.get(T - entry.getKey());
        }

        System.out.println(answer);

    }

    private static int binary_search(Long aNum) {
        int start = 0;
        int end = sumOfB.size() - 1;
        int middle;
        while (start <= end) {
            middle = (start + end) / 2;
            if (sumOfB.get(middle) == T - aNum) {
                return counterB.get(sumOfB.get(middle));
            } else if (sumOfB.get(middle) < T - aNum) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return 0;
    }
}
