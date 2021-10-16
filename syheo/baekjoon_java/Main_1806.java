package baekjoon_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac
 * 백준
 * 1806
 * 부분합
 * 아이디어 :
 * 전형적인 투포인터 문제
 * 수열에 투포인터 알고리즘을 적용하여 풀이
 * SUM == S 일때 sum>S와 같은 동작을 수행해도 무관하니
 * 두 조건문을 묶어도 될듯.
 **/

public class Main_1806 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N,S;
    static int[] sequence;
    static int start, end;
    static int answer;

    public static void main(String[] args) throws IOException {
        // 1. input
        tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        S = Integer.parseInt(tokens.nextToken());

        tokens = new StringTokenizer(br.readLine());
        sequence = new int[N+1];
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(tokens.nextToken());
        }

        // 2. two point algorithm
        start = 0;
        end = 0;
        answer = N+1;
        int sum = sequence[0];
        while(true){
            if(end==N || start==N){
                break;
            }
            if(sum<S){
                sum+=sequence[++end];
            }
            else if(sum==S){
                answer = Math.min(answer,end-start+1);
                sum+=sequence[++end];
            }
            else{
                answer = Math.min(answer,end-start+1);
                sum-=sequence[start++];
            }

        }

        if(answer == N+1){
            System.out.println(0);

        }
        else{
            System.out.println(answer);
        }

    }

}
