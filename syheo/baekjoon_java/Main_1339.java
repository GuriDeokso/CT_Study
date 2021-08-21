package baekjoon_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * solved.ac
 * 백준
 * 1339
 * 단어 수학
 * 골드 4
 * 아이디어 :
 * 그리디 -> 알파벳별 10^자릿수의 합을 저장하고, 정렬한다.
 * 값이 큰 순으로 높은 수를 할당해주어
 * 계산한 결과를 출력한다.
 **/

public class Main_1339 {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static HashMap<Character,Integer> alphaNums = new HashMap<>(); //알파벳 별 자릿 수 합
    static ArrayList<String> words = new ArrayList<>();
    static int[] alphas = new int[26];
    static int N;
    static int result;

    public static void main(String[] args) throws IOException {

        // 1. input
        N = Integer.parseInt(br.readLine());

        // 2. init
        for (int i = 0; i < 26; i++) {
            alphaNums.put((char)('A'+i),0);
        }

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            words.add(str);
            int cnt = str.length();
            for (int j = 0; j < cnt; j++) {
                alphaNums.replace(str.charAt(j),alphaNums.get(str.charAt(j))+(int)Math.pow(10,cnt-j-1));
            }
        }

        // 3. sort by value
        List<Character> keySetList = new ArrayList<Character>(alphaNums.keySet());
        Collections.sort(keySetList, (o1, o2) -> (alphaNums.get(o2).compareTo(alphaNums.get(o1))));

        int number = 9;
        for(Character key : keySetList) {
            alphas[key-'A']=number;
            number--;
        }

        for (int i = 0; i < words.size(); i++) {
            int sum = 0;
            for (int j = 0; j < words.get(i).length(); j++) {
                sum+=Math.pow(10,words.get(i).length()-j-1)*alphas[words.get(i).charAt(j)-'A'];
            }
            result+=sum;
        }

        System.out.println(result);
    }
}

