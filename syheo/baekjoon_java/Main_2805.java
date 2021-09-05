package baekjoon_java;

import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main_2805{
    /**
     * 2일차
     * 파라메트릭 서치
     * 나무 자르기
     * 실버 3
     * 아이디어 :
     * 파라메트릭 서치를 통해
     * 내가 얻고 싶은 나무를 얻었을 떄
     * 내가 얻고 싶은 나무를 얻지 못했을 때
     *  -> 후자일 경우 최대한 나무를 아끼고 내가 얻고 싶은 나무와 가장 근접한 답일 경우
     * 자를때는 mid보다 클 때만 자를 수 있음!
     */

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder sb = new StringBuilder();
    static Long[] trees;
    static long N,M;
    static long result=-1;
    static long max;
    public static void main(String[] args) throws IOException{
        tokens = new StringTokenizer(input.readLine());

        N = Long.parseLong((tokens.nextToken()));
        M = Long.parseLong((tokens.nextToken()));

        trees = new Long[(int)N];
        tokens = new StringTokenizer(input.readLine());
        for(int i=0;i<N;i++){
            trees[i] = Long.parseLong(tokens.nextToken());
            max = Math.max(trees[i],max);
        }

        search();

        System.out.println(result);
    }

    public static void search(){
        long left = 0;
        long right = max;
        long mid = (left+right)/2;

        while(left<=right){
            long sum = 0;

            mid = (left+right)/2;
            for(int i=0;i<N;i++){
                if(trees[i]>mid)
                    sum+=trees[i]-mid;
            }
            if(sum<M){
                right = mid-1;
            }
            else if(sum==M){
                result = mid;
                break;
            }

            else{
                left = mid+1;
                result = Math.max(result,mid);
            }
        }

    }
}
