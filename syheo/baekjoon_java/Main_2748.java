package baekjoon_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2748 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static long[] pivo = new long[91];

    public static void main(String[] args) throws IOException {

        // 1. input
        N = Integer.parseInt(br.readLine());

        // 2. pivo by dp
        pivo[0] = 0;
        pivo[1] = 1;
        for (int i = 2; i <= N; i++) {
            pivo[i] = pivo[i-1]+pivo[i-2];
        }

        // 3.  answer output
        System.out.println(pivo[N]);
    }
}
