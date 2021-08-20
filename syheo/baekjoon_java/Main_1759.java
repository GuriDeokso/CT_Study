package baekjoon_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1759 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int L, C;
    static char[] alphabets;
    static final Character[] MOEUM = {'a', 'e', 'i', 'o', 'u'};
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
        dfs(0, 0, "");

        // 3. result
        System.out.println(sb.toString());
    }

    private static void dfs(int cnt, int idx, String str) {
        if (cnt == L) {
            int moCnt = 0;
            int zaCnt = 0;
            for (int i = 0; i < L; i++) {
                boolean isMo = false;
                for (int j = 0; j < 5; j++) {
                    if (str.charAt(i) == MOEUM[j]) {
                        moCnt++;
                        isMo = true;
                        break;
                    }
                }
                if (!isMo)
                    zaCnt++;
            }
            if (moCnt > 0 && zaCnt > 1)
                sb.append(str + "\n");
            return;
        }
        for (int i = idx; i < C - L + cnt + 1; i++) { // 가능한 범위까지만 for문 돔.
            char now = alphabets[i];
            dfs(cnt + 1, i + 1, str + String.valueOf(alphabets[i]));
        }
    }
}
