package baekjoon_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2003 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N,M;
    static int[] nums;
    static int start,end;
    static int sum = 0;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        // 1. input
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        nums = new int[N];
        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(tokens.nextToken());
        }


        // 2. two pointer start
        start = 0;
        end = 0;
        sum = nums[start];
        while(start<N){
            if(sum == M){
                answer++;
                if(end<N-1) {
                    end++;
                    sum += nums[end];
                }
                else{
                    break;
                }
            }
            else if(sum<M){
                if(end==N-1){
                    break;
                }
                end++;
                sum+=nums[end];
            }
            else{
                if(start==end){
                    if(end==N-1){
                        break;
                    }
                    end++;
                    sum+=nums[end];
                }
                else{
                    sum-=nums[start];
                    start++;
                }

            }
        }

        System.out.println(answer);
    }
}
