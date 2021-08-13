package baekjoon_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * solved.ac
 * 백준
 * 1920
 * 수 찾기
 * 실버 4
 * 아이디어 :
 * NList를  HashSet을 이용하여 단일 조회의 시간을 logN으로 단축 시킨다.
 * HashMap이 HashSet보다 빠르니 HashMap을 사용해봐도 좋을 듯 하다.
 **/

public class Main_1920 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N,M;
    static HashSet<Integer> nList;
    static ArrayList<Integer> mList;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        tokens = new StringTokenizer(br.readLine());
        nList = new HashSet<>();
        for (int i = 0; i < N; i++) {
            nList.add(Integer.parseInt(tokens.nextToken()));
        }

        M = Integer.parseInt(br.readLine());
        tokens = new StringTokenizer(br.readLine());
        mList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            mList.add(Integer.parseInt(tokens.nextToken()));
        }

        for (int i = 0; i < M; i++) {
            if(nList.contains(mList.get(i))){
                System.out.println(1);
            }
            else{
                System.out.println(0);
            }
        }

    }
}
