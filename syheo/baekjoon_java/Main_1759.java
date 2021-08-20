package baekjoon_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1759 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int L,C;
    static char[] alphabets;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        // 1. input
        tokens = new StringTokenizer(br.readLine());

        L = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());

        alphabets = new char[C];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            alphabets[i] = tokens.nextToken().charAt(0);
        }

        Arrays.sort(alphabets);
        // 2. dfs
        dfs(0,0,"");

        // 3. result
        System.out.println(sb.toString());
    }

    private static void dfs(int cnt,int idx, String str) {
        if(cnt == L){
            sb.append(str+"\n");
            return;
        }
        for (int i = idx; i < C; i++) {
            dfs(cnt+1,i+1,str+String.valueOf(alphabets[i]));
        }
    }
}
